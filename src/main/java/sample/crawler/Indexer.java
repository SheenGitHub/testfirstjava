package sample.crawler;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2016/7/13.
 */
public class Indexer implements Runnable {
    private BlockingQueue<Integer> DataQueue;

    public Indexer(BlockingQueue<Integer> dataQueue) {
        DataQueue = dataQueue;
    }

    @Override
    public void run() {
        Integer i;
        while (!Thread.interrupted()) {
            try {
                i = DataQueue.take();
                System.out.println("Index : " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
