package sample;

/**
 * Created by Administrator on 2016/7/4.
 */
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
