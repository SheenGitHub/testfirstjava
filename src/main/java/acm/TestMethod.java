package acm;


/**
 * Created by Administrator on 2016/9/27.
 */
public class TestMethod {
    public int LastRem(int n, int m,int i) {
        if(i==1)
            return (n+m-1)%n;
        else
            return (LastRem(n-1,m,i-1)+m)%n;
    }

    public int test() {
        try {
            int i = 0 / 0;
            return 1;

        } catch (Exception e) {
            return 2;
        }
    }

    public static void main(String[] args) {
        TestMethod cls = new TestMethod();
        for(int i=1;i<=78;i++)
            System.out.println(cls.LastRem(78,4,i)+1);

    }
}
