package acm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/7/21.
 */
public class Problem3635 {
    public static final List<Ball> ballList = new ArrayList<>();
    public static final List<City> cityList = new ArrayList<>();
    public static void main(String[] args) {
        int loop;
        int cc;
        int qc;
        Scanner scanner = new Scanner(System.in);
        loop = scanner.nextInt();
//        System.out.println("I:"+loop);
        for (int i = 0; i < loop; i++) {
            System.out.println("Case "+(i+1)+":");
            cc = scanner.nextInt();
            qc = scanner.nextInt();
//            System.out.println("I:"+cc + " " +qc);
            ballList.clear();
            cityList.clear();
            for (int j = 0; j < cc; j++) {
                City city = new City(j);
                Ball ball = new Ball(city, j, j, 0);
                cityList.add(city);
                ballList.add(ball);
            }


            for (int j = 0; j < qc; j++) {
                String type = scanner.next();
                if ("T".equals(type)) {
                    int fc = scanner.nextInt();
                    int tc = scanner.nextInt();
//                    System.out.println("I:"+type + " " + fc + " " + tc);
                    Ball fball = ballList.get(fc-1);
                    City tcity = cityList.get(tc-1);
                    fball.moveTo(tcity);
                } else if ("Q".equals(type)) {
                    int query = scanner.nextInt();
//                    System.out.println("I:"+type + " " + query);
                    Ball qball = ballList.get(query-1);
                    System.out.println((qball.cityno+1) + " " + qball.city.balls.size() + " " + qball.times);
                }
            }
        }
    }
}

class City {
    public int no;
    List<Ball> balls = new ArrayList<>();

    public City(int id) {
        this.no = id;
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public void moveTo(City another) {
        final int cityno = another.no;
        balls.stream().forEach(t->{
            t.cityno =cityno;
            t.times++;
            t.city = another;
        });
        another.balls.addAll(this.balls);
        this.balls.clear();
    }
}
class Ball {
    public int no;
    public int cityno;
    public int times;
    public  City city;

    public Ball(City city,int no, int cityno, int times) {
        this.no = no;
        this.cityno = cityno;
        this.times = times;
        this.city = city;
        this.city.addBall(this);
    }

    public void moveTo(City another) {
            city.moveTo(another);
    }
}
