package pl.pwr.ite.service;

import org.apache.kafka.clients.admin.TopicListing;
import pl.pwr.ite.model.entity.Topic;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface KafkaAdminService {
    Map<String, TopicListing> listTopics(boolean listInternal) throws ExecutionException, InterruptedException;

    void registerTopic(Topic topic);
}
