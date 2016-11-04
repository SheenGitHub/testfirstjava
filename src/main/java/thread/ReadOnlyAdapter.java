package thread;

/**
 * Created by Administrator on 2016/11/4.
 */
public class ReadOnlyAdapter {

}

class InsufficientFunds extends Exception{}
interface Account{ long balance();}

interface UpdatableAccount extends Account {
    void credit(long amount) throws InsufficientFunds;
    void debit(long amount) throws InsufficientFunds;
}

class UpdatableAccountImpl implements UpdatableAccount {
    private long currentBalance;

    public UpdatableAccountImpl(long currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public synchronized void credit(long amount) throws InsufficientFunds {
        if(amount >= 0 || currentBalance >= -amount)
            currentBalance += amount;
        else
            throw new InsufficientFunds();
    }

    @Override
    public void debit(long amount) throws InsufficientFunds {
        credit(-amount);
    }

    @Override
    public synchronized long balance() {
        return currentBalance;
    }
}

final class ImmutableAccount implements Account {
    private Account delegate;

    public ImmutableAccount(long initialBalance) {
        this.delegate = new UpdatableAccountImpl(initialBalance);
    }

    public ImmutableAccount(Account delegate) {
        this.delegate = delegate;
    }

    @Override
    public long balance() {
        return delegate.balance();
    }
}

class AccountRecorder {
    public void recordBalance(Account a) {
        System.out.println(a.balance());
    }
}

class AccountHolder {
    private UpdatableAccount acct = new UpdatableAccountImpl(0);
    private AccountRecorder recorder;

    public AccountHolder(AccountRecorder recorder) {
        this.recorder = recorder;
    }

    public synchronized void acceptMoney(long amount) {
        try {
            acct.credit(amount);
            recorder.recordBalance(new ImmutableAccount(acct));
        } catch (InsufficientFunds insufficientFunds) {
            System.out.println("Cannot accept negative amount.");
        }
    }
}
