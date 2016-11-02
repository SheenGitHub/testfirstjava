package thinkinginjava.util;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/22.
 */
public class TupleList<A,B,C, D> {
    public static void main(String[] args) {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2);
    }
}
