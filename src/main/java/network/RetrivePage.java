package network;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/5/30.
 */
public class RetrivePage {
    private static final int BSIZE = 1024;
    private static String host = "www.lietu.com";
    private static String file = "/index.jsp";
    private static int port = 80;

    public static String downloadPage(String path) throws IOException {
        URL pageUrl = new URL(path);
        System.out.println(getHeadField(pageUrl,"Content-Type"));
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(pageUrl.openStream()))){
                String line;
                StringBuilder pageBuffer = new StringBuilder();
                while ((line = reader.readLine()) != null){
                        pageBuffer.append(line+"\n");
                }
            return pageBuffer.toString();
        }catch (IOException e) {
            e.printStackTrace();
        } ;
        return null;
    }

    public static String getHeadField(URL url,String fieldKey) throws IOException {
        URLConnection con = url.openConnection();
        Map<String, List<String>> header = con.getHeaderFields();

        Iterator i = header.keySet().iterator();
        String key = null;
        while (i.hasNext()) {
            key = (String)i.next();
            if (key == null) {
                if (fieldKey == null) {
                    return (String) ((List) (header.get(null))).get(0);
                }
            } else {
                if (key.equalsIgnoreCase(fieldKey)) {
                    return (String) ((List) (header.get(key))).get(0);
                }
            }
        }
        return null;
    }

    public static void httpClientDemo() {

        try(CloseableHttpClient httpClient = HttpClients.createDefault();) {
            HttpGet httpGet = new HttpGet("http://www.baidu.com/");
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                System.out.println(EntityUtils.toString(entity, "utf-8"));
                EntityUtils.consume(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void updateWeb() throws IOException {
        Socket s = new Socket(host, port);
        String file = "/";

        OutputStream out = s.getOutputStream();
        PrintWriter outw = new PrintWriter(out, false);

        outw.print("HEAD " + file + " HTTP/1.0\r\n");
        outw.print("If-Modified-Since: Tue, 31 May 2016 00:00:00 GMT\r\n");
        outw.print("\r\n");
        outw.flush();

        InputStream in = s.getInputStream();
        InputStreamReader inr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(inr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

//    public static void tryConnection() throws IOException {
//        URL u = new URL("http://www.qq.com");
//        HttpURLConnection http = (HttpURLConnection) u.openConnection();
//        http.setRequestMethod("GET");
//        BufferedReader br = new BufferedReader(new InputStreamReader(u.openStream()));
//        FileChannel outChannel = new FileOutputStream("urlfrontier.txt").getChannel();
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        Document doc = Jsoup.parse(u.openStream(),"gb2312","/");
//        Elements links = doc.select("a[href]");
//        StringBuilder sb = new StringBuilder();
//        for (Element link : links) {
//            String linkHref = link.attr("href");
//            String title = link.text().trim();
//            sb.append(title).append("\n").append(linkHref).append("\n");
//            System.out.println(linkHref);
//            System.out.println(title);
//        }
//        outChannel.write(ByteBuffer.wrap(sb.toString().getBytes()));
////        String line;
////        while ((line = br.readLine()) != null) {
////            System.out.println(line);
////        }
//        System.out.println(u + " 更新时间 " + new Date(http.getLastModified()));
//    }


    public static void main(String[] args) throws IOException {
        FileChannel fileChannel = new FileOutputStream(new File("retrivePage.txt")).getChannel();
        fileChannel.write(ByteBuffer.wrap(downloadPage("http://en.people.cn/n3/2016/0715/c90000-9086529.html").getBytes()));
        httpClientDemo();
//            updateWeb();

    }


    public static boolean isFutileImage(String imgsrc) {
        return imgsrc.contains("head_")
                || imgsrc.contains("image_emoticon")
                || imgsrc.contains("png")
                ||imgsrc.contains("h.hiphotos")
                ||imgsrc.contains("tb.himg");
    }




}
