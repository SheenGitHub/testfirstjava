package io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by Administrator on 2016/3/17.
 */
public class UseNio {
    public static void main(String[] args) {
        UseNio nio = new UseNio();
        nio.openAndWrite();
        nio.readWriteAbsolute();
        nio.loadWebPage("http://115.28.194.148");
        //nio.copyUseByteBuffer("content.txt","copycontent.txt");
        nio.copyUseChannelTransfer("content.txt","copycontent.txt");
        nio.mapFile();
    }

    public void openAndWrite() {
        try (FileChannel channel = FileChannel.open(Paths.get("my.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            ByteBuffer buffer = ByteBuffer.allocate(64);
            buffer.put((byte)'A');
            buffer.put((byte)'B');
            buffer.put((byte)'C');
            buffer.put((byte)'D');
            buffer.flip();
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadWebPage(String url) {
        try (FileChannel destChannel = FileChannel.open(
                Paths.get("content.txt"),
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE
        )) {
            InputStream inputStream = new URL(url).openStream();
            ReadableByteChannel srcChannel = Channels.newChannel(inputStream);
            destChannel.transferFrom(srcChannel, 0, Integer.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readWriteAbsolute() {
        try (FileChannel channel = FileChannel.open(Paths.get("absolute.txt"), StandardOpenOption.READ, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            ByteBuffer writerBuffer = ByteBuffer.allocate(4)
                    .putChar('A')
                    .putChar('B');
            writerBuffer.flip();
            channel.write(writerBuffer, 1024);
            ByteBuffer readBuffer = ByteBuffer.allocate(2);
            channel.read(readBuffer, 1026);
            readBuffer.flip();
            System.out.println(readBuffer.getChar());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copyUseByteBuffer(String srcFilename, String destFilename) {
        ByteBuffer buffer = ByteBuffer.allocate(32 * 1024);
        try (FileChannel src = FileChannel.open(Paths.get(srcFilename), StandardOpenOption.READ);
             FileChannel dest = FileChannel.open(Paths.get(destFilename), StandardOpenOption.WRITE,StandardOpenOption.CREATE)
        ) {
            while (src.read(buffer) > 0 || buffer.position() != 0) {
                buffer.flip();
                dest.write(buffer);
                buffer.compact();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copyUseChannelTransfer(String srcFilename, String destFilename) {
        try (FileChannel src = FileChannel.open(Paths.get(srcFilename), StandardOpenOption.READ);
             FileChannel dest = FileChannel.open(Paths.get(destFilename), StandardOpenOption.WRITE, StandardOpenOption.CREATE)
        ) {
            src.transferTo(0, src.size(), dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mapFile() {
        try (FileChannel channel = FileChannel.open(Paths.get("src.data"), StandardOpenOption.READ, StandardOpenOption.WRITE)) {
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
            byte b = buffer.get(1024 * 1024);
            buffer.put(5 * 1024 * 1024, b);
            buffer.force();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}