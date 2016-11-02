package sample.crawler;

import org.apache.http.cookie.Cookie;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sheen.util.Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/29.
 */
public class SinaCrawler {
    public static void main(String[] args) throws IOException {
        String weibo = "weibo.com";
        Map cookies = Utils.readMapFromFile("d:/Downloads/cookie.txt");

        System.out.println(cookies);
        Connection.Response res = Jsoup.connect("http://www.weibo.com")
                                                    .timeout(10000)
                                                    .execute();

        Document doc = res.parse();
        String sid = res.cookie("long_sid_t");
        System.out.println("session:"+sid);

        FileChannel fc = new FileOutputStream("sina.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fc.write(ByteBuffer.wrap(doc.body().toString().getBytes()));

    }


}
