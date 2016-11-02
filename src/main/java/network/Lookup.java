package network;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by Administrator on 2016/3/31.
 * @author Sheen
 * @version 1.0
 *
 */
public class Lookup {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        useProxy();
    }

    private static void resolveHost(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println("Call with one parameter that specifies the host to lookup.");
            } else {
                InetAddress address = InetAddress.getByName(args[0]);

                System.out.println(address);
            }
        } catch (UnknownHostException e) {
            System.out.println("Could not find " + args[0]);
        }
    }

    public static void login(String url,String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accpet","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);

            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void useProxy() {
        try {
            URL url = new URL("http://www.sina.com");
            InetSocketAddress addr = new InetSocketAddress("115.28.194.148", 3128);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
            URLConnection connection = url.openConnection(proxy);

            try (InputStream inputStream = connection.getInputStream()) {
                FileChannel outChannel = new FileOutputStream("content.txt").getChannel();
                ReadableByteChannel inChannel = Channels.newChannel(inputStream);
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (true) {
                    if (inChannel.read(buffer) == -1) {
                        break;
                    }
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }
                inChannel.close();
                outChannel.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
