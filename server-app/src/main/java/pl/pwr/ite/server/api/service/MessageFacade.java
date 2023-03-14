package pl.pwr.ite.server.api.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.pwr.ite.model.entity.Message;
import pl.pwr.ite.model.entity.Topic;
import pl.pwr.ite.server.api.dto.MessageDto;
import pl.pwr.ite.server.api.mapper.MessageMapper;
import pl.pwr.ite.service.ClockService;
import pl.pwr.ite.service.MessageService;
import pl.pwr.ite.service.TopicService;
import pl.pwr.ite.service.impl.EntityServiceFacade;

import java.util.UUID;

@Component
public class MessageFacade extends EntityServiceFacade<Message, MessageDto, MessageService, MessageMapper> {

    private final TopicService topicService;

    private final ClockService clockService;

    public MessageFacade(MessageService service, MessageMapper mapper, TopicService topicService, ClockService clockService) {
        super(service, mapper);
        this.topicService = topicService;
        this.clockService = clockService;
    }


    public Message create(MessageDto dto) {
        Message message = new Message();

//        UUID topicId = dto.getTopic().getId();
//        Topic topic = topicService.findById(topicId);
//        if(topic == null) {
//            throw new IllegalArgumentException(String.format("Topic with ID '%s' not found.", topicId));
//        }

        message.setMessage(dto.getMessage());
//        message.setTopic(topic);
        message.setTime(clockService.getCurrentTime());

        return message;
    }
}
