package io;

import org.apache.lucene.analysis.util.CharArraySet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Administrator on 2016/5/30.
 */
public class BufferToText {
    public static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        FileChannel fc = new FileOutputStream("data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("测试字符".getBytes("UTF-16BE")));
        fc.close();

        fc = new FileInputStream("data2.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fc.read(buffer);
        buffer.flip();

        System.out.println(buffer.asCharBuffer());

        buffer.rewind();

        String encoding = System.getProperty("file.encoding");
        System.out.println("Decode using " + encoding + ":" + Charset.forName(encoding).decode(buffer));
        fc = new FileOutputStream("data2.txt").getChannel();
        Charset utf = StandardCharsets.UTF_16BE;
        Charset utf16 = Charset.forName("UTF-16BE");
        Charset utf8 = Charset.forName("UTF-8");
        fc.write(ByteBuffer.wrap("Some text".getBytes(utf16)));
        fc.close();

        fc = new FileInputStream("data2.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(utf16.decode(buffer));


        fc = new FileOutputStream("data2.txt").getChannel();
        buffer = ByteBuffer.allocate(24);
        buffer.asCharBuffer().put("测试字符");
        fc.write(buffer);
        fc.close();

        fc = new FileInputStream("data2.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());

        SortedMap<String, Charset> map = Charset.availableCharsets();
        Iterator<String> keys = map.keySet().iterator();
        while (keys.hasNext()) {
            String csName = keys.next();
            System.out.print(csName+":");
            Iterator aliases = map.get(csName).aliases().iterator();
            while (aliases.hasNext()) {
                System.out.print(aliases.next()+",");
            }
            System.out.println();
        }
    }
}
