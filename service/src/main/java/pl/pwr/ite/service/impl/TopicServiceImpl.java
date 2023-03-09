package pl.pwr.ite.service.impl;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.pwr.ite.model.entity.QTopic;
import pl.pwr.ite.model.entity.Topic;
import pl.pwr.ite.model.repository.TopicRepository;
import pl.pwr.ite.service.TopicService;

import javax.persistence.EntityManager;
import java.util.Collection;

@Service
public class TopicServiceImpl extends EntityServiceBase<Topic> implements TopicService {

    private final EntityManager entityManager;

    protected TopicServiceImpl(TopicRepository repository, EntityManager entityManager) {
        super(repository);
        this.entityManager = entityManager;
    }

    @Override
    public Collection<Topic> listEnabled() {
        QTopic path = QTopic.topic;
        return new JPAQuery<>(entityManager)
                .select(path).from(path)
                .where(path.enabled.eq(Expressions.TRUE))
                .fetch();
    }
}
