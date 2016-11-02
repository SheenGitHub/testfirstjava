package sample.designpattern.command;

/**
 * Created by Administrator on 2016/9/26.
 */
public class ConcreteCommand implements Command {
    private Receiver receiver = null;
    private String state;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
