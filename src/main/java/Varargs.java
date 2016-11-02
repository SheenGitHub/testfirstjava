import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

/**
 * Created by Administrator on 2016/3/9.
 */
public class Varargs {
    public static void main(String[] args) {
        Varargs varargs = new Varargs();
        try {
            varargs.insertArguments();
            varargs.filterArguments();
            varargs.foldArguments();
            varargs.permuteArguments();
            varargs.catchException();
            varargs.guardWithTest();
            varargs.filterReturnValue();
            varargs.invoker();
            varargs.invokerTransform();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
    public void normalMethod(String arg1, int arg2, int[] arg3) {
        System.out.println(arg1+" " + arg2);
        for (int i : arg3) {
            System.out.println(" "+i);
        }


    }

    public void asVarargsCollector()throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findVirtual(Varargs.class, "normalMethod",
                MethodType.methodType(void.class, String.class, int.class, int[].class));
        mh = mh.asVarargsCollector(int[].class);
        mh.invoke(this, "Hello", 2, 3, 4, 5);
    }

    public void asCollector() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findVirtual(Varargs.class, "normalMethod",
                MethodType.methodType(void.class, String.class, int.class, int[].class));
        mh = mh.asCollector(int[].class, 2);
        mh.invoke(this, "Hello", 2, 3, 4);
    }

