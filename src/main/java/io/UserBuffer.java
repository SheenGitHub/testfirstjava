package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/3/17.
 */
public class UserBuffer {
    public static void main(String[] args) {
        byteOrder();
        compact();
        differFlipAndWind();
    }

    private static void differFlipAndWind() {
        ByteBuffer flipBuffer = ByteBuffer.allocate(32);
        flipBuffer.putChar('A');
        flipBuffer.putChar('A');
        flipBuffer.putChar('A');
        flipBuffer.putChar('A');
        flipBuffer.flip();
        while (flipBuffer.hasRemaining()) {
            System.out.print((char)flipBuffer.get());
        }
        System.out.println("X");

        ByteBuffer windBuffer = ByteBuffer.allocate(32);
        windBuffer.putChar('A');
        windBuffer.putChar('A');
        windBuffer.putChar('A');
        windBuffer.putChar('A');
        windBuffer.rewind();
        while (windBuffer.hasRemaining()) {
            System.out.print((int)windBuffer.get()+" ");
        }
        System.out.println("X");
    }

    public static void useByteBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.putChar('A');
        buffer.putLong(10, 100L);
        try (FileChannel fc = new FileOutputStream("data.txt").getChannel()) {
            fc.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print((char)buffer.get());
        }
    }

    public static void byteOrder() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(1);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        System.out.println(buffer.getInt(0));
    }

    public static void compact() {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put(new byte[16]);
        buffer.flip();
        buffer.getInt();
        buffer.compact();
        int pos = buffer.position();
        System.out.println(pos);
    }
}
