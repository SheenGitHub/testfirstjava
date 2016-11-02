import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/2/20.
 */
public class LoggingInvocationHandler implements InvocationHandler {
    private static final Logger LOGGER = Logger.getLogger(LoggingInvocationHandler.class);
    private Object receiverObject;

    public LoggingInvocationHandler(Object receiverObject) {
        this.receiverObject = receiverObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.log(Level.INFO, "调用方法 " + method.getName() + ";参数为 " + Arrays.deepToString(args));
        return method.invoke(receiverObject, args);
    }



}
