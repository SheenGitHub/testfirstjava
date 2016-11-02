package io;

/**
 * Created by Administrator on 2016/6/6.
 */
public class NotAtomicity {
    public static long t = 0;

    public static long getT() {
        return t;
    }

    public static void setT(long t) {
        NotAtomicity.t = t;
    }

    public static class ChangeT implements Runnable {
        private long to;

        public ChangeT(long to) {
            this.to = to;
        }

        @Override
        public void run() {
            while (true) {
                NotAtomicity.setT(to);
                Thread.yield();
            }
        }
    }

    public static class ReadT implements Runnable {
        @Override
        public void run() {
            while (true) {
                long tmp = NotAtomicity.getT();
                if (tmp != 100L && tmp != 200L && tmp != -300L && tmp != -400L) {
                    System.out.println(tmp);
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ChangeT(100L)).start();
        new Thread(new ChangeT(200L)).start();
        new Thread(new ChangeT(-300L)).start();
        new Thread(new ChangeT(-400L)).start();
        new Thread(new ReadT()).start();
    }


}
