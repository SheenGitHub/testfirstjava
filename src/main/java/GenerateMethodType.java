import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SwitchPoint;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/26.
 */
public class GenerateMethodType {
    public static void main(String[] args) throws Throwable {
        GenerateMethodType generateMethodType = new GenerateMethodType();
        generateMethodType.invokeExact();


    }
    public void generateMethodTypes() {
        //String.length()
        MethodType mt1 = MethodType.methodType(int.class);
        //String.concat(String str)
        MethodType mt2 = MethodType.methodType(String.class, String.class);
        //String.getChars(int strBegin,int srcEnd, char[] dst, int dstBegin)
        MethodType mt3 = MethodType.methodType(Void.class, int.class, int.class, char[].class, int.class);
        //String.startsWith(String prefix)
        MethodType mt4 = MethodType.methodType(boolean.class, mt2);
    }

    public void generateGenericMethodTypes() {
        MethodType mt1 = MethodType.genericMethodType(3);
        MethodType mt2 = MethodType.genericMethodType(2, true);
    }

    public void generateMethodTypesFromDescriptor() {
        ClassLoader cl = getClass().getClassLoader();
        String descrptor = "(Ljava/lang/String;)Ljava/langString;";
        MethodType mt1 = MethodType.fromMethodDescriptorString(descrptor, cl);

    }

    public void changeMethodType() {
        //(int,int)String
        MethodType mt = MethodType.methodType(String.class, int.class, int.class);
        //(int,int,float)String
        mt = mt.appendParameterTypes(float.class);
        //(int,double,long,int,float)String
        mt = mt.insertParameterTypes(1, double.class, long.class);
        //(int,double,int,float)String
        mt = mt.dropParameterTypes(2,3);
        //(int,double,String,float)String
        mt = mt.changeParameterType(2, String.class);
        //(int,double,String,float)void
        mt = mt.changeReturnType(void.class);
    }

    public void invokeExact() throws Throwable{
        System.out.println("Current Invoking Method =====> "+Thread.currentThread().getStackTrace()[1].getMethodName()+":");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class, int.class, int.class);
        MethodHandle mh = lookup.findVirtual(String.class, "substring", type);
        String str = (String) mh.invokeExact("HelloWorld", 1, 3);
        System.out.println(str);

    }





















}
