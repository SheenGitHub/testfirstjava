package thinkinginjava.generic.coffee;

import thinkinginjava.util.Generator;

/**
 * Created by Administrator on 2016/4/22.
 */
public class Fibonacci implements Generator<Integer> {
    private int count = 0;

    private int fib(int n) {
        if( n < 2) return 1;
        return fib(n-2) + fib(n -1);
    }

    @Override
    public Integer next() {
        return null;
    }

}
