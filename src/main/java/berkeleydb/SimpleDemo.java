package berkeleydb;

import com.sleepycat.je.*;
import java.io.File;
import java.util.function.Consumer;

/**
 * Created by Administrator on 2016/7/16.
 */
public class SimpleDemo {
    static Consumer<Environment> envClose = t->{
        if (null != t) {
            try {
                t.cleanLog();
                t.close();
                t = null;
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }
    };
    public static void testEnvironment(String envHomePath) {
        Environment mydbEnv = null;
        System.out.println(" ---&gt; EnvironmentConfig init");
        EnvironmentConfig envCfg = new EnvironmentConfig();
        envCfg.setAllowCreate(true);
        envCfg.setCacheSize(1024 * 1024 * 20);
        envCfg.setTransactional(true);


        try {
            System.out.println(" ---&gt; Environment init");
            mydbEnv = new Environment(new File(envHomePath), envCfg);
            System.out.println(" ---&gt; Env Config: " + mydbEnv.getConfig());
            System.out.println(" ---&gt; Env Home: " + mydbEnv.getHome().getAbsolutePath());
        } catch (DatabaseException e) {
            e.printStackTrace();
        }finally {

        }
    }

    public static void testDatabase(String envHomePath) {
        Environment mydbEnv = null;
        String dbName1 = "micmiu-michael";
        String dbName2 = "micmiu-sun";

        Database mydatabase1 = null;
        Database mydatabase2 = null;
        System.out.println(" ----&gt; Environmentconfig init");
        EnvironmentConfig envCfg = new EnvironmentConfig();
        envCfg.setAllowCreate(true);
        envCfg.setCacheSize(1024 * 1024 * 20);
        envCfg.setTransactional(true);


        try {
            System.out.println(" ----&gt; Environment init");
            mydbEnv = new Environment(new File(envHomePath), envCfg);

            System.out.println(" ----&gt; DatabaseConfig init");
            DatabaseConfig dbCfg = new DatabaseConfig();
            dbCfg.setAllowCreate(true);
            dbCfg.setTransactional(true);

            System.out.println(" ----&gt; openDatabase:name is micmiu-michael");
            mydatabase1 = mydbEnv.openDatabase(null, dbName1, dbCfg);
            System.out.println(" ----&gt; name: " + mydatabase1.getDatabaseName());
            System.out.println(" ----&gt; config: " + mydatabase1.getConfig());

            System.out.println(" ----&gt; openDatabase:name is micmiu-sun");
            mydatabase2 = mydbEnv.openDatabase(null, dbName2, dbCfg);
            System.out.println(" ----&gt; name: " + mydatabase2.getDatabaseName());
            System.out.println(" ----&gt; config: " + mydatabase2.getConfig());

        } catch (DatabaseException e) {
            e.printStackTrace();
        }finally {
            try {
             if(null != mydatabase1)
                mydatabase1.close();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }

            try {
                if(null != mydatabase2)
                mydatabase2.close();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            envClose.accept(mydbEnv);

        }
    }

    public static void main(String[] args) {
        System.out.println(" === &gt; Demo Test Start &lt; =====");
        String envHomePath = "D:/test/berkeleydata";
        SimpleDemo.testEnvironment(envHomePath);
        System.out.println(" ===== &gt; Demo Test End &lt; =====");
    }
}
