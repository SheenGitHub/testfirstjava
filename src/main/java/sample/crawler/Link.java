package sample.crawler;

import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import java.util.HashSet;

/**
 * Created by Administrator on 2016/7/15.
 */
public class Link {
    @PrimaryKey
    public String fromURL;
    @SecondaryKey(relate = Relationship.MANY_TO_MANY)
    public HashSet<String> toURL = new HashSet<>();

}
