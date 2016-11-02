package sample;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static sample.Dish.menu;
import static java.util.Comparator.comparing;

/**
 * Created by Administrator on 2016/7/6.
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> lowCaloricDishesName =
                        menu.stream()
                        .filter(d -> d.getCalories() < 400)
                        .sorted(comparing(Dish::getCalories))
                        .map(Dish::getName)
                        .collect(Collectors.toList());

        Printer.printList(lowCaloricDishesName);

        Map<Dish.Type, List<Dish>> dishesByType =
                                menu.stream().collect(groupingBy(Dish::getType));

        System.out.println(dishesByType);
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);

        String[] words = {"Hello", "World"};

        Arrays.asList(words).stream()
                .map(word->word.split(""))
                .distinct()
                .forEach(System.out::println);

        Arrays.asList(words).stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .forEach(System.out::println);

        Arrays.asList(words).stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream().flatMap(i -> numbers2.stream().filter(j->(i + j)%3 == 0).map(j -> new int[]{i, j})).collect(Collectors.toList());
        Printer.printList(pairs);

        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d-> System.out.println(d.getName()));

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream().map(x -> x*x)
                                        .filter(x -> x % 3 == 0)
                                        .findFirst();
        firstSquareDivisibleByThree.ifPresent(System.out::println);

        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(product);

        int sum1 = someNumbers.stream().reduce(0, Integer::sum);

        int count = menu.stream()
                            .map(d -> 1)
                            .reduce(0, (a,b)->a+b);

        Optional<Integer> max = someNumbers.stream().reduce(Integer::max);
        max.ifPresent(System.out::println);

        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(calories);

        IntStream evenNumber = IntStream.rangeClosed(1,100)
                                                    .filter(n -> n % 2 == 0);
        System.out.println(evenNumber.count());

        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1,100).boxed()
                        .flatMap(a ->
                            IntStream.rangeClosed(a,100)
                                .filter(b -> Math.sqrt(a*a + b*b)%1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a*a +b*b)})
                        );
        pythagoreanTriples.limit(10)
                .forEach(t -> System.out.println(t[0]+", "+t[1]+", "+t[2]));

        Stream<double[]> pythagoreanTriples2 =
                IntStream.range(1,100).boxed()
                        .flatMap(a ->
                            IntStream.rangeClosed(a,100)
                                .mapToObj(b -> new double[]{a, b,Math.sqrt(a*a +b*b)})
                                .filter(t -> t[2]%1 == 0)
                        );

        pythagoreanTriples2.limit(5)
                .forEach(t -> System.out.println(t[0]+", "+t[1]+", "+t[2]));

        IntSupplier fib = new IntSupplier() {
            private int previous = 1;
            private int current = 2;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);


    }
}
