package thread;

import sample.Trader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/11/7.
 */
public class Mutext implements Sync {
    @Override
    public void acquire() throws InterruptedException {

    }

    @Override
    public void release() {

    }

    @Override
    public boolean attempt(long mesec) throws InterruptedException {
        return false;
    }
}

class CancellableReader {
    private Thread readerThread;
    private FileInputStream dataFile;

    public synchronized void startReaderThread() throws FileNotFoundException,IllegalStateException {
        if(readerThread != null) throw new IllegalStateException();
        dataFile = new FileInputStream("data");
        readerThread = new Thread(new Runnable(){

            @Override
            public void run() {
                doReadd();
            }
        });
    }

    private void doReadd() {
        try {
            while (!Thread.interrupted()) {
                try {
                    int c =dataFile.read();
                    if(c == -1) break;
                    else process(c);
                } catch (IOException e) {
                    break;
                }
            }
        }finally {
            closeFile();
            synchronized (this) {
                readerThread = null;
            }
        }

    }

    private void closeFile() {
        if (dataFile != null) {
            try {
                dataFile.close();
            } catch (IOException e) {
                dataFile = null;
            }
        }

    }

    private void process(int c) {
    }
}

class ReaderWithTimeout {
    void attemptRead(InputStream stream, long timeout) {
        long startTime =  System.currentTimeMillis();
        try {
            for (; ; ) {
                if (stream.available() > 0) {
                    int c = stream.read();
                    if (c != -1) process(c);
                    else break;
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        return;
                    }
                    long now = System.currentTimeMillis();
                    if (now - startTime >= timeout) {
                        /* fail*/
                    }
                }
            }
        } catch (IOException e) {
            /*fail*/
        }
    }

    private void process(int c) {
    }
}

class Terminator{
    static SecurityManager theSecurityMgr;
    static boolean terminate(Thread t, long maxWaitToDie) {
        if(t.isAlive()) return true;

        t.interrupt();
        try {
            t.join();
        } catch (InterruptedException e) {
        }

        if(!t.isAlive()) return true;

        theSecurityMgr.checkAccess(t);
        try {
            t.join(maxWaitToDie);
        } catch (InterruptedException e) {
        }

        if(!t.isAlive()) return true;
        t.setPriority(Thread.MIN_PRIORITY);
        return false;
    }

}
