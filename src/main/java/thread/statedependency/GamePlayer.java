package thread.statedependency;

/**
 * Created by Administrator on 2016/11/7.
 */
public class GamePlayer implements Runnable {
    protected GamePlayer other;
    protected boolean myturn = false;
    private int id ;

    public GamePlayer(int id) {
        this.id = id;
    }

    public synchronized void setOther(GamePlayer other) {
        this.other = other;
    }

    public synchronized void giveTurn() {
        myturn = true;
        notify();
    }

    void releaseTurn() {
        GamePlayer p;
        synchronized (this) {
            myturn = false;
            p = other;
        }
        p.giveTurn();
    }

    synchronized void awaitTurn() throws InterruptedException {
        while (!myturn) wait();
    }

    void move() {
        System.out.println(id +  " move");
    }
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            for(;;) {
                awaitTurn();
                move();
                releaseTurn();
            }
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        GamePlayer one = new GamePlayer(1);
        GamePlayer two = new GamePlayer(2);
        one.setOther(two);
        two.setOther(one);
        one.giveTurn();
        new Thread(two).start();
        new Thread(one).start();


    }
}
