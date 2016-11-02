package thread;

/**
 * Created by Administrator on 2016/5/23.
 */
public class waittest {
    public static void main(String[] args) {
        waittest wt = new waittest();
        try {
            wt.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
