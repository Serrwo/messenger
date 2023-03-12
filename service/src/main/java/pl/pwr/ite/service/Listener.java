package pl.pwr.ite.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface Listener<K, V> {

    void consume(ConsumerRecord<K, V> record);
}
