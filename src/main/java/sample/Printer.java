package sample;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class Printer {
    public static <T> void printList(List<T> list) {
        System.out.println("================");
        for (T item : list) {
            System.out.println(item);
        }
        System.out.println();
    }
}
