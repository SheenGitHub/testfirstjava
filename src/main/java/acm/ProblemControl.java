package acm;

/**
 * Created by Administrator on 2016/9/26.
 */
public class ProblemControl {
    private Problem problem;

    private int degree = 1;

    public ProblemControl(Problem problem) {
        this.problem = problem;
    }

    public void setSecond() {
        degree = 1000;
    }

    public void solve() {
        long start = System.nanoTime();
        problem.solve();
        long end = System.nanoTime();
        System.out.println("Time Consumed:" +(end - start));
        problem.printResult();
    }

}
