package acm;

/**
 * Created by Administrator on 2016/9/26.
 */
public class LcsProblem implements Problem {
    private LCS lcs;

    public LcsProblem(LCS lcs) {
        this.lcs = lcs;
    }

    @Override
    public void solve() {
       lcs.solve();
    }

    @Override
    public void printResult() {
        lcs.printResult();
    }

}
