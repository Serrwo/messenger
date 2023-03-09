package pl.pwr.ite.server;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.pwr.ite.server.api.dto.MessageDto;

@Component
public class KafkaTestListener {

    @KafkaListener(topics = "main", groupId = "groupId", containerFactory = "messageConsumerFactory")
    void listener(MessageDto data) {
        System.out.println("Listener received: " + data);
    }
}