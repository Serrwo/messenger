package pl.pwr.ite.service.impl;

import pl.pwr.ite.mapping.Mapper;
import pl.pwr.ite.model.entity.EntityBase;
import pl.pwr.ite.service.EntityService;

import java.util.Collection;

public abstract class EntityServiceFacade<E extends EntityBase, D, S extends EntityService,M extends Mapper<? super E, ? super D>> {

    protected final S service;

    protected final M mapper;

    public EntityServiceFacade(S service, M mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public D map(E entity) {
        return (D) this.mapper.map(entity);
    }

    public E save(E entity) {
        return (E) service.save(entity);
    }

    public E saveAndFlush(E entity) {
        return (E) service.saveAndFlush(entity);
    }

    public Collection<D> map(Collection<E> entities) {
        return (Collection<D>) this.mapper.map(entities);
    }

    public Collection<D> listAllDto() {
        return (Collection<D>) this.mapper.map(service.getAll());
    }
}
