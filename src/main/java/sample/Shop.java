package sample;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Administrator on 2016/7/9.
 */
public class Shop {
    private final String name;
    private final  double longtitude;
    private final double latitude;

    public String getName() {
        return name;
    }

    public Shop(String name, double longtitude, double latitude) {
        this.name = name;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }


    public double getLongtitude() {
        return longtitude;
    }

    public double getLatitude() {
        return latitude;
    }



    public static double distance(double longtitude, double latitude,Shop shop) {
        return Math.sqrt((longtitude - shop.latitude) *(latitude -shop.longtitude)
                + (latitude - shop.latitude)*(latitude - shop.latitude));
    }

    public double calDistance(Shop shop) {
        return Math.sqrt((longtitude - shop.latitude) *(latitude -shop.longtitude)
                + (latitude - shop.latitude)*(latitude - shop.latitude));
    }

    public static void main(String[] args) {
        List<Shop> shops = Arrays.asList(new Shop("Liang",3,5),
                new Shop("Yang",6,8),
                new Shop("Gong",34,56),
                new Shop("Jin",23,14),
                new Shop("Zhou",15,23),
                new Shop("Bi",24,16));
            Stream<Shop[]> result = shops.stream()
                        .flatMap(a ->
                                shops.stream()
                                        .filter(b -> shops.indexOf(b) > shops.indexOf(a) &&b.calDistance(a) < 5)
                                        .map(b -> { Shop[] c = new Shop[2];c[0] = a;c[1] =b;return c;})
                );

        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1,100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a,100)
                                    .filter(b -> Math.sqrt(a*a + b*b)%1==0)
                                    .mapToObj(b -> new int[]{a,b,(int)Math.sqrt(a*a + b*b)})

                        );



//        shops.stream()
//                .filter(a -> Shop.distance())
        System.out.println(findByDistance(4,4));
    }

    public static List<Shop> findByDistance(double longitude,double latitude) {
        List<Shop> shops = Arrays.asList(new Shop("Liang",3,5),
                new Shop("Yang",6,8),
                new Shop("Gong",34,56),
                new Shop("Jin",23,14),
                new Shop("Zhou",15,23),
                new Shop("Bi",24,16));
    return shops.stream()
                .filter(a -> Shop.distance(longitude,latitude,a) < 5)
                .collect(toList());
//
    }

    public static List<Shop> takeWhile(List<Shop> list, Shop shop) {
        int i = 0;
        for (Shop item : list) {
                if(item.getName().equals(shop.getName()))
                    return list.subList(i,list.size());
                        i++;
        }
        return list;
    }


}
