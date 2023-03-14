package pl.pwr.ite.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import pl.pwr.ite.model.entity.EntityBase;

public interface Listener<K, V, E extends EntityBase> {

    void consume(ConsumerRecord<K, V> record);

    ListenableFuture<SendResult<K, V>> produce(E data);

    ListenableFuture<SendResult<K, V>> produce(V data);

    ListenableFuture<SendResult<K, V>> produce(String topic, E data);

    ListenableFuture<SendResult<K, V>> produce(String topic, V data);
}
