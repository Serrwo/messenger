package pl.pwr.ite.server.api.service;

import org.springframework.stereotype.Component;
import pl.pwr.ite.kafka.Topics;
import pl.pwr.ite.model.entity.Message;
import pl.pwr.ite.server.api.dto.MessageDto;
import pl.pwr.ite.server.api.mapper.MessageMapper;
import pl.pwr.ite.server.kafka.MessageKafkaService;
import pl.pwr.ite.service.ClockService;
import pl.pwr.ite.service.MessageService;
import pl.pwr.ite.service.TopicService;
import pl.pwr.ite.service.impl.EntityServiceFacade;

@Component
public class MessageFacade extends EntityServiceFacade<Message, MessageDto, MessageService, MessageMapper> {

    private final TopicService topicService;

    private final MessageKafkaService messageKafkaService;

    private final ClockService clockService;

    public MessageFacade(MessageService service, MessageMapper mapper, TopicService topicService, MessageKafkaService messageKafkaService, ClockService clockService) {
        super(service, mapper);
        this.topicService = topicService;
        this.messageKafkaService = messageKafkaService;
        this.clockService = clockService;
    }

    public Message create(MessageDto dto) {
        Message message = new Message();

        message.setMessage(dto.getMessage());
        message.setTime(clockService.getCurrentTime());
        message.setUsername(dto.getUsername());

        return message;
    }

    public void produce(String topic, MessageDto message) {
        messageKafkaService.produce(topic, message);
    }
}
