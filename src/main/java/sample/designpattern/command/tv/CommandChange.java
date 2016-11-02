package sample.designpattern.command.tv;

/**
 * Created by Administrator on 2016/9/26.
 */
public class CommandChange implements Command {
    private Tv myTv;
    private int channel;
    public CommandChange(Tv myTv ,int channel) {
        this.myTv = myTv;
        this.channel = channel;
    }

    @Override
    public void execute() {
        myTv.changeChannel(channel);
    }
}
