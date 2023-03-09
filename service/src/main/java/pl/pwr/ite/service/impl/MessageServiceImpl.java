package pl.pwr.ite.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.pwr.ite.model.entity.Message;
import pl.pwr.ite.model.repository.MessageRepository;
import pl.pwr.ite.service.MessageService;

import java.util.UUID;

@Service
public class MessageServiceImpl extends EntityServiceBase<Message> implements MessageService {

    protected MessageServiceImpl(MessageRepository repository) {
        super(repository);
    }
}
