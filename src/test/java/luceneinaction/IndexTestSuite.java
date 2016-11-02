package luceneinaction;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

/**
 * Created by Administrator on 2016/4/21.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        IndexingTest.class,
        IndexerTest.class
})
public class IndexTestSuite {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(IndexingSuiteTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.getMessage());
        }
    }
}
