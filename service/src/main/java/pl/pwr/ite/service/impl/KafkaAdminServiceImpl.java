package pl.pwr.ite.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.errors.TopicExistsException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import pl.pwr.ite.model.entity.Topic;
import pl.pwr.ite.service.KafkaAdminService;
import pl.pwr.ite.service.TopicService;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaAdminServiceImpl implements KafkaAdminService, InitializingBean {

    private static final Integer DEFAULT_TIMEOUT_MS = 5 * 60 * 1000;

    private final TopicService topicService;

    private final AdminClient adminClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        registerTopics(topicService.listEnabled());
    }

    @Override
    public Map<String, TopicListing> listTopics(boolean listInternal) {
        var listTopicsOptions = new ListTopicsOptions();
        listTopicsOptions.timeoutMs(DEFAULT_TIMEOUT_MS);
        listTopicsOptions.listInternal(listInternal);
        var listTopicResult = adminClient.listTopics(listTopicsOptions);
        try {
            return listTopicResult.namesToListings().get();
        } catch (ExecutionException | InterruptedException ex) {
            throw new RuntimeException(String.format("Couldn't list topics, timeout was %s ms", DEFAULT_TIMEOUT_MS), ex);
        }
    }

    @Override
    public void registerTopic(Topic topic) {
        this.registerTopics(Collections.singleton(topic));
    }

    private List<Topic> registerTopics(Collection<Topic> topics) {
        var registeredTopics = new ArrayList<Topic>();
        var existingTopics = listTopics(false);
        for(Topic topic : topics) {
            var topicName = topic.getName();
            if(existingTopics.containsKey(topicName)) {
                continue;
            }
            try {
                var newTopic = new NewTopic(topicName, topic.getPartitions(), topic.getReplicationFactor());
                var result = adminClient.createTopics(Collections.singleton(newTopic));
                var topicId= result.topicId(topicName);
                topic.setKafkaId(topicId.get().toString());
                registeredTopics.add(topicService.saveAndFlush(topic));
            } catch (TopicExistsException ex) {
                log.info("Couldn't create topic with name '"+ topicName + "', topic already exists.");
            } catch (ExecutionException | InterruptedException ex) {
                throw new IllegalArgumentException(String.format("Couldn't retrieve Uuid from topic '%s'", topicName), ex);
            }
        }
        return registeredTopics;
    }
}
