package thread.createthread;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */
public class PollingWorker implements Runnable {
    private List<IOEventTask> tasks;
    private long sleepTime;
    void register(IOEventTask t){tasks.add(t);}
    void deregister(IOEventTask t){tasks.remove(t);}
    @Override
    public void run() {
        try {
            for(;;) {
                for (IOEventTask item : tasks) {
                    if(item.done())
                        deregister(item);
                    else {
                        boolean trigger;
                        try {
                            trigger = item.input().available()>0;

                        } catch (IOException e) {
                            trigger = true;
                        }
                        if(trigger)
                            item.run();
                    }
                }
                Thread.sleep(sleepTime);
            }
        } catch (InterruptedException e) {}
    }
}
