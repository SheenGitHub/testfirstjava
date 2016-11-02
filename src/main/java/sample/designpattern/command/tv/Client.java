package sample.designpattern.command.tv;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Client {
    public static void main(String[] args) {
        Tv myTv = new Tv();
        CommandOn on = new CommandOn(myTv);
        CommandOff off = new CommandOff(myTv);
        CommandChange channel = new CommandChange(myTv, 2);
        Control control = new Control(on, off, channel);

        control.turnOn();
        control.changeChannel();
        control.turnOff();
    }
}
