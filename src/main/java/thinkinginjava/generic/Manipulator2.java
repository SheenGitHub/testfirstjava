package thinkinginjava.generic;

/**
 * Created by Administrator on 2016/4/22.
 */
public class Manipulator2<T extends HasF> {
    private T obj;
    public Manipulator2(T x){obj = x;}

    public void manipulate() {
        obj.f();
    }
}

class HasF{
    public void f() {

    }
}
