package acm;

import java.util.Scanner;

/**
 * Created by Administrator on 2016/11/9.
 */
public class Main {
    static int[][] r;
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            String s = cin.next();
            System.out.println(result(s));
        }
    }

    static int result(String s){
        int l = s.length();
        int t;
        r = new int[l][l];
        for (int i = 0; i < l; i++)
            r[i][i] = 1;

        for(int i =1; i < l;i++) {
            t = 0;
            for(int j = 0; j+i<l;j++) {
                if(s.charAt(j)==s.charAt(j+i)){
                    if(i>1)
                    t = r[j+1][j+i-1]+2;
                    else
                        t = 2;
                }else if(r[j+1][j+i]>=r[j][j+i-1])
                    t=r[j+1][j+i];
                else
                    t=r[j][j+i-1];
                r[j][j+i] = t;
                System.out.println(j+","+(j+i)+":"+t);
            }
        }
        return r[0][l-1];
    }
}
