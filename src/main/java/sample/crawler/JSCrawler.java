package sample.crawler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Administrator on 2016/7/15.
 */
public class JSCrawler {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("prop.properties");
        prop.load(fis);
        String email = prop.getProperty("username");
        String pwd = prop.getProperty("fbpass");
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        HtmlPage page = webClient.getPage("http://www.facebook.com");
        HtmlTextInput emailInput = (HtmlTextInput) page.getElementById("email");
        emailInput.setValueAttribute(email);
        HtmlPasswordInput passInput = (HtmlPasswordInput) page.getElementById("pass");
        passInput.setValueAttribute(pwd);
        HtmlSubmitInput submitInput = (HtmlSubmitInput)page.getElementById("u_0_m").getFirstChild();
        HtmlPage mainPage = submitInput.click();
        webClient.waitForBackgroundJavaScript(10_000);
        String pageAsXml = mainPage.asXml();
        System.out.println(pageAsXml);




    }
}
/*
WebClient wc = new WebClient(BrowserVersion.CHROME);
        WebClientOptions options = wc.getOptions();
        options.setJavaScriptEnabled(true);
        options.setCssEnabled(false);
        options.setUseInsecureSSL(true);
        options.setThrowExceptionOnFailingStatusCode(false);
        wc.getCookieManager().setCookiesEnabled(true);
        wc.setAjaxController(new NicelyResynchronizingAjaxController());
        options.setThrowExceptionOnScriptError(false);
        options.setTimeout(30_000);
        HtmlPage page = wc.getPage("http://en.people.cn/n3/2016/0715/c90000-9086529.html");
        String pageXml = page.asXml();
 */
