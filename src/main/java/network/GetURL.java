package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/4/6.
 */
public class GetURL {
    public static String curl = "http://115.28.194.148";
    public static void getURL(String u) {
        URL url;
        InputStreamReader isr;
        InputStream is;
        BufferedReader r;
        String str;

        try {
            System.out.println("Reading URL: " + u);
            url = new URL(u);
            is = url.openStream();
            isr = new InputStreamReader(is);
            r = new BufferedReader(isr);
            do {
                str = r.readLine();
                if (str != null) {
                    System.out.println(str);
                }
            } while (str != null);

        } catch (MalformedURLException e) {
            System.out.println("Must enter a valid URL");
        } catch (IOException e) {
            System.out.println("Can't connect");
        }

    }

    public static void main(String[] args) {
        getURL(curl);
    }
}

