package thinkinginjava.concurrency;

import org.apache.xalan.xslt.EnvironmentCheck;

/**
 * Created by Administrator on 2016/8/9.
 */
public class EvenGenerator extends IntGenerator{
    private int currentEvenValue = 0;

    @Override
    public int next() {
        ++currentEvenValue;
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
