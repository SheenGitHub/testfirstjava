package thread.createthread;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2016/11/15.
 */
public abstract class EventTemplate implements Runnable{
    protected final Socket socket;
    protected final InputStream inputStream;
    protected final static int BUFFERSIZE = 1024;

    public EventTemplate(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
    }


    @Override
    public void run() {
        byte[] commandBuffer = new byte[BUFFERSIZE];
        try {
            processCommand();
        } catch (IOException e) {
        }finally {
            try {
                inputStream.close();
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    public abstract void processCommand() throws IOException;
}
