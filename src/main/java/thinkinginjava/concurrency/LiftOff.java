package thinkinginjava.concurrency;

/**
 * Created by Administrator on 2016/8/8.
 */
public class LiftOff implements Runnable {
    protected int countdown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff(){}

    public LiftOff(int countdown) {
        this.countdown = countdown;
    }

    public String status() {
        return "#"+id+"("+(countdown>0?countdown:"Liftoff!")+"),";
    }

    @Override
    public void run() {
        while (countdown-- > 0) {
            System.out.print(status());
            Thread.yield();
        }
    }
}
