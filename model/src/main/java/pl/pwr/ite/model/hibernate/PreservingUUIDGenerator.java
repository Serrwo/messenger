package pl.pwr.ite.model.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;
import pl.pwr.ite.model.entity.EntityBase;

import java.io.Serializable;
import java.util.UUID;

public class PreservingUUIDGenerator extends UUIDGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if(object instanceof EntityBase) {
            EntityBase entityBase = (EntityBase) object;
            UUID id = entityBase.getId();
            if(id != null) {
                return id;
            }
        }
        return super.generate(session, object);
    }
}
