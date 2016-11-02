package sample.crawler;

import jdk.nashorn.internal.parser.JSONParser;
import net.sourceforge.htmlunit.corejs.javascript.json.JsonParser;
import netscape.javascript.JSObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Administrator on 2016/7/18.
 */
public class DecondeDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String dename = URLDecoder.decode("History%20will%20tell%20the%20true%20guardian%20of%20South%20China%20Sea%20peace%3A%20People%E2%80%99s%20Daily", "utf-8");
        System.out.println(dename);
    }
}
