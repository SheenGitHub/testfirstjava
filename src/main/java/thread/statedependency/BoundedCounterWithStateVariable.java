package thread.statedependency;

/**
 * Created by Administrator on 2016/11/8.
 */
public class BoundedCounterWithStateVariable implements BoundedCounter {
    static final int BOTTOM = 0, MIDDLE = 1, TOP = 2;
    protected int state = BOTTOM;
    protected long count = MIN;

    protected void updateState() {
        int oldState = state;
        if         (count == MIN )  state = BOTTOM;
        else if (count == MAX)   state = TOP;
        else                                 state = MIDDLE;
        if(state != oldState && oldState != MIDDLE)
            notifyAll();
    }
    @Override
    public long count() {
        return 0;
    }

    @Override
    public void inc() throws InterruptedException {
        while ( state == TOP ) wait();
        ++count;
        updateState();
    }

    @Override
    public void dec() throws InterruptedException {
        while ( state == BOTTOM )wait();
        --count;
        updateState();
    }
}
