package thread;

/**
 * Created by Administrator on 2016/11/7.
 */
public interface Sync {
    void acquire() throws InterruptedException;
    void release();
    boolean attempt(long mesec) throws InterruptedException;
}
