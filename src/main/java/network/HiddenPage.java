package network;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/7/11.
 */
public class HiddenPage {
    public static void main(String[] args) throws IOException {
        String s = "金泰妍";
        String searchWord = URLEncoder.encode(s, "utf-8");
        //String searchURL = "http://www.baidu.com/s?wd=" + searchWord;
        String tiebaURL = "http://tieba.baidu.com/f?kw="+searchWord;
        System.out.println(tiebaURL);
        String content = RetrivePage.downloadPage(tiebaURL);
        try(FileChannel fc = new FileOutputStream("data.txt").getChannel()) {
            fc.write(ByteBuffer.wrap(content.getBytes()));
        }
    }
}
