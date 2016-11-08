package thread.createthread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2016/11/8.
 */
public class Handler {
    public void process(Socket connection) {
        DataInputStream in =null;
        DataOutputStream out = null;
        try {
            in = new DataInputStream(connection.getInputStream());
            out = new DataOutputStream(connection.getOutputStream());
            int request = in.readInt();
            int result = -request;
            out.write(result);
        } catch (IOException e) {
        }finally {
            try {
                if(in != null)in.close();
            } catch (IOException e) {
            }
            try {
                if(out!=null) out.close();
            } catch (IOException e) {
            }
            try {
                connection.close();
            } catch (IOException e) {
            }
        }
    }
}
