
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public class SimpleProxy {
    public static void main(String[] args) {
        useProxy();
        useGenericProxy();
    }
    public static void useProxy() {
        String str = "Hello World";
        LoggingInvocationHandler handler = new LoggingInvocationHandler(str);
        ClassLoader cl = SimpleProxy.class.getClassLoader();
        Comparable obj = (Comparable) Proxy.newProxyInstance(cl, new Class[]{Comparable.class}, handler);
        obj.compareTo("Good");
    }

    public static <T> T makeProxy(Class<T> initf, final T object) {
        LoggingInvocationHandler handler = new LoggingInvocationHandler(object);
        ClassLoader cl = object.getClass().getClassLoader();
        return (T) Proxy.newProxyInstance(cl, new Class[]{initf}, handler);
    }

    public static void useGenericProxy() {
        String str = "Hello World";
        Comparable proxy = makeProxy(Comparable.class, str);
        proxy.compareTo("Good");
        List<String> list = new ArrayList<>();
        list = makeProxy(List.class, list);
        list.add("Hello");
    }

    public static Object proxyAll(final Object object) {
        LoggingInvocationHandler handler = new LoggingInvocationHandler(object);
        ClassLoader cl = object.getClass().getClassLoader();
        Class<?>[] interfaces = object.getClass().getInterfaces();
        return Proxy.newProxyInstance(cl, interfaces, handler);
    }


}
