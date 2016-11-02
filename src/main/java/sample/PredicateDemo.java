package sample;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.ToIntBiFunction;

/**
 * Created by Administrator on 2016/7/4.
 */
public class PredicateDemo {
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static <T> void printList(List<T> list) {
        for (T e : list) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple("green", 80),
                new Apple("red", 120),
                new Apple("green", 150));
        List<Apple> redApples = filter(inventory, (Apple a) -> "red".equals(a.getColor()));
        printList(redApples);

        List<Integer> numbers = Arrays.asList(15, 14, 23, 25, 3, 11, 17, 18, 20, 33);
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
        printList(evenNumbers);
        PrivilegedAction<Integer> p = ()->42;
        Callable<Integer> c = ()->42;
        Comparator<Apple> c1 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        ToIntBiFunction<Apple,Apple> c2 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
    }
}
