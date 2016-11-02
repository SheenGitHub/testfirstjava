package jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * Created by Administrator on 2016/10/31.
 */
public class SoftRefQ {
    public static class User {
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
        public int id;
        public String name;

        @Override
        public String toString() {
            return "[id="+String.valueOf(id)+",name="+name+"]";
        }
    }

    static ReferenceQueue<User> softQueue = null;

    public static class CheckRefQueue extends Thread {
        @Override
        public void run() {
            while (true) {
                if (softQueue != null) {
                    UserSoftReference obj = null;
                    try {
                        obj  = (UserSoftReference)softQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (obj != null) {
                        System.out.println("user id " + obj.uid + " is delete");
                    }
                }
            }
        }
    }

    public static class UserSoftReference extends SoftReference<User> {
        /**
         * Creates a new soft reference that refers to the given object and is
         * registered with the given queue.
         *
         * @param referent object the new soft reference will refer to
         * @param q        the queue with which the reference is to be registered,
         */
        public UserSoftReference(User referent, ReferenceQueue<? super User> q) {
            super(referent, q);
            uid = referent.id;
        }

        int uid;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);
        t.start();
        User u = new User(1, "geym");
        UserSoftReference userSoftReference = new UserSoftReference(u, softQueue);
        u = null;
        System.out.println(userSoftReference.get());
        System.gc();

        System.out.println("After GC");
        System.out.println(userSoftReference.get());

        System.out.println("Try to Create byte array and GC");
        byte[] b = new byte[1024*925*7];
        System.gc();

        System.out.println(userSoftReference.get());
        Thread.sleep(1000);
    }

}