    public void multipleBindTo() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findVirtual(String.class, "indexOf",
                MethodType.methodType(int.class, String.class, int.class));
        mh = mh.asType(mh.type().wrap());
        mh = mh.bindTo("Hello").bindTo("l").bindTo(2);
        System.out.println(mh.invoke());
    }

    public void unreflect() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Constructor constructor = String.class.getConstructor(byte[].class);
        lookup.unreflectConstructor(constructor);


    }

    public void arrayHandles() throws Throwable {
        int[] array = new int[]{1, 2, 3, 4, 5};
        MethodHandle setter = MethodHandles.arrayElementSetter(int[].class);
        setter.invoke(array, 3, 6);
        MethodHandle getter = MethodHandles.arrayElementGetter(int[].class);
        int value = (int) getter.invoke(array, 3);
        System.out.println(value);
    }

    public void dropArguments() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class, int.class, int.class);
        MethodHandle mhOld = lookup.findVirtual(String.class, "substring", type);
        String value = (String) mhOld.invoke("Hello", 2, 3);
        System.out.println(value);
        MethodHandle mhNew = MethodHandles.dropArguments(mhOld, 0, float.class, String.class);
        value = (String) mhNew.invoke(0.5f, "Ignore", "Hell0", 2, 3);
        System.out.println(value);
    }

    public void insertArguments() throws Throwable {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class, String.class);
        MethodHandle mhOld = lookup.findVirtual(String.class, "concat", type);
        String value = (String) mhOld.invoke("Hello", "World");
        System.out.println(value);
        MethodHandle mhNew = MethodHandles.insertArguments(mhOld, 1, "--");
        value = (String) mhNew.invoke("Hello");
        System.out.println(value);
    }
    public void filterArguments() throws Throwable {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(int.class, int.class, int.class);
        MethodHandle mhGetLength = lookup.findVirtual(String.class, "length",
                MethodType.methodType(int.class));
        MethodHandle mhTarget = lookup.findStatic(Math.class, "max", type);
        MethodHandle mhNew = MethodHandles.filterArguments(mhTarget, 0, mhGetLength, mhGetLength);
        int value = (int) mhNew.invoke("Hello", "New World");
        System.out.println(value);
    }

    public static int targetMethod(int arg1, int arg2, int arg3)
    {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        return arg1;
    }

    public void foldArguments() throws Throwable {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType typeCombiner = MethodType.methodType(int.class, int.class, int.class);
        MethodHandle mhCombiner = lookup.findStatic(Math.class, "max", typeCombiner);
        MethodType typeTarget = MethodType.methodType(int.class, int.class, int.class, int.class);
        MethodHandle mhTarget = lookup.findStatic(Varargs.class, "targetMethod", typeTarget);
        MethodHandle mhResult = MethodHandles.foldArguments(mhTarget, mhCombiner);
        int value = (int) mhResult.invoke(3, 4);
        System.out.println(value);
    }


    public void permuteArguments() throws Throwable {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(int.class, int.class, int.class);
        MethodHandle mhCompare = lookup.findStatic(Integer.class, "compare", type);
        int value = (int) mhCompare.invoke(3, 4);
        System.out.println(value);
        MethodHandle mhNew = MethodHandles.permuteArguments(mhCompare, type, 1, 0);
        value = (int) mhNew.invoke(3, 4);
        System.out.println(value);
        MethodHandle mhDuplicateArgs = MethodHandles.permuteArguments(mhCompare, type, 1, 1);
        value = (int) mhDuplicateArgs.invoke(3, 4);
        System.out.println(value);
    }


    public int handleException(Exception e, String string) {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        System.out.println(e.getMessage());
        return 0;
    }

    public void catchException() throws Throwable {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType typeTarget = MethodType.methodType(int.class, String.class);
        MethodHandle mhParseInt = lookup.findStatic(Integer.class, "parseInt", typeTarget);
        MethodType typeHandler = MethodType.methodType(int.class, Exception.class, String.class);
        MethodHandle mhHandler = lookup.findVirtual(Varargs.class, "handleException", typeHandler).bindTo(this);
        MethodHandle mh = MethodHandles.catchException(mhParseInt, NumberFormatException.class, mhHandler);
        mh.invoke("Hello");
    }

    public static boolean guardTest() {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        return Math.random() > 0.5;
    }

    public void guardWithTest() throws Throwable {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mhTest = lookup.findStatic(Varargs.class, "guardTest", MethodType.methodType(boolean.class));
        MethodType type = MethodType.methodType(int.class, int.class, int.class);
        MethodHandle mhTarget = lookup.findStatic(Math.class, "max", type);
        MethodHandle mhFallback = lookup.findStatic(Math.class, "min", type);
        MethodHandle mh = MethodHandles.guardWithTest(mhTest, mhTarget, mhFallback);
        int value = (int) mh.invoke(3, 5);
        System.out.println(value);

    }

    public void filterReturnValue() throws Throwable {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mhSubstring = lookup.findVirtual(String.class, "substring", MethodType.methodType(String.class, int.class));
        MethodHandle mhUpperCase = lookup.findVirtual(String.class, "toUpperCase", MethodType.methodType(String.class));
        MethodHandle mh = MethodHandles.filterReturnValue(mhSubstring, mhUpperCase);
        String str = (String) mh.invoke("Hello World", 5);
        System.out.println(str);
    }

    public String testMethod(int a, int b) {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        return "apfel";
    }
    public void invoker() throws Throwable {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        MethodType typeInvoker = MethodType.methodType(String.class, Object.class, int.class, int.class);
        MethodHandle invoker = MethodHandles.invoker(typeInvoker);
        MethodType typeFind = MethodType.methodType(String.class, int.class, int.class);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh1 = lookup.findVirtual(String.class, "substring", typeFind);
        MethodHandle mh2 = lookup.findVirtual(Varargs.class, "testMethod", typeFind);
        String result = (String) invoker.invoke(mh1, "Hello", 2, 3);
        System.out.println(result);
        result = (String) invoker.invoke(mh2, this, 2, 3);
        System.out.println(result);
    }

    public void invokerTransform() throws Throwable {
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        MethodType typeInvoker = MethodType.methodType(String.class, String.class, int.class, int.class);
        MethodHandle invoker = MethodHandles.exactInvoker(typeInvoker);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mhUpperCase = lookup.findVirtual(String.class, "toUpperCase", MethodType.methodType(String.class));
        invoker = MethodHandles.filterReturnValue(invoker, mhUpperCase);
        MethodType typeFind = MethodType.methodType(String.class, int.class, int.class);
        MethodHandle mh1 = lookup.findVirtual(String.class, "substring", typeFind);
        String result = (String) invoker.invoke(mh1, "Hello", 1, 4);
        System.out.println(result);
    }
}

