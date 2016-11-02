package sample;

import com.sleepycat.je.Environment;
import com.sleepycat.persist.StoreConfig;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

/**
 * Created by Administrator on 2016/7/13.
 */
@Entity
public class NewsSource {
    @PrimaryKey
    public String URL;
    public String source;
    public int level;
    public int rank;
    public String urlDesc = null;
    @SecondaryKey(relate = Relationship.MANY_TO_ONE)
    public int score;

}
