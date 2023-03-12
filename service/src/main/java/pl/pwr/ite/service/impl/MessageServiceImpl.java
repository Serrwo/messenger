package pl.pwr.ite.service.impl;

import org.springframework.stereotype.Service;
import pl.pwr.ite.model.entity.Message;
import pl.pwr.ite.model.repository.MessageRepository;
import pl.pwr.ite.service.MessageService;

@Service
public class MessageServiceImpl extends EntityServiceBase<Message> implements MessageService {

    public MessageServiceImpl(MessageRepository repository) {
        super(repository);
    }
}
