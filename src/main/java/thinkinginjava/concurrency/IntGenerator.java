package thinkinginjava.concurrency;

/**
 * Created by Administrator on 2016/8/9.
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();
    public void cancel(){canceled = true;}
    public boolean isCanceled(){return canceled;}
}
