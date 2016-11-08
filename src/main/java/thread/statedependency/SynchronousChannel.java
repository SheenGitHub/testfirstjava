package thread.statedependency;

import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2016/11/8.
 */
public class SynchronousChannel implements Channel {
    protected Object item = null;
    protected final Semaphore putPermit;
    protected final Semaphore takePermit;
    protected final Semaphore taken;

    public SynchronousChannel() {
        putPermit = new Semaphore(1);
        takePermit = new Semaphore(0);
        taken = new Semaphore(0);
    }

    @Override
    public void put(Object x) throws InterruptedException {
        putPermit.acquire();
        item = x;
        takePermit.release();

        InterruptedException caught = null;
        for(;;){
            try {
                taken.acquire();
                break;
            }catch (InterruptedException ie){
                caught = ie;
            }
        }
        if(caught !=null) throw caught;
    }

    @Override
    public Object take() throws InterruptedException {
        takePermit.acquire();
        Object x = item;
        item = null;
        putPermit.release();
        taken.release();
        return x;
    }
}
