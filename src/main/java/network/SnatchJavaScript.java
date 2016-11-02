package network;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;

/**
 * Created by Administrator on 2016/7/11.
 */
public class SnatchJavaScript {
    public static void main(String[] args) throws ScriptException, IOException {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
//        String s = "var x;\n" +
//                "document.write(\"<p>只有 17 位: \");\n" +
//                "x=12345678901234567890;\n" +
//                "document.write(x + \"</p>\");\n" +
//                "\n" +
//                "document.write(\"<p>0.2 + 0.1 = \");\n" +
//                "x=0.2+0.1;\n" +
//                "document.write(x + \"</p>\");\n" +
//                "\n" +
//                "document.write(\"<p>可分别乘以 10 并除以 10 : \");\n" +
//                "x=(0.2*10+0.1*10)/10;\n" +
//                "document.write(x +\"</p>\");";
//        engine.eval(s);
        WebClient webClient = new WebClient();
        String url = "http://www.lietu.com/lab/location.html";
        HtmlPage page = (HtmlPage) webClient.getPage(url);
        System.out.println(page);
        DomNodeList<DomNode> nodeList = page.getChildNodes();
        for (Object node : nodeList) {
            HtmlElement n = (HtmlElement)node;
            extract(n);
        }
    }

    public static void extract(HtmlElement node) throws IOException {
        if ("input".equals(node.getNodeName())) {
            HtmlInput inputHtml = (HtmlInput)node;
            HtmlPage newPage = inputHtml.click();
            System.out.println(newPage.toString());
        }

        DomNode childNode = node.getFirstChild();

        if (childNode instanceof HtmlElement) {
            HtmlElement child = (HtmlElement)childNode;
            while (child != null) {
                if (child == childNode) {
                    extract(child);

                }
                childNode = child.getNextSibling();
                if (child == null) {
                    break;
                }
                if (childNode instanceof HtmlElement) {
                    child = (HtmlElement) childNode;
                }
            }
        }
    }
}
