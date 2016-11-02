package acm;

/**
 * Created by Administrator on 2016/9/27.
 */
public class NanoControl extends TimeControler {
    @Override
    public long start() {
        start = System.nanoTime();
        return start;
    }

    @Override
    public long end() {
        end = System.nanoTime();
        return end;
    }

    @Override
    public void printTime() {
        System.out.println("Time Consumed "+ timeConsumed() + " nano Seconds");
    }
}
