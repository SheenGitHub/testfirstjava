package thinkinginjava.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/8/8.
 */
public class SimplePriority implements Runnable{
    private int countDown = 5;
    private double d;
    private int priority;

    public SimplePriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() +":" +countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for(int i = 1;i<10_000;i++) {
                d+=(Math.PI + Math.E)/(double)i;
                if(i%1_000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if(--countDown == 0) return;
        }

    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            service.execute(new SimplePriority(Thread.MIN_PRIORITY));
        }
        service.execute(new SimplePriority(Thread.MAX_PRIORITY));
        service.shutdown();
        
    }
}
