package hw9;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams<T> {
    List<T> co;

    private Streams(List<T> list) {
        co = new ArrayList<T>();
        co.addAll(list);
    }

    public static <T> Streams<T> of(List<T> list) {
        return new Streams(list);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        List<T> out = co.stream().filter(predicate).collect(Collectors.toList());
        return new Streams<T>(out);
    }

    public <R> Streams<R> transform(Function<? super T, ? extends R> mapper) {
        List<R> out = co.stream().map(mapper).collect(Collectors.toList());
        return new Streams<R>(out);
    }

    public <K, U> Map<K, U> toMap(Function<? super T, ? extends K> keyMapper,
                                  Function<? super T, ? extends U> valueMapper) {
        return co.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }
}
