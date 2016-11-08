package thread.statedependency;

import sample.*;

/**
 * Created by Administrator on 2016/11/8.
 */
public interface Transactor {
    boolean join(Transaction t);

    boolean canCommit(Transaction t);

    void commit(Transaction t) throws Failure;

    void abort(Transaction t);
}
