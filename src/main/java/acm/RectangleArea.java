package acm;

/**
 * Created by Administrator on 2016/9/26.
 */
public class RectangleArea {
    public static void main(String[] args) {
        RectangleArea area = new RectangleArea();
        int r = area.computeArea(-1500000001,0,-1500000000,1,1500000000,0,1500000001,1);
        System.out.println(r);
    }
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C-A)*(D-B);
        int area2 = (G-E)*(H-F);
        int overlap = overlap(A, B,  C,  D,  E,  F,  G,  H);

        return area1 + area2 - overlap;

    }
    public int overlap(int A, int B, int C, int D, int E, int F, int G, int H) {
        int h1 = Math.max(A,E);
        int h2 = Math.min(C,G);

        long h = (long)h2-(long)h1;
        System.out.println(h1+","+h2+","+h);

        int v1 = Math.max(B,F);
        int v2 = Math.min(D,H);

        long v = (long)v2-(long)v1;
        System.out.println(v1+","+v2+","+v);

        System.out.println(v);
        if(h<0||v<0) return 0;
        else return Integer.valueOf(""+h*v);
    }

}
