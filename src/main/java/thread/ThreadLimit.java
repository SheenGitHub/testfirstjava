package thread;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

/**
 * Created by Administrator on 2016/11/4.
 */
public class ThreadLimit {
    public static void main(String[] args) {

    }
}

class ThreadPerSessionBasedService {

    public void service() {
        Runnable r= () -> {
            OutputStream output = null;
            try {
                output= new FileOutputStream("");
                doService(output);
            } catch (FileNotFoundException e) {
                handleIOFailure();
            }finally {
                try {
                    if(output != null)
                        output.close();
                } catch (IOException e) {

                }
            }
        };
    }

    private void handleIOFailure() {
    }

    private void doService(OutputStream output) {
    }
}

class ThreadWithOutputStream extends Thread {
    private OutputStream output;


    public ThreadWithOutputStream(Runnable target, OutputStream output) {
        super(target);
        this.output = output;
    }

    static ThreadWithOutputStream current() throws ClassCastException {
        return (ThreadWithOutputStream) (currentThread());
    }

    public OutputStream getOutput() {
        return output;
    }

    public void setOutput(OutputStream output) {
        this.output = output;
    }

    class ServiceUsingThreadWithOutputStream {
        public void service() throws FileNotFoundException {
            OutputStream output = new FileOutputStream("");
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    try {
                        doService();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            new ThreadWithOutputStream(r,output).start();
        }

        private void doService() throws IOException {
            ThreadWithOutputStream.current().getOutput().write(0);
        }
    }
}

