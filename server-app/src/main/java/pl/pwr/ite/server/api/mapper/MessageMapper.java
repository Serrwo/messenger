package pl.pwr.ite.server.api.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pwr.ite.mapping.MapperBase;
import pl.pwr.ite.model.entity.Message;
import pl.pwr.ite.server.api.dto.MessageDto;

@Component
@RequiredArgsConstructor
public class MessageMapper extends MapperBase<Message, MessageDto> {

    private final TopicMapper topicMapper;

    @Override
    public void transform(Message source, MessageDto destination) {
        destination.setMessage(source.getMessage());
//        destination.setTime(source.getTime());

//        map(destination::setTopic, source.getTopic(), topicMapper);
    }
}
