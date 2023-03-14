package pl.pwr.ite.server.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.pwr.ite.kafka.Topics;
import pl.pwr.ite.model.entity.Message;
import pl.pwr.ite.server.api.dto.MessageDto;
import pl.pwr.ite.server.api.mapper.MessageMapper;
import pl.pwr.ite.server.api.service.MessageFacade;
import pl.pwr.ite.service.ForbiddenWordService;
import pl.pwr.ite.service.MessageService;
import pl.pwr.ite.service.impl.KafkaServiceBase;

@Component
public class MessageKafkaService extends KafkaServiceBase<String, MessageDto, Message, MessageMapper, MessageService> {

    @Value("${spring.kafka.group-id}")
    private static String groupId;

    private final MessageFacade messageFacade;

    private final ForbiddenWordService forbiddenWordService;

    private static final String DEFAULT_GROUP_ID = "defaultMessengerGroup";

    public MessageKafkaService(MessageService service, MessageMapper mapper, MessageFacade messageFacade, KafkaTemplate<String, MessageDto> producerTemplate, ForbiddenWordService forbiddenWordService) {
        super(Topics.MESSAGE_BROADCAST_DEFAULT, producerTemplate, service, mapper);
        this.messageFacade = messageFacade;
        this.forbiddenWordService = forbiddenWordService;
    }

    @Override
    @KafkaListener(topics = Topics.CENSORSHIP_DEFAULT, groupId = DEFAULT_GROUP_ID, containerFactory = "messageConsumerContainerFactory")
    public void consume(ConsumerRecord<String, MessageDto> record) {
        Message message = messageFacade.create(record.value());
        forbiddenWordService.censorMessage(message);
        message = super.service.saveAndFlush(message);
        produce(Topics.MESSAGE_BROADCAST_DEFAULT, message);
    }

    @KafkaListener(topics = Topics.MESSAGE_BROADCAST_DEFAULT, groupId = DEFAULT_GROUP_ID, containerFactory = "messageConsumerContainerFactory")
    public void consumeBroadcast(ConsumerRecord<String, MessageDto> record) {
        MessageDto message = record.value();
    }
}
