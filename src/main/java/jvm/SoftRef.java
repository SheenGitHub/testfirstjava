package jvm;

import java.lang.ref.SoftReference;
import java.nio.channels.Pipe;

/**
 * Created by Administrator on 2016/10/31.
 */
public class SoftRef {
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

    public static void main(String[] args) {
        User u = new User(1,"geym");
        SoftReference<User> userSoftReference = new SoftReference<User>(u);
        u = null;
        System.out.println(userSoftReference.get());
        System.gc();
        System.out.println("After gc");
        System.out.println(userSoftReference.get());

        byte[] b = new byte[1024 * 925 * 7];
        System.gc();
        System.out.println(userSoftReference.get());
    }
}
