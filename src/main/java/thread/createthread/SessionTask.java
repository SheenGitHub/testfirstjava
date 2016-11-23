package thread.createthread;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2016/11/15.
 */
public class SessionTask implements Runnable {
    private static final int BUFFSIZE = 1024;
    protected final Socket socket;
    protected final InputStream input;
    public SessionTask(Socket s)throws IOException {
        socket = s;
        input = s.getInputStream();
    }
    @Override
    public void run() {
                byte[] commandBuffer = new byte[BUFFSIZE];
                try {
                    for(;;) {
                        int bytes = input.read(commandBuffer,0,BUFFSIZE);
                        if(bytes != BUFFSIZE) break;
                        processCommand(commandBuffer,bytes);
                    }

                } catch (IOException e) {
                    cleanup();
                }finally {
                    try {
                        input.close();socket.close();
                    } catch (IOException e) {}
                }
    }

    private void cleanup() {
    }

    private void processCommand(byte[] commandBuffer, int bytes) {
    }
}
