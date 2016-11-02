import java.net.SocketTimeoutException;

/**
 * Created by Administrator on 2016/3/10.
 */
public class TestStack {
    public static void main(String[] args) {
        currentaction();
    }

    public static void currentaction() {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        for(StackTraceElement item :ste){
            System.out.println(item.getMethodName()+" ==> "
                    +item.getClassName()+" ==> "+item.getFileName()
                    +" @linenumber:"+item.getLineNumber());

        }
    }
}
