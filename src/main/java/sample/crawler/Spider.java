package sample.crawler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/7/13.
 */
public class Spider implements Runnable {
    private BlockingQueue<Integer> DataQueue;
    private static int i = 0;

    public Spider(BlockingQueue<Integer> dataQueue) {
        DataQueue = dataQueue;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            DataQueue.add(new Integer(++i));
            System.out.println("product :" + i);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> DataQueue = new LinkedBlockingDeque<>();

        Thread ct = new Thread(new Indexer(DataQueue));
        ct.start();

        Thread pt = new Thread(new Spider(DataQueue));
        pt.start();

    }
}
