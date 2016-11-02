package sample;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static sample.Dish.*;
/**
 * Created by Administrator on 2016/7/6.
 */
public class GroupStreamDemo {
    public enum CaloricLevel {DIET, NORMAL, FAT,}

    public static void main(String[] args) {
        Function<Dish,CaloricLevel> classification = (Dish dish) -> {
            if(dish.getCalories()<=400) return CaloricLevel.DIET;
            else if(dish.getCalories() <=700) return CaloricLevel.NORMAL;
            else  return CaloricLevel.FAT;
        };
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
            menu.stream().collect(groupingBy(classification));


        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(
                        groupingBy(Dish::getType, groupingBy(classification))
                );
        for (Map.Entry<Dish.Type, Map<CaloricLevel, List<Dish>>> entry : dishesByTypeCaloricLevel.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println("*****************************");
            for (Map.Entry<CaloricLevel, List<Dish>> entries : entry.getValue().entrySet()) {
                System.out.println(entries.getKey());
                Printer.printList(entries.getValue());
            }
        }

        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting())
        );


        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream().collect(
                        groupingBy(Dish::getType,
                                maxBy(comparing(Dish::getCalories)))
                );


        Map<Dish.Type, Dish> mostCaloricByType2 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories))
                        ,Optional::get)
                        ));

        Map<Dish.Type, Integer> totalCaloriesByType =
                menu.stream().collect(groupingBy(Dish::getType,
                        summingInt(Dish::getCalories)));

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(
                        groupingBy(Dish::getType,
                                mapping(classification,toSet()))
                );


        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType2 =
                menu.stream().collect(
                        groupingBy(Dish::getType,
                                mapping(classification,toCollection(HashSet::new)))
                );

        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType))
                );
        System.out.println(vegetarianDishesByType);

        Map<Boolean, Dish> mostCaloricPartionedByVegetarian =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                collectingAndThen(maxBy(comparingInt(Dish::getCalories))
                                ,Optional::get))
                );
        System.out.println(mostCaloricPartionedByVegetarian);
    }





}
