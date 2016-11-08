package thread.statedependency;

import java.util.Hashtable;

/**
 * Created by Administrator on 2016/11/8.
 */
public class Inventory {
    protected final Hashtable items = new Hashtable();
    protected final Hashtable suppliers = new Hashtable();

    protected int storing = 0;
    protected int retrieving = 0;

    protected void doStore(String description, Object item, String supplier) {
        items.put(description,item);
        suppliers.put(supplier,description);
    }

    protected Object doRetrieve(String description) {
        Object x = items.get(description);
        if (x != null)
            items.remove(description);
        return x;
    }

    public void store(String description, Object item, String supplier) throws InterruptedException {
        synchronized (this){
            while (retrieving != 0)
                wait();
            ++storing;
        }
        try {
            doStore(description,item,supplier);
        }finally {
            synchronized (this) {
                if(--storing == 0)
                    notifyAll();
            }
        }
    }

    public Object retrieve(String description) throws InterruptedException {
        synchronized (this) {
            while (storing!=0 || retrieving != 0)
                wait();
            try {
                return doRetrieve(description);
            }finally {
                synchronized (this) {
                    if(--retrieving == 0)
                        notifyAll();
                }
            }
        }
    }
}
