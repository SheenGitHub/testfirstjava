package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by Administrator on 2016/5/31.
 */
public class LockingMappedFiles {
    static final int LENGTH = 0x8ffffff;
    static FileChannel fileChannel;

    public static void main(String[] args) throws IOException {
        fileChannel = new RandomAccessFile("test1.dat","rw").getChannel();
        MappedByteBuffer out = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            out.put((byte) 'x');
        }

        new LockAndModify(out, 0, 0 + LENGTH / 3);
        new LockAndModify(out, LENGTH / 2, 0 + LENGTH / 2 + LENGTH / 4);


    }

    private static class LockAndModify extends Thread {
        private ByteBuffer buffer;
        private int start, end;

        public LockAndModify(ByteBuffer mbb, int start, int end) {
            this.start = start;
            this.end = end;
            mbb.limit(end);
            mbb.position(start);
            buffer = mbb.slice();
            start();
        }

        @Override
        public void run() {
            try {
                FileLock fl = fileChannel.lock(start, end, false);
                System.out.println("Locked: " + start + "  to " + end);
                while (buffer.position() < buffer.limit() - 1)
                    buffer.put((byte) (buffer.get() + 1));
                fl.release();
                System.out.println("Released: " + start + " to " + end);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
