package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/10/27.
 */
class Bus {
    private boolean waxOn = true;
    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (waxOn == false) {
            wait();
        }
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn == true) {
            wait();
        }
    }
}

class BusWax implements Runnable{
    private Bus bus;

    public BusWax(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                bus.waitForBuffing();
                System.out.print("Wax On ");
                TimeUnit.MILLISECONDS.sleep(200);
                bus.waxed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Bus Waxing");
    }

}

class BusBuff implements Runnable {
    private Bus bus;

    public BusBuff(Bus bus) {
        this.bus = bus;
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.print("Wax Off ");
                TimeUnit.MILLISECONDS.sleep(200);
                bus.buffed();
                bus.waitForWaxing();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Bus Buffing");
    }
}

public class SwapWax {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Bus bus = new Bus();
        service.execute(new BusWax(bus));
        service.execute(new BusBuff(bus));
        TimeUnit.SECONDS.sleep(3);
        service.shutdownNow();
    }
}
