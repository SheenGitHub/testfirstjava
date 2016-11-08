package thread.statedependency;

/**
 * Created by Administrator on 2016/11/7.
 * 有条件的等待
 */
public class BoundedCounterWithWhen implements BoundedCounter{

    private int count;

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void inc() throws InterruptedException {
        while (count >= MAX) wait();
        ++count;
    }

    @Override
    public void dec() {

    }
}

class GuardedClass{
    protected boolean cond = false;

    protected void awaitCond() throws InterruptedException {
        while (!cond)wait();
    }

    public synchronized void guardAction() {
        try {
            awaitCond();
        } catch (InterruptedException e) {
            //fail
        }
        //action
    }

}



