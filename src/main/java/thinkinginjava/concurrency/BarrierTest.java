package thinkinginjava.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/10/27.
 */
class Monkey implements Runnable{
    private static int counter = 0;
    private final int id = counter++;
    private int num = 0;
    private final CyclicBarrier barrier;

    public Monkey(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {


        try {
            while (!Thread.interrupted()) {
                System.out.println("#"+id + " Eat $" + num++ + " Apple");
                barrier.await();
            }
        } catch (InterruptedException e) {

        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
public class BarrierTest {
    private CyclicBarrier barrier;
    private ExecutorService exec = Executors.newCachedThreadPool();
    private List<Monkey> zoo = new ArrayList<>();
    public BarrierTest(int n, int pause) {
        barrier = new CyclicBarrier(n, new Runnable() {
            private int num = 20;
            @Override
            public void run() {
                System.out.println("All have Eaten. ");
                if( num-- <0)
                    exec.shutdownNow();

                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    System.out.println("stop");
                }
            }
        });
        for (int i = 0; i < n; i++) {
            Monkey monkey = new Monkey(barrier);
            zoo.add(monkey);
            exec.execute(monkey);
        }
    }

    public static void main(String[] args) {
        new BarrierTest(10,200);
    }
}
