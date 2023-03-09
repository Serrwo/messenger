package pl.pwr.ite.service;

import pl.pwr.ite.model.entity.Topic;

import java.util.Collection;

public interface TopicService extends EntityService<Topic> {
    Collection<Topic> listEnabled();
}
