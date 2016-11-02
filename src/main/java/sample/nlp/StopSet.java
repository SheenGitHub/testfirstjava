package sample.nlp;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by Administrator on 2016/7/16.
 */
public class StopSet extends HashSet<String> {
    private static StopSet stopSet = new StopSet();

    public static StopSet getInstance() {
        return stopSet;
    }



}

