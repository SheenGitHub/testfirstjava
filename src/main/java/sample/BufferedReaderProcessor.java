package sample;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Administrator on 2016/7/4.
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}
