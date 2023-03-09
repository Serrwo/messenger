package pl.pwr.ite.server.api.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.pwr.ite.model.entity.Message;
import pl.pwr.ite.server.api.dto.MessageDto;
import pl.pwr.ite.server.api.mapper.MessageMapper;
import pl.pwr.ite.service.impl.EntityServiceFacade;

@Component
public class MessageFacade extends EntityServiceFacade<Message, MessageDto, MessageMapper> {

}
