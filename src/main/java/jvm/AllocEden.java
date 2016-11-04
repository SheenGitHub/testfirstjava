package jvm;

/**
 * Created by Administrator on 2016/11/2.
 */
public class AllocEden {
    public static final int _1k=1024;
    public static void main(String[] args) {
        for (int i = 0; i < 5 * _1k; i++) {
            byte[] b = new byte[_1k];
        }
    }
}
