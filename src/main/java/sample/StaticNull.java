package sample;

/**
 * Created by Administrator on 2016/8/5.
 */
public class StaticNull {
    public static void action() {
        System.out.println("Success");
    }

    public static void main(String[] args) {
        ((StaticNull)null).action();
        if (null instanceof StaticNull) {
            System.out.println("Succeed");
        }

        if (((StaticNull)null) instanceof StaticNull) {
            System.out.println("Succeed");
        }
    }
}
