package pl.pwr.ite.server;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;
import pl.pwr.ite.server.api.dto.MessageDto;

@Component
@RequiredArgsConstructor
public class KafkaTestListener {

    @KafkaListener(topics = "main", groupId = "groupId", containerFactory = "messageConsumerContainerFactory")
    void listener(MessageDto data) {
        System.out.println("Listener received: " + data);
    }

}