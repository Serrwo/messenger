package pl.pwr.ite.service.impl;

import pl.pwr.ite.mapping.Mapper;
import pl.pwr.ite.model.entity.EntityBase;

public abstract class EntityServiceFacade<E extends EntityBase, D, M extends Mapper<? super E, ? super D>> {
}
