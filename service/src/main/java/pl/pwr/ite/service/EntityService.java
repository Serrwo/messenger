package pl.pwr.ite.service;

import pl.pwr.ite.model.entity.EntityBase;

import java.util.List;
import java.util.UUID;

public interface EntityService<E extends EntityBase> {

    E findById(UUID id);

    <T extends E> T save(T entity);

    <T extends E> T saveAndFlush(T entity);

    void delete(E entity);

    void deleteById(UUID id);

    List<E> getAll();
}
