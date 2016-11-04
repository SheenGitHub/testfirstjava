package jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/2.
 */
public class MaxTenuringThreshold {
    public static final int _1M = 1024 * 1024;
    public static final int _1K = 1024;
    public static void main(String[] args) {
        Map<Integer, byte[]> map = new HashMap<>();
        for (int i = 0; i < 5 * _1K; i++) {
            byte[] b = new byte[_1K];
            map.put(i, b);
        }

        for (int k = 0; k < 17; k++) {
            for (int i = 0; i < 270; i++) {
                byte[] g = new byte[_1M];
            }
        }
    }
}
