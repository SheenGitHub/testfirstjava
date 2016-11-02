package sample.designpattern.command.tv;

/**
 * Created by Administrator on 2016/9/26.
 */
public class CommandOff implements Command {

        private  Tv myTv;

    public CommandOff(Tv myTv) {
        this.myTv = myTv;
    }

    @Override
    public void execute() {
        myTv.turnOff();
    }
}
