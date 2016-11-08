package thread.createthread;

import thread.statedependency.Channel;

/**
 * Created by Administrator on 2016/11/8.
 */
public class PlainWorkerPool implements Executor {
    protected final Channel workQueue;

    public PlainWorkerPool(Channel workQueue, int nworkers) {
        this.workQueue = workQueue;
        for (int i = 0; i < nworkers; i++) activate();
    }

    private void activate() {
        Runnable runLoop =new Runnable() {
            @Override
            public void run() {
                try {
                    for(;;){
                        Runnable r = (Runnable)(workQueue.take());
                    }
                } catch (InterruptedException e) {}
            }
        };
        new Thread(runLoop).start();
    }


    @Override
    public void execute(Runnable runnable) {
        try {
            workQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
