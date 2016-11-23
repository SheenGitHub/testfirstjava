package thread.createthread;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/15.
 */
public class TimerDaemon {
    static class TimerTask implements Comparable {

        final Runnable command;
        final long execTime;

        public TimerTask(Runnable command, long execTime) {
            this.command = command;
            this.execTime = execTime;
        }

        @Override
        public int compareTo(Object x) {
            long otherExecTime = ((TimerTask)x).execTime;
            return (execTime < otherExecTime)?-1:
                    (execTime == otherExecTime)?0:1;
        }
    }

    static class PriorityQueue {
        void put(TimerTask timeTask) {}
        TimerTask least(){return null;}
        void removeLeast(){}
        boolean isEmpty(){return false;}
    }

    protected final PriorityQueue pq = new PriorityQueue();
    public synchronized void executeAfterDelay(Runnable r,long t) {
        pq.put(new TimerTask(r,t+System.currentTimeMillis()));
        notifyAll();
    }

    public synchronized void executeAt(Runnable r, Date time) {
        pq.put(new TimerTask(r,time.getTime()));
    }


}
