package pl.pwr.ite.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.hibernate.type.SerializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pwr.ite.utils.GenericHelper;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public abstract class DeserializerBase<E> implements Deserializer<E> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Class<E> entityType;

    public DeserializerBase() {
        entityType = GenericHelper.getArgumentType(getClass(), "E");
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public E deserialize(String topic, byte[] data) {
        if(data == null) {
            return null;
        }
        try {
            return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), entityType);
        } catch (JsonProcessingException ex) {
            throw new SerializationException("Couldn't deserialize object.", null);
        }
    }
}
