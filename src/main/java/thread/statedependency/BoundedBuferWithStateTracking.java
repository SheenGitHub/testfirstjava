package thread.statedependency;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2016/11/8.
 */
public class BoundedBuferWithStateTracking implements BoundedBuffer {
    protected final Object[] array;
    protected int putPtr = 0;
    protected int takePtr = 0;
    protected int usedSlots = 0;

    public BoundedBuferWithStateTracking(int capcity) throws IllegalStateException{
        if(capcity <= 0) throw new IllegalStateException();
        array = new Object[capcity];
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    public int size() {
        return usedSlots;
    }

    @Override
    public synchronized void put(Object x) throws InterruptedException {
        while (usedSlots == array.length)
            wait();

        array[putPtr] = x;
        putPtr = (putPtr + 1)%array.length;

        if(usedSlots++ == 0)
            notifyAll();
    }

    @Override
    public synchronized Object take() throws InterruptedException {
        while (usedSlots == 0)
            wait();
        Object x = array[takePtr];
        array[takePtr] = null;
        takePtr = (takePtr + 1)%array.length;

        if(usedSlots-- == array.length)
            notifyAll();

        return x;
    }
}
