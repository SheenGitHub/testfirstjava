package acm;

/**
 * Created by Administrator on 2016/9/26.
 */
public abstract class TimeControler {
    protected long start;
    protected long end;
    public abstract long start();
    public abstract long end();
    public long timeConsumed(){
        return end - start;
    }
    public abstract void printTime();
}
