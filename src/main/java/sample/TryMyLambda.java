package sample;

import java.lang.reflect.AnnotatedType;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Created by Administrator on 2016/7/4.
 */
public class TryMyLambda{
    Integer value;

    public TryMyLambda(Integer value) {
        this.value = value;
    }

    public void listInteger(IntegerFilter filter) {
            if (filter.accept(value)) {
                System.out.println(value);
            }

    }

    public static boolean isGreaterThanZero(Integer ivalue) {
        System.out.println("greater than 0;");
        return ivalue.compareTo(0) == 1;
    }

    public static boolean isGreaterThanTwenty(Integer ivalue) {
        System.out.println("greater than 20;");
        return ivalue.compareTo(20) == 1;
    }

    public static void main(String[] args) {
        TryMyLambda tryMyLambda = new TryMyLambda(20);
        System.out.println("output means greater than 0");
        tryMyLambda.listInteger(TryMyLambda::isGreaterThanZero);
        System.out.println("output means greater than 20");
        tryMyLambda.listInteger(TryMyLambda::isGreaterThanTwenty);
        List<Apple> inventory = new ArrayList<>();
        filterApples(inventory, Apple::isGreenApple);
        filterApples(inventory, Apple::isHeavyApple);
        filterApples(inventory, (Apple a) -> a.getWeight() < 80 || "brown".equals(a.getColor()));
        filterApples(inventory,(Apple a) ->a.getWeight()<150);

        List<Apple> heavyApples = inventory.stream()
                                                                    .filter((Apple a) -> a.getWeight() > 150)
                                                                    .collect(Collectors.toList());
        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);
        inventory.sort(comparing(Apple::getWeight));

        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();
        System.out.println(a1);

        Function<Integer,Apple> c2 = Apple::new;
        Apple a2 = c2.apply(200);
        System.out.println(a2);

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);

        Printer.printList(apples);

        BiFunction<String, Integer, Apple> c3 = Apple::new;
        Apple a3 = c3.apply("yellow", 300);
        System.out.println(a3);

        List<Apple> stores = Arrays.asList(c3.apply("yellow",200),
                                                                c3.apply("green",400),
                                                                c3.apply("black",300),
                                                                c3.apply("red",300),
                                                                c3.apply("blue",250));
        stores.sort(comparing(Apple::getWeight)
                            .reversed()
                            .thenComparing(Apple::getColor));
        Printer.printList(stores);

        Function<Integer, Integer> f = x -> x+1;
        Function<Integer, Integer> g = x -> x*2;
        Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> j = f.compose(g);
        System.out.println(h.apply(4));
        System.out.println(j.apply(4));

    }



    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> function) {
        List<Apple> result = new ArrayList<>();
        for (Integer e : list) {
            result.add(function.apply(e));
        }
        return  result;
    }



    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

}

interface IntegerFilter {
    public boolean accept(Integer integer);
}
