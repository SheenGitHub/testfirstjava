package thinkinginjava.generic.coffee;

/**
 * Created by Administrator on 2016/4/22.
 */
public class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " "+ id;
    }
}
