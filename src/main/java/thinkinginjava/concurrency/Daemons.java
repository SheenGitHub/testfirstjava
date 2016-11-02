package thinkinginjava.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/8/8.
 */
class Daemon implements Runnable{
    private Thread[] t = new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            System.out.print("DaemonSpawn " + i + " started,");
        }
        for (int i = 0; i < t.length; i++) {
            System.out.print("t["+i +"].isDaemon() = " + t[i].isDaemon() + ",");
        }
        while (true)
            Thread.yield();
    }
}
class DaemonSpawn implements Runnable{

    @Override
    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}

public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.print("d.isDaemon() - " + d.isDaemon() +",");
        TimeUnit.SECONDS.sleep(1);
    }

}
