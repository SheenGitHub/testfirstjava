package thread.statedependency;

/**
 * Created by Administrator on 2016/11/8.
 */
public interface Channel {
    void put(Object x) throws InterruptedException;
    Object take() throws InterruptedException;
}
