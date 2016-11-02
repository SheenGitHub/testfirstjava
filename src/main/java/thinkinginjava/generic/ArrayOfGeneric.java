package thinkinginjava.generic;

/**
 * Created by Administrator on 2016/4/23.
 */
public class ArrayOfGeneric {
    static final int SIZE = 100;
    static Generic<Integer>[] gia;

    public static void main(String[] args) {
        gia = (Generic<Integer>[])new Generic[SIZE];
        System.out.println(gia.getClass().getSimpleName());
        gia[0] = new Generic<Integer>();
//        gia[1] = new Generic<Double>();
    }
}
