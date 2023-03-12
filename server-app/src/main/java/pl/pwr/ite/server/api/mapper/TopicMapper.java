package pl.pwr.ite.server.api.mapper;

import org.springframework.stereotype.Component;
import pl.pwr.ite.mapping.MapperBase;
import pl.pwr.ite.model.entity.Topic;
import pl.pwr.ite.server.api.dto.TopicDto;

@Component
public class TopicMapper extends MapperBase<Topic, TopicDto> {

    @Override
    public void transform(Topic source, TopicDto destination) {
        destination.setId(source.getId());
        destination.setName(source.getName());
    }
}
