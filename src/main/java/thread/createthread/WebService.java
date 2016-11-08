package thread.createthread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2016/11/8.
 */
public class WebService implements Runnable{
    static final int PORT = 1040;
    Handler handler = new Handler();

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            ServerSocket socket = new ServerSocket(PORT);
            for(;;){
                final Socket connection = socket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.process(connection);
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new WebService()).start();
    }
}
