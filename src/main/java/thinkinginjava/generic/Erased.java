package thinkinginjava.generic;

/**
 * Created by Administrator on 2016/4/23.
 */
public class Erased<T> {
    private static final int SIZE = 100;

    public void f(Object arg) {
        T[] array = (T[])new Object[SIZE];

    }
}
