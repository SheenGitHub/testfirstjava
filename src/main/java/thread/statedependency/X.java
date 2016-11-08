package thread.statedependency;

/**
 * Created by Administrator on 2016/11/7.
 */
public class X {

    synchronized void w() throws InterruptedException{
        before();wait();after();
    }
    synchronized void n(){ notifyAll();}
    void before(){}
    void after(){}
    public static void main(String[] args) {

    }
    public void sleep(long msecs) throws InterruptedException {
        if (msecs != 0) {
            Object s = new Object();
            synchronized (s) {
                s.wait(msecs);
            }
        }
    }
}

