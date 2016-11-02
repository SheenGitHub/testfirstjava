package network;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/6/1.
 */
public class GetWebData {
    public static void main(String[] args) throws IOException {
        String host = "www.baidu.com";
        String file = "/";
        int port = 80;

        Socket s = new Socket(host, port);
        OutputStream out = s.getOutputStream();
        PrintWriter outw = new PrintWriter(out, false);
        outw.print("GET " + file + " HTTP/1.0\r\n");
        outw.print("Accept:  text/plain,  text/html,  text/*\r\n");
        outw.print("Range: bytes=0-500\r\n");
        outw.print("\r\n");
        outw.flush();

        InputStream in = s.getInputStream();
        InputStreamReader inr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(inr);
        String line;
        StringBuilder sb = new StringBuilder();
        FileChannel fileChannel = new FileOutputStream(new File("retrivePage.txt")).getChannel();

        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        fileChannel.write(ByteBuffer.wrap(sb.toString().getBytes()));
    }
}
