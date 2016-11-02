import java.lang.invoke.*;

/**
 * Created by Administrator on 2016/3/16.
 */
public class UseMethodHandleProxies {
    private static final MethodType typeCallback = MethodType.methodType(Object.class, Object.class, int.class);

    public static void main(String[] args) {
        UseMethodHandleProxies proxies = new UseMethodHandleProxies();
        try {
            proxies.useMethodHandleProxy();
            proxies.useSwitchPoint();
            System.out.println(proxies.add5(6));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
    public void doSomething() {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        System.out.println("WORK");
    }

    public void useMethodHandleProxy() throws Throwable {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findVirtual(UseMethodHandleProxies.class,"doSomething", MethodType.methodType(void.class));
        mh = mh.bindTo(this);
        Runnable runnable = MethodHandleProxies.asInterfaceInstance(Runnable.class, mh);
        new Thread(runnable).start();
    }

    public void useSwitchPoint() throws Throwable {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(int.class, int.class, int.class);
        MethodHandle mhMax = lookup.findStatic(Math.class, "max", type);
        MethodHandle mhMin = lookup.findStatic(Math.class, "min", type);
        SwitchPoint sp = new SwitchPoint();
        MethodHandle mhNew = sp.guardWithTest(mhMin, mhMax);
        int result = (int)mhNew.invoke(3, 4);
        System.out.println(result);
        SwitchPoint.invalidateAll(new SwitchPoint[]{sp});
        result = (int)mhNew.invoke(3, 4);
        System.out.println(result);
    }
    public static void forEach(Object[] array, MethodHandle handle) throws Throwable {
        for(int i = 0, len = array.length; i < len; i++) {
            handle.invoke(array[i], i);
        }
    }

    public static Object[] map(Object[] array, MethodHandle handle) throws  Throwable {
        Object[] result = new Object[array.length];
        for (int i = 0,len = array.length; i<len ;i++) {
            result[i] = handle.invoke(array[i], i);
        }
        return  result;
    }

    public static Object reduce(Object[] array, Object initialValue,MethodHandle handle) throws Throwable {
        Object result = initialValue;
        for (int i = 0, len = array.length; i < len; i++) {
            result = handle.invoke(result, array[i]);
        }
        return result;
    }

    public static MethodHandle curry(MethodHandle handle, int value) {
        return MethodHandles.insertArguments(handle, 0, value);
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int add5(int a) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(int.class, int.class, int.class);
        MethodHandle mhAdd = lookup.findStatic(UseMethodHandleProxies.class, "add", type);
        MethodHandle mh = curry(mhAdd, 5);
        return (int) mh.invoke(a);
    }
}
