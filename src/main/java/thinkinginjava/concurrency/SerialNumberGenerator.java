package thinkinginjava.concurrency;

/**
 * Created by Administrator on 2016/8/9.
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;

    public static int nextSerialNumber() {
        return serialNumber++;
    }
}
