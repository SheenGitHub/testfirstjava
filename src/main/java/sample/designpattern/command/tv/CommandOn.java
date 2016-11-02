package sample.designpattern.command.tv;

/**
 * Created by Administrator on 2016/9/26.
 */
public class CommandOn implements Command {
    private Tv myTv;

    public CommandOn(Tv tv) {
        myTv = tv;
    }
    @Override
    public void execute() {
        myTv.turnOn();
    }
}
