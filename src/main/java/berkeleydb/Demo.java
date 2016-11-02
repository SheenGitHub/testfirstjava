package berkeleydb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2016/7/16.
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        String pageUrl ="http://en.people.cn/n3/2016/0718/c90000-9087298.html";
        String commentURL = getCommentURL(pageUrl);
       URL url = new URL(commentURL);
        Document doc = Jsoup.parse(url.openStream(),"utf-8","/");
        Element element = doc.select("script#disqus-threadData").first();
        System.out.println(element);
        String result = element.toString();
        String Json = result.substring(result.indexOf("{"), result.lastIndexOf("}")+1);
        System.out.println(Json);

    }

    private static String getCommentURL(String pageUrl) throws UnsupportedEncodingException {
        String baseUrl = "http://disqus.com/embed/comments/?";
        String defaultParams = "base=default&version=903c42d8cc0fc262b4dc784af26d8041&f=enpeople&t_u=";
        String location = URLEncoder.encode(pageUrl, "utf-8");
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl)
            .append(defaultParams)
            .append(location);
        return sb.toString();
    }
}
