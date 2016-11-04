package thread;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Administrator on 2016/11/4.
 */
public class CopyOnWriteArrayList {
    protected Object[] array = new Object[0];

    protected synchronized Object[] getArray(){return array;}
    protected synchronized void add(Object element) {
        int len = array.length;
        Object[] newArray = new Object[len + 1];
        System.arraycopy(array, 0, newArray, 0, len);
        newArray[len] = element;
        array = newArray;
    }

    public Iterator iterator() {
        return new Iterator(){
            protected final Object[] snapshot = getArray();
            protected int cursor = 0;
            /**
             * Returns {@code true} if the iteration has more elements.
             * (In other words, returns {@code true} if {@link #next} would
             * return an element rather than throwing an exception.)
             *
             * @return {@code true} if the iteration has more elements
             */
            @Override
            public boolean hasNext() {
                return cursor < snapshot.length;
            }

            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more elements
             */
            @Override
            public Object next() {
                try {
                    return snapshot[cursor++];
                } catch (IndexOutOfBoundsException e) {
                    throw new NoSuchElementException();
                }
            }

        };
    }
}
