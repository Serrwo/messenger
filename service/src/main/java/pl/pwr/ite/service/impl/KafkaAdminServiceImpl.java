package pl.pwr.ite.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicListing;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import pl.pwr.ite.model.entity.Topic;
import pl.pwr.ite.service.KafkaAdminService;
import pl.pwr.ite.service.TopicService;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class KafkaAdminServiceImpl implements KafkaAdminService, InitializingBean {

    private final TopicService topicService;

    private final AdminClient adminClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        registerTopics(topicService.listEnabled());
    }


    @Override
    public Map<String, TopicListing> listTopics() throws ExecutionException, InterruptedException {
        return adminClient.listTopics().namesToListings().get();
    }

    @Override
    public void registerTopic(Topic topic) {
        this.registerTopics(Collections.singleton(topic));
    }

    private void registerTopics(Collection<Topic> topics) {
        List<NewTopic> topicsToRegister = new ArrayList<>();
        for(Topic topic : topics) {
            NewTopic newTopic = new NewTopic(topic.getName(), topic.getPartitions(), topic.getReplicationFactor());
            topicsToRegister.add(newTopic);
        }
        adminClient.createTopics(topicsToRegister);
    }
}
