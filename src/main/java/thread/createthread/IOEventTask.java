package thread.createthread;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2016/11/15.
 */
public class IOEventTask implements Runnable {
    private static final int BUFFSIZE = 1024;
    protected final Socket socket;
    protected final InputStream input;
    protected volatile boolean done =false;

    public IOEventTask(Socket socket) throws IOException {
        this.socket = socket;
        input = socket.getInputStream();
    }

    @Override
    public void run() {
        if(done) return;
        byte[] commandBuffer = new byte[BUFFSIZE];
        try {
            int bytes = input.read(commandBuffer, 0, BUFFSIZE);
            if(bytes!= BUFFSIZE) done = true;
            processCommand(commandBuffer,bytes);
        } catch (IOException e) {
            cleanup();
            done = true;
        }finally {
            if(!done) return;
            try {
                input.close();socket.close();
            } catch (IOException e) {
            }
        }
    }

    private void cleanup() {
    }

    private void processCommand(byte[] commandBuffer, int bytes) {
    }
    boolean done(){return done;}
    InputStream input(){return input;}
}
