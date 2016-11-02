package sample.designpattern.command;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Invoker {
    private Command command = null;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void runCommand() {
        command.execute();
    }
}
