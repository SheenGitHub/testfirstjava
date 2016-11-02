import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/2/23.
 */
public class MultipleInterfacesProxy {
    public void proxyMultipleInterfaces() throws Throwable {
        List<String> receiverObj = new ArrayList<>();
        ClassLoader cl = MultipleInterfacesProxy.class.getClassLoader();
        LoggingInvocationHandler handler = new LoggingInvocationHandler(receiverObj);
        Class<?> proxyClass = Proxy.getProxyClass(cl, new Class<?>[]{List.class, Set.class});
        Object proxy = proxyClass.getConstructor(new Class[]{InvocationHandler.class})
                .newInstance(new Object[]{handler});
        List list = (List)proxy;
        list.add("Hello");
        Set set = (Set)proxy;
        set.add("World");
    }
}
