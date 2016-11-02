package sample;

import java.util.function.Function;

/**
 * Created by Administrator on 2016/7/6.
 */
public class Letter {
    public static String addHeader(String text) {
        return "From Raoul, Mario and Alan: " +text;
    }

    public static String addFooter(String text) {
        return text + " Kind regards";
    }

    public static String checkSpelling(String text) {
        return text.replace("labda", "lambda");
    }

    public static void main(String[] args) {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline =
                addHeader.andThen(Letter::addFooter)
                        .andThen(Letter::checkSpelling);

        System.out.println(transformationPipeline.apply(" Ilabda"));

    }
}
