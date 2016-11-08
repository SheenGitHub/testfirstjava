package thread.statedependency;

import thread.Sync;

/**
 * Created by Administrator on 2016/11/8.
 */
public interface ReadWriteLock {
    Sync readLock();

    Sync writeLock();
}
