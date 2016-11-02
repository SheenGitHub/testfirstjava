package io;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Created by Administrator on 2016/5/31.
 */
public class IntBufferDemo {
    public static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        IntBuffer ib = bb.asIntBuffer();

        ib.put(new int[]{11, 42, 47, 99, 143, 811, 1016});
        System.out.println(ib.get(3));

        ib.flip();
        while (ib.hasRemaining()) {
            int i = ib.get();
            System.out.println(i);
        }
    }
}
