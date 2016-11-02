package sample;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by Administrator on 2016/6/4.
 */
public class CharacterUse {
    public static void main(String[] args) {
//            usdeCharacter();
        simpleEncode();
    }
    public static void usdeCharacter() {
        String str = "你好";
        int codePoint = Character.codePointAt(str, 0);
        boolean isBmp = Character.isBmpCodePoint(codePoint);
        int smpCodePoint = 0x12367;
        boolean isSupple = Character.isSupplementaryCodePoint(smpCodePoint);
        int count = Character.charCount(smpCodePoint);
        char high = Character.highSurrogate(smpCodePoint);
        char low = Character.lowSurrogate(smpCodePoint);

        System.out.println("codePoint " + codePoint);
        System.out.println("isBmp " + isBmp);
        System.out.println("isSupple " + isSupple);
        System.out.println("count " + count);
        System.out.println("high " + high);
        System.out.println("low " + low);
    }

    public static void simpleEncode() {
        Charset charset = StandardCharsets.UTF_8;
        CharsetEncoder encoder = charset.newEncoder();
        CharBuffer inputBuffer = CharBuffer.allocate(256);
        inputBuffer.put("你好").flip();
        ByteBuffer outputBuffer = ByteBuffer.allocate(256);
        encoder.encode(inputBuffer, outputBuffer, true);

    }
}
