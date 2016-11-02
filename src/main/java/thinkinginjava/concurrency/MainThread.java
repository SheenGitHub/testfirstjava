package thinkinginjava.concurrency;

/**
 * Created by Administrator on 2016/8/8.
 */
public class MainThread {
    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
    }
}
