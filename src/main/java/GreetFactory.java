import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2016/2/24.
 */
public class GreetFactory {
    public static GreetV2 adapterGreet(GreetV1 greet) {
        GreetAdapter adapter = new GreetAdapter(greet);
        ClassLoader cl = greet.getClass().getClassLoader();
        return (GreetV2) Proxy.newProxyInstance(cl, new Class<?>[]{GreetV1.class}, adapter);
    }
}
