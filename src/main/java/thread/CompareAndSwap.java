package thread;

/**
 * Created by Administrator on 2016/11/4.
 */
public class CompareAndSwap {
}
class ImmutablePoint{
    private final int x;
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class Dot {
    protected ImmutablePoint loc;

    public Dot(int x, int y) {
        loc = new ImmutablePoint(x, y);
    }

    public synchronized ImmutablePoint getLoc() {
        return loc;
    }

    public synchronized void updateLoc(ImmutablePoint newLoc) {
        loc = newLoc;
    }

    public void moveTo(int x, int y) {
        updateLoc(new ImmutablePoint(x, y));
    }

    public synchronized void shiftx(int delta) {
        updateLoc(new ImmutablePoint(loc.getX()+delta,loc.getY()));
    }
}

class OptimisticDot {
    protected  ImmutablePoint loc;

    public OptimisticDot(int x, int y) {
        loc = new ImmutablePoint(x, y);
    }

    public synchronized ImmutablePoint location() {
        return loc;
    }

    protected synchronized boolean commit(ImmutablePoint assumed, ImmutablePoint next) {
        if (loc == assumed) {
            loc = next;
            return true;
        }
        else return false;
    }

    public synchronized void moveTo(int x, int y) {
        loc = new ImmutablePoint(x, y);
    }

    public void shiftX(int delta) {
        boolean success = false;
        do{
            ImmutablePoint old = location();
            ImmutablePoint next = new ImmutablePoint(old.getX() + delta,old.getY());
            success = commit(old, next);
        }while (!success);
    }
}
