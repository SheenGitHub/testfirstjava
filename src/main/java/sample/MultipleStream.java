package sample;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2016/7/6.
 */
public class MultipleStream {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        long uniqueWords = 0;

        try (Stream<String> lines = Files.lines(Paths.get("content.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line ->Arrays.stream(line.split(" ")))
                                    .distinct()
                                    .count();
            System.out.println(uniqueWords);
            }catch (IOException e) {
            e.printStackTrace();
        }

        Stream.iterate(0, n-> n+2)
                .limit(10)
                .forEach(System.out::println);

        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" +t[0] +"," + t[1]+")"));

        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
