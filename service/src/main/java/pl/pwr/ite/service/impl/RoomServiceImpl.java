package pl.pwr.ite.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;
import pl.pwr.ite.service.RoomService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final AdminClient adminClient;

    private Map<UUID, Consumer<String, MessageDto>> rooms = new HashMap<>();

    public void registerRoom(UUID roomId) {
        NewTopic newTopic = new NewTopic(roomId.toString(), 1, (short) 1);
        adminClient.createTopics(Collections.singleton(newTopic));

        Consumer<String, MessageDto> consumer = new KafkaConsumer<String, MessageDto>(new HashMap<>());
        consumer.subscribe(Collections.singleton(roomId.toString()));
        rooms.put(roomId, consumer);
    }

    private class MessageDto {

    }
}
