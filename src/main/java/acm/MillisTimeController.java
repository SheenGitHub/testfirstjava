package acm;

/**
 * Created by Administrator on 2016/9/26.
 */
public class MillisTimeController extends TimeControler {
    @Override
    public long start() {
        start = System.currentTimeMillis();
        return start;
    }

    @Override
    public long end()
    {
        end = System.currentTimeMillis();
        return 0;
    }

    @Override
    public void printTime() {
        System.out.println("Time Consumed "+ timeConsumed() + " nano Seconds");
    }
}
