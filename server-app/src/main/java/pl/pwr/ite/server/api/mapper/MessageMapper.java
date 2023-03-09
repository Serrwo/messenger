package pl.pwr.ite.server.api.mapper;

import org.springframework.stereotype.Component;
import pl.pwr.ite.mapping.MapperBase;
import pl.pwr.ite.model.entity.Message;
import pl.pwr.ite.server.api.dto.MessageDto;

@Component
public class MessageMapper extends MapperBase<Message, MessageDto> {

    @Override
    public void transform(Message source, MessageDto destination) {
        source.setMessage(destination.getMessage());
        source.setTopic(destination.getTopic());
        source.setTime(destination.getTime());
    }
}
