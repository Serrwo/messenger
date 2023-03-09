package pl.pwr.ite.mapping;

import pl.pwr.ite.utils.GenericHelper;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public abstract class MapperBase<S, D> implements Mapper<S, D> {

    private final Class<S> sourceType;

    private final Class<D> destinationType;

    public MapperBase() {
        this.sourceType = GenericHelper.getArgumentType(getClass(), "S");
        this.destinationType = GenericHelper.getArgumentType(getClass(), "D");
    }

    @Override
    public Class<S> getSourceType() {
        return sourceType;
    }

    @Override
    public Class<D> getDestinationType() {
        return destinationType;
    }

    @Override
    public D createDestination(S source) {
        return createDestination(destinationType);
    }

    @Override
    public <T> T createDestination(Class<T> destinationType)  {
        try {
            return (T)destinationType.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new IllegalStateException(String.format("Could not create destination instance"), ex);
        }
    }

    @Override
    public D map(S source) {
        if (source == null) {
            return null;
        }
        var destination = createDestination(source);
        transform(source, destination);
        return destination;
    }

    @Override
    public void map(S source, D destination) {
        transform(source, destination);
    }

    @Override
    public <Sx, Dx> void map(Consumer<Dx> consumer, Sx source, Mapper<Sx, Dx> mapper) {
        var destination = source != null ? mapper.createDestination(source) : null;
        if (source != null) {
            mapper.transform(source, destination);
        }
        consumer.accept(destination);
    }

    @Override
    public <Sx, Dx> void map(Consumer<Dx[]> setter, Iterable<Sx> source, Mapper<Sx, Dx> mapper) {
        Collection<Dx> mapped = mapper.map(source);
        Dx[] array = mapped.toArray((Dx[]) Array.newInstance(mapper.getDestinationType(), 0));
        setter.accept(array);
    }

    @Override
    public List<D> map(Iterable<S> source) {
        if(source == null) {
            return null;
        }
        List<D> mapped = new ArrayList<>();
        source.forEach(e -> mapped.add(map(e)));
        return mapped;
    }
}
