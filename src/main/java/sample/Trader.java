package sample;

/**
 * Created by Administrator on 2016/7/6.
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Trader:"+this.name+" in "+this.city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}
