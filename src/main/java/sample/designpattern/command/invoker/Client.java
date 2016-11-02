package sample.designpattern.command.invoker;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Client {
    public static void main(String[] args) {
        Receiver r = new Receiver();
        Command c = new ConcreteCommand(r);
        Invoker i = new Invoker();
        i.setCommand(c);
        i.ExecuteCommand();
    }
}
