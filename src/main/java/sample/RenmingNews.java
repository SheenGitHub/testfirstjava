package sample;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/7/15.
 */
public class RenmingNews {
    public static void main(String[] args) throws IOException {
        WebClient wc = new WebClient(BrowserVersion.FIREFOX_45);
        WebClientOptions options = wc.getOptions();
        options.setJavaScriptEnabled(true);
        options.setCssEnabled(false);
        options.setUseInsecureSSL(true);
        options.setThrowExceptionOnFailingStatusCode(false);
        wc.getCookieManager().setCookiesEnabled(true);
        wc.setAjaxController(new NicelyResynchronizingAjaxController());
        options.setThrowExceptionOnScriptError(false);
        options.setTimeout(30_000);

//        HtmlPage page = wc.getPage("http://en.people.cn/n3/2016/0715/c90000-9086529.html");
        HtmlPage page = wc.getPage("http://disqus.com/embed/comments/?base=default&version=903c42d8cc0fc262b4dc784af26d8041&f=enpeople&t_u=http%3A%2F%2Fen.people.cn%2Fn3%2F2016%2F0718%2Fc90000-9087298.html");
        String pageXml = page.asXml();
        FileChannel fc = new FileOutputStream("renming.txt").getChannel();

        fc.write(ByteBuffer.wrap(pageXml.getBytes()));

    }

    private static void crawlerComment(String linkHref) {

    }
}
