package sample.designpattern.command;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Client {
    public void assemble() {
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
    }
}
