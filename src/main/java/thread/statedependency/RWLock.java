package thread.statedependency;

import thread.Sync;

/**
 * Created by Administrator on 2016/11/8.
 */
public class RWLock extends ReadWriter implements ReadWriteLock {
    @Override
    public Sync readLock() {
        return null;
    }

    @Override
    public Sync writeLock() {
        return null;
    }

    class RLock implements Sync {

        @Override
        public void acquire() throws InterruptedException {
            beforeRead();
        }

        @Override
        public void release() {
            afterRead();
        }

        @Override
        public boolean attempt(long mesec) throws InterruptedException {
            return false;
        }
    }
    @Override
    protected void doRead() {

    }

    @Override
    protected void doWrite() {

    }
}
