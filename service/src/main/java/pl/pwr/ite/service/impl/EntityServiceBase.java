package pl.pwr.ite.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.ite.model.entity.EntityBase;
import pl.pwr.ite.service.EntityService;

import java.util.List;
import java.util.UUID;

public abstract class EntityServiceBase<E extends EntityBase> implements EntityService<E> {

    private final JpaRepository<E, UUID> repository;

    protected EntityServiceBase(JpaRepository<E, UUID> repository) {
        this.repository = repository;
    }

    @Override
    public E findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public <T extends E> T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public <T extends E> T saveAndFlush(T entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(E entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }
}
