package acm;

/**
 * Created by Administrator on 2016/9/22.
 */
public class Framework {
    public static void main(String[] args) {

        Problem problem = new Palindrome("aab");
        ProblemControl control = new ProblemControl(problem);
        control.solve();
    }
}
