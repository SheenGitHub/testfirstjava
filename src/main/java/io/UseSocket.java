package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by Administrator on 2016/3/18.
 */
public class UseSocket {
    public static void main(String[] args) throws IOException {
        UseSocket us = new UseSocket();
        us.loadWebPageUseSocket();
    }
    public void loadWebPageUseSocket() {
        try (FileChannel destChannel = FileChannel.open(
                Paths.get("content.txt"), StandardOpenOption.WRITE,
                StandardOpenOption.CREATE
        );
             SocketChannel sc = SocketChannel.open(
                     new InetSocketAddress("115.28.194.148", 80))
        ) {
            String request = "GET / HTTP/1.1\r\n\r\nHost: 115.28.194.148\r\n\r\n";
            ByteBuffer header = ByteBuffer.wrap(request.getBytes("UTF-8"));
            sc.write(header);
            destChannel.transferFrom(sc, 0, Integer.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StartSimpleServer() throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress("localhost", 10800));
        while (true) {
            try (SocketChannel sc = channel.accept();
                 FileChannel src = FileChannel.open(Paths.get("i-have-a-dream.html"))
            ) {
                ByteBuffer buffer = ByteBuffer.allocate(5*1024);
                while (src.read(buffer) >0 || buffer.position()!=0){
                    buffer.flip();
                    sc.write(buffer);
                    buffer.compact();
                }



            }
        }
    }
}
