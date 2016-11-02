package sample.crawler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/13.
 */
public class NIODemo {
    public static Selector sel = null;
    public static Map<SocketChannel, String> sc2Path = new HashMap<>();

    public static void setConnect(String ip, String path, int port) {
        try (SocketChannel client = SocketChannel.open()
        ) {
            client.configureBlocking(false);
            client.connect(new InetSocketAddress(ip, port));
            client.register(sel, SelectionKey.OP_CONNECT|SelectionKey.OP_READ
                                    |SelectionKey.OP_WRITE);
            sc2Path.put(client, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            sel = Selector.open();
            setConnect("www.sina.com", "/", 80);
            setConnect("hao123.com", "/book.htm", 80);

            while (!sel.keys().isEmpty()) {
                if (sel.select(5000) > 0) {
                    Iterator<SelectionKey> it = sel.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey key = it.next();
                        it.remove();
                        try {
                            processSelectionKey(key);
                        } catch (Exception e) {
                            key.cancel();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }


    }

    public static void processSelectionKey(SelectionKey key) throws IOException {
        SocketChannel sChannel = (SocketChannel)key.channel();
        if (key.isValid() && key.isConnectable()) {
            boolean isSuccess = sChannel.finishConnect();
            if (!isSuccess) {
                key.cancel();
            }
            sendMessage(sChannel,"GET " + sc2Path.get(sChannel) + " HTTP/1.0\r\nAccept:*/*\r\n\r\n");


        } else if (key.isReadable()) {
            String ret = readMessage(sChannel);
            if (ret != null && ret.length() > 0) {
                System.out.println(ret);
            } else {
                key.cancel();
            }
        }
    }

    private static String readMessage(SocketChannel client) {
        String result = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            int i = client.read(buffer);
            buffer.flip();
            if (i != -1) {
                result = new String(buffer.array(), 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }

    private static boolean sendMessage(SocketChannel client, String s) {

        try {
            ByteBuffer buf = ByteBuffer.allocate(1024);
            buf = ByteBuffer.wrap(s.getBytes());
            client.write(buf);
        } catch (IOException e) {
            return true;
        }
        return false;
    }

}
