package pl.pwr.ite.service;

import pl.pwr.ite.model.entity.ForbiddenWord;
import pl.pwr.ite.model.entity.Message;

import java.util.Iterator;

public interface ForbiddenWordService extends EntityService<ForbiddenWord>{

   Iterator<ForbiddenWord> iterate();

   Message censorMessage(Message messageToCensor);
}
