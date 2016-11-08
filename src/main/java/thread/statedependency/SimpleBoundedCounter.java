package thread.statedependency;

/**
 * Created by Administrator on 2016/11/7.
 */
public class SimpleBoundedCounter implements BoundedCounter {
    protected long count = MIN;
    @Override
    public synchronized long count() {
        return count;
    }

    @Override
    public synchronized void inc() throws InterruptedException {
        awaitUnderMax();
        setCount(count + 1);
    }

    @Override
    public synchronized void dec() throws InterruptedException {
        awaitUnderMin();
        setCount(count - 1);
    }

    protected void setCount(long newValue) {
        count = newValue;
        notifyAll();
    }

    protected void awaitUnderMax() throws InterruptedException {
        while (count == MAX) wait();
    }
    protected void awaitUnderMin() throws InterruptedException {
        while (count == MIN) wait();
    }
}

class GuardedClassUsingNotify {
    protected boolean cond = false;
    protected int nWaiting = 0;

    protected synchronized void awaitCond() throws InterruptedException {
        while (!cond){
            ++nWaiting;
            try {
                wait();
            } catch (InterruptedException e) {
                notify();
                throw e;
            }finally {
                --nWaiting;
            }
        }
    }

    protected synchronized void signalCond() {
        if (cond) {
            for (int i = nWaiting; i > 0; i--) notify();
        }
    }
}


