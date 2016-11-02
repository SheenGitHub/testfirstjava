package io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/3/17.
 */
public class GetChannel {
    public static final int BSIZE = 1024;

    public static void main(String[] args) {
        try (FileChannel fc = new FileOutputStream("data.txt").getChannel()) {
            fc.write(ByteBuffer.wrap("Some text ".getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileChannel fc = new RandomAccessFile("data.txt", "rw").getChannel()) {
            fc.position(fc.size());
            fc.write(ByteBuffer.wrap("Some more ".getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileChannel fc = new FileInputStream("data.txt").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
            fc.read(buffer);
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char)buffer.get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
