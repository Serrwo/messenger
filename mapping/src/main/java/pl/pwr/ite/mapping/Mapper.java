package pl.pwr.ite.mapping;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public interface Mapper<S, D> {

    Class<S> getSourceType();

    Class<D> getDestinationType();

    D createDestination(S source);

    <T> T createDestination(Class<T> destinationType);

    D map(S source);

    void map(S source, D destination);

    <Sx, Dx> void map(Consumer<Dx> consumer, Sx source, Mapper<Sx, Dx> mapper);

    <Sx, Dx> void map(Consumer<Dx[]> setter, Iterable<Sx> source, Mapper<Sx, Dx> mapper);

    List<D> map(Iterable<S> source);

    void transform(S source, D destination);
}
