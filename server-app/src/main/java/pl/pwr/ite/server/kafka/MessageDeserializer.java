package pl.pwr.ite.server.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import pl.pwr.ite.kafka.DeserializerBase;
import pl.pwr.ite.server.api.dto.MessageDto;

public class MessageDeserializer extends DeserializerBase<MessageDto> {
}
