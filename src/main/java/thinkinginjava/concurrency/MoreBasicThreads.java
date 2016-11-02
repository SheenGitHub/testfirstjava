package thinkinginjava.concurrency;

/**
 * Created by Administrator on 2016/8/8.
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new  Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for LiftOff");
    }
}
