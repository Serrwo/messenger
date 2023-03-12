package pl.pwr.ite.server.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.pwr.ite.model.entity.Message;
import pl.pwr.ite.server.api.dto.MessageDto;
import pl.pwr.ite.server.api.mapper.MessageMapper;
import pl.pwr.ite.server.api.service.MessageFacade;
import pl.pwr.ite.service.MessageService;
import pl.pwr.ite.service.impl.KafkaListenerBase;

@Component
public class MessageKafkaListener extends KafkaListenerBase<String, MessageDto, Message, MessageMapper, MessageService> {

    private final MessageFacade messageFacade;

    public MessageKafkaListener(MessageService service, MessageMapper mapper, MessageFacade messageFacade) {
        super(service, mapper);
        this.messageFacade = messageFacade;
    }

    @Override
    @KafkaListener(topics = "main", groupId = "groupId", containerFactory = "messageConsumerContainerFactory")
    public void consume(ConsumerRecord<String, MessageDto> record) {
        Message message = messageFacade.create(record.value());
    }
}
