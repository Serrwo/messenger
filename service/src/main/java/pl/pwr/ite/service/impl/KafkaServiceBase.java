package pl.pwr.ite.service.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import pl.pwr.ite.mapping.Mapper;
import pl.pwr.ite.model.entity.EntityBase;
import pl.pwr.ite.service.EntityService;
import pl.pwr.ite.service.Listener;

public abstract class KafkaServiceBase<K, V, E extends EntityBase, M extends Mapper<E, V>, S extends EntityService<E>> implements Listener<K, V, E> {

    protected final String defaultTopic;

    protected final KafkaTemplate<K, V> template;

    protected final S service;

    protected final M mapper;

    public KafkaServiceBase(String defaultTopic, KafkaTemplate<K, V> template, S service, M mapper) {
        this.defaultTopic = defaultTopic;
        this.template = template;
        this.service = service;
        this.mapper = mapper;
    }

    public E save(E entity) {
        return service.save(entity);
    }

    public E saveAndFlush(E entity) {
        return service.saveAndFlush(entity);
    }

    @Override
    public ListenableFuture<SendResult<K, V>> produce(E data) {
        return produce(this.defaultTopic, data);
    }

    @Override
    public ListenableFuture<SendResult<K, V>> produce(V data) {
        return produce(defaultTopic, data);
    }

    @Override
    public ListenableFuture<SendResult<K, V>> produce(String topic, E data) {
        return produce(topic, mapper.map(data));
    }

    @Override
    public ListenableFuture<SendResult<K, V>> produce(String topic, V payload) {
        return template.send(topic, payload);
    }
}
