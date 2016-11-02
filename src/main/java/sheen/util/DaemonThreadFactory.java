package sheen.util;

import java.util.concurrent.ThreadFactory;

/**
 * Created by Administrator on 2016/8/8.
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread();
        t.setDaemon(true);
        return t;
    }
}
