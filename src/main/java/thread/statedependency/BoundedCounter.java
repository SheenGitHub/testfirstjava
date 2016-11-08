package thread.statedependency;

/**
 * Created by Administrator on 2016/11/7.
 */
public interface BoundedCounter {
    static final long MIN = 0;
    static final long MAX = 10;

    long count();
    void inc() throws InterruptedException;
    void dec() throws InterruptedException;
}
