package sample;

/**
 * Created by Administrator on 2016/7/6.
 */
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

import static sample.Dish.menu;
public class ReduceCollector {
    public static void main(String[] args) {
        long howManyDishes = menu.stream().collect(counting());
        System.out.println(howManyDishes);
        long howManyDishes2 = menu.stream().count();
        System.out.println(howManyDishes2);

        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));
        System.out.println(mostCalorieDish.get());

        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(avgCalories);
        IntSummaryStatistics menuStatistic = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistic);

        String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(shortMenu);

        int totalCalories2 = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCalories2);

        Optional<Dish> mostCalorieDish2 = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(mostCalorieDish2.get());

        Stream<Integer> stream =  Arrays.asList(1,2,3,4,5,6).stream();
        List<Integer> numbers = stream.reduce(new ArrayList<Integer>(),
                (List<Integer> l, Integer e) ->{ l.add(e);return l;},
                (List<Integer> l1, List<Integer> l2) ->{ l1.addAll(l2);return l1;});

        int totalCalories3 = menu.stream()
                                            .map(Dish::getCalories)
                                            .reduce(Integer::sum)
                                            .get();
        System.out.println(totalCalories3);

        int totalCalories4 = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(totalCalories4);

        List<Dish> dishes = menu.stream()
                .collect(ArrayList::new,List::add,List::addAll);


    }
}
