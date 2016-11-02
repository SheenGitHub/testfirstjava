package sample;

import java.util.Random;

/**
 * Created by Administrator on 2016/6/4.
 */
public class TestHashMap {
    static class Entry {
        String name;
        String id;
        Entry next;

        public Entry(String name, String id, Entry next) {
            this.name = name;
            this.id = id;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Entry[] list = new Entry[10];
        Random rand = new Random(47);

        Entry e3 = new Entry("Allen", "3", null);
        Entry e2 = new Entry("Bob", "2", e3);
        Entry e1 = new Entry("Carl", "1", e2);

        Entry e = e1;
        do {
            Entry next = e.next;
            e.next = list[0];
            list[0] = e;
            e = next;
        } while (e != null);
        Entry r = list[0];
        while (r != null) {
            System.out.println(r.name + "," +r.id+" ");
            r = r.next;
        }
    }

}
