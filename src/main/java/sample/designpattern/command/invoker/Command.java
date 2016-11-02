package sample.designpattern.command.invoker;

/**
 * Created by Administrator on 2016/9/26.
 */
public abstract class Command {
    protected Receiver receiver;

    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    public abstract void ExecuteCommand();
}
