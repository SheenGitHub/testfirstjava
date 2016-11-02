package sample;

import java.io.File;

/**
 * Created by Administrator on 2016/6/21.
 */
public class BooleanOperation {
    public static void main(String[] args) {
        int one = 0b110100;
        int two = 0b110111;
        int three = 0b010000;
        System.out.println(Integer.toBinaryString(one));
        System.out.println(Integer.toBinaryString(two));
        System.out.println(Integer.toBinaryString(~three));
        System.out.println(Integer.toBinaryString(one&two));
        System.out.println(Integer.toBinaryString(one&two&(~three)));


        File[] hiddenFiles = new File("C:\\").listFiles(File::isHidden);
        for (File file : hiddenFiles) {
            System.out.println(file.getName());
        }
    }
}
