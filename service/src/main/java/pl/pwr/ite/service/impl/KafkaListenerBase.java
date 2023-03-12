package pl.pwr.ite.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import pl.pwr.ite.mapping.Mapper;
import pl.pwr.ite.model.entity.EntityBase;
import pl.pwr.ite.service.EntityService;
import pl.pwr.ite.service.Listener;

public abstract class KafkaListenerBase<K, V, E extends EntityBase, M extends Mapper<E, V>, S extends EntityService<E>> implements Listener<K, V> {

    protected final S service;

    protected final M mapper;

    public KafkaListenerBase(S service, M mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public E save(E entity) {
        return service.save(entity);
    }

    public E saveAndFlush(E entity) {
        return service.saveAndFlush(entity);
    }

    public S getService() {
        return service;
    }
}
