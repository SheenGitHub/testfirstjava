package sample;

/**
 * Created by Administrator on 2016/7/4.
 */
public class Apple{
    private String color;
    private Integer weight;

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple(Integer weight) {
        this.color ="red";
        this.weight = weight;
    }

    public Apple() {
        this.color = "red";
        this.weight = 100;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    @Override
    public String toString() {
        return "Apple:" + color + "," + weight + ".";
    }

}
