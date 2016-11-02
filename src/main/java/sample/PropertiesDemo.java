package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Administrator on 2016/7/16.
 */
public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("prop.properties");
        prop.load(fis);

        System.out.println("username:"+prop.getProperty("username"));
        fis.close();

        FileOutputStream fos = new FileOutputStream("prop.properties");
        prop.setProperty("sitename", "sheen.com");
        prop.setProperty("timeout", "300");
        prop.store(fos, "Copyright (c) Boxcode Studio");
        fos.close();
    }
}
