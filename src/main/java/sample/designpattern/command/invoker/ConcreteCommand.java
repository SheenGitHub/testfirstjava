package sample.designpattern.command.invoker;

/**
 * Created by Administrator on 2016/9/26.
 */
public class ConcreteCommand extends Command {
    public ConcreteCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void ExecuteCommand() {
        receiver.Execute();
    }
}
