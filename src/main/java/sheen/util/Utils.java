package sheen.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/7/29.
 */
public class Utils {
    public static Map<String, String> readMapFromFile(String inputfile) throws IOException {
        Pattern p = Pattern.compile("^\\s*(\\S*)\\s*(\\S*)");
        Matcher m;

        BufferedReader in = new BufferedReader(new FileReader(inputfile));
        Map<String,String> map = new HashMap<>();
        String s,column, type;
        while((s = in.readLine()) != null){
            m = p.matcher(s);

            if(m.find()){
                column = m.group(1);
                type = m.group(2);
                map.put(column, type);
            }

        }
        in.close();
        return map;
    }

}
