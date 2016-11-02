package sample;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.persist.*;
import com.sleepycat.persist.model.PrimaryKey;

/**
 * Created by Administrator on 2016/7/13.
 */
public class ToDoTaskList {
    EntityStore store;
    PrimaryIndex<String,NewsSource> newsByURL;
    SecondaryIndex<Integer,String,NewsSource> secondaryIndex;


    public ToDoTaskList(Environment env) throws DatabaseException {
        StoreConfig storeConfig = new StoreConfig();
        storeConfig.setAllowCreate(true);
        storeConfig.setTransactional(false);
        store = new EntityStore(env, "classDb", storeConfig);
        newsByURL = store.getPrimaryIndex(String.class,NewsSource.class);
        secondaryIndex = store.getSecondaryIndex(newsByURL,Integer.class,"store");

    }

    public NewsSource removeBest() throws DatabaseException {
        Integer score = secondaryIndex.sortedMap().lastKey();
        if (score != null) {
            EntityIndex<String, NewsSource> urlList = secondaryIndex.subIndex(score);
            EntityCursor<String> ec = urlList.keys();
            String url = ec.first();
            ec.close();
            NewsSource source = urlList.get(url);
            urlList.delete(url);
            return source;
        }
        return null;
    }
}
