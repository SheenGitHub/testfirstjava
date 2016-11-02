package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by Administrator on 2016/7/4.
 */
public class ReadTwoLine {
    public static String processFile(BufferedReaderProcessor p) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("content.txt"))){
            return p.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        System.out.println(oneLine);

        String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(twoLines);
        IntPredicate evenNumbers = (int i ) -> i%2 == 0;
        evenNumbers.test(1000);

        Supplier<Apple> supplier = () -> new Apple("yellow", 100);
        supplier.get();
    }
}
