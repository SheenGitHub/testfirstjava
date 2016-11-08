package thread.statedependency;

/**
 * Created by Administrator on 2016/11/8.
 */
public interface BoundedBuffer extends Channel {
    int capacity();
    int size();
}
