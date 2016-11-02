package sample.designpattern.command.invoker;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void ExecuteCommand() {
        command.ExecuteCommand();
    }
}
