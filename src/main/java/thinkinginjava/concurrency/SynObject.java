package thinkinginjava.concurrency;

/**
 * Created by Administrator on 2016/8/9.
 */
class DualSynch {
    private Object syncObject = new Object();

    public void f() {
        for (int i = 0; i < 5; i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }

    public void g() {
        for (int i = 0; i < 5; i++) {
            System.out.println("g()");

        }
    }
}

public class SynObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread(){
            @Override
            public void run() {
                ds.f();
            }
        }.start();
        ds.g();
    }

}
