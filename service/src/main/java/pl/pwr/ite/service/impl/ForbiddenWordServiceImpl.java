package pl.pwr.ite.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.pwr.ite.model.entity.ForbiddenWord;
import pl.pwr.ite.model.entity.Message;
import pl.pwr.ite.model.repository.ForbiddenWordRepository;
import pl.pwr.ite.service.ForbiddenWordService;

import java.util.Iterator;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class ForbiddenWordServiceImpl extends EntityServiceBase<ForbiddenWord> implements ForbiddenWordService, InitializingBean {

    protected ForbiddenWordServiceImpl(ForbiddenWordRepository repository) {
        super(repository);
    }

    public Iterator<ForbiddenWord> iterate(){
          return repository.findAll().iterator();
    }

    @Override
    public Message censorMessage(Message messageToCensor) {




        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
