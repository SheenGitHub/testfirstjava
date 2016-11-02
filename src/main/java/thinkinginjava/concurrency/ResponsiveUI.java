package thinkinginjava.concurrency;

import java.io.IOException;

/**
 * Created by Administrator on 2016/8/8.
 */
class UnresponsiveUI {
    private double d = 1;

    public UnresponsiveUI() throws Exception{
        while(d > 0)
            d = d + (Math.PI+Math.E)/d;
        System.in.read();
    }
}

public class ResponsiveUI extends Thread {
    private static double d = 1;

    public ResponsiveUI() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while(true)
            d = d + (Math.PI+Math.E)/d;
    }

    public static void main(String[] args) throws IOException {
        new ResponsiveUI();
        System.in.read();
        System.out.println(d);

    }
}