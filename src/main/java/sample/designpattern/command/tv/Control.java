package sample.designpattern.command.tv;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Control {
    private Command onCommand,offCommand,changeCommand;

    public Control(Command onCommand, Command offCommand, Command changeCommand) {
        this.onCommand = onCommand;
        this.offCommand = offCommand;
        this.changeCommand = changeCommand;
    }

    public void turnOn() {
        onCommand.execute();
    }

    public void turnOff() {
        offCommand.execute();
    }

    public void changeChannel() {
        changeCommand.execute();
    }
}
