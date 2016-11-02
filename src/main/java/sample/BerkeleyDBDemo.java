package sample;

import com.sleepycat.bind.serial.ClassCatalog;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.je.*;

import java.io.File;
import java.util.SortedMap;

/**
 * Created by Administrator on 2016/7/13.
 */
public class BerkeleyDBDemo {
    Environment environment;
    ClassCatalog catalog;
    private Database store;

    public BerkeleyDBDemo(File envDir) throws DatabaseException {
        EnvironmentConfig environmentConfig = new EnvironmentConfig();
        environmentConfig.setTransactional(false);
        environmentConfig.setAllowCreate(true);
        environment = new Environment(envDir, environmentConfig);
    }

    public static void main(String[] args) throws DatabaseException {
        String dir = "db";
        EnvironmentConfig environmentConfig = new EnvironmentConfig();
        environmentConfig.setTransactional(false);
        environmentConfig.setAllowCreate(true);
        Environment env = new Environment(new File(dir), environmentConfig);

        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setTransactional(false);

        databaseConfig.setAllowCreate(true);

        Database catalogDb = env.openDatabase(null, "catalog", databaseConfig);
        ClassCatalog classCatalog = new StoredClassCatalog(catalogDb);

        TupleBinding keyBinding = TupleBinding.getPrimitiveBinding(String.class);

        SerialBinding dataBinding = new SerialBinding(classCatalog, String.class);

        Database db = env.openDatabase(null, "url", databaseConfig);

        SortedMap<String, String> map = new StoredSortedMap(db, keyBinding, dataBinding, true);

        String url = "http://www.lietu.com";
        map.put(url, null);
        if (map.containsKey(url)) {
            System.out.println("Been Crawled");
        }

    }

    public void createDb() throws DatabaseException {
        String databaseName = "ToDoTaskList.db";
        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setAllowCreate(true);
        databaseConfig.setTransactional(false);

        databaseConfig.setSortedDuplicates(false);
        Database myClassDb = environment.openDatabase(null, "classDb", databaseConfig);
        SerialBinding serialBinding = new SerialBinding(catalog, NewsSource.class);
        store = environment.openDatabase(null, databaseName, databaseConfig);
        store.close();
        myClassDb.close();
    }

    public void freeEnv() throws DatabaseException {
        environment.sync();
        environment.close();
        environment = null;
    }



}
