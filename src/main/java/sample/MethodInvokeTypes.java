package sample;

import java.lang.invoke.*;

/**
 * Created by Administrator on 2016/3/17.
 */
public class MethodInvokeTypes {
    public void invoke() {
        SampleInterface sample = new Sample();
        sample.sampleMethodInInterface();
        Sample newSample = new Sample();
        newSample.normalMethod();
        Sample.staticSampleMethod();
    }

    public static void main(String[] args) {
        MethodInvokeTypes types = new MethodInvokeTypes();
        try {
            types.useConstantCallSite();
            types.useMutableCallSite();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void useConstantCallSite() throws Throwable {
        System.out.println("Current Method ===> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class, int.class, int.class);
        MethodHandle mh = lookup.findVirtual(String.class, "substring", type);
        ConstantCallSite callSite = new ConstantCallSite(mh);
        MethodHandle invoker = callSite.dynamicInvoker();
        String result = (String) invoker.invoke("Hello", 2, 3);
        System.out.println(result);
    }

    public void useMutableCallSite() throws Throwable {
        System.out.println("Current Method ===> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        MethodType type = MethodType.methodType(int.class, int.class, int.class);
        MutableCallSite callSite = new MutableCallSite(type);
        MethodHandle invoker = callSite.dynamicInvoker();
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mhMax = lookup.findStatic(Math.class, "max", type);
        MethodHandle mhMin = lookup.findStatic(Math.class, "min", type);
        callSite.setTarget(mhMax);
        int result = (int) invoker.invoke(3, 5);
        System.out.println(result);
        callSite.setTarget(mhMin);
        result = (int) invoker.invoke(3, 5);
        System.out.println(result);
    }
}
