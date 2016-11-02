package acm;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Administrator on 2016/9/22.
 */
public class ActivitySelector {
    public static Set<Integer> result = new TreeSet<>();
    public static void main(String[] args) {
        int[] s = {1,3,0,5,3,5,6,8,8,2,12};
        int[] f = {4,5,6,7,9,9,10,11,12,14,16};
        long start = System.nanoTime();
        greedSolve(s,f,0,11);
        long end = System.nanoTime();
        System.out.println("Execution Time:"+(end-start)/1000);
        for(int item:result){
            System.out.println("["+s[item]+","+f[item]+"],");
        }
    }
    public static void greedSolve(int[] s, int[] f, int k,int n){
        result.add(0);
        k = 1;
        for(int i=1;i<n;i++){
            if(s[i]>=f[k]){
                result.add(i);
                k = i;
            }
        }
    }
    public static void solve(int[] s, int[] f, int k, int n) {
        result.add(0);
        int m = k+1;
        while(m<n && s[m]<f[k])
            m+=1;
        if(m<n){
            result.add(m);
            solve(s,f,m,n);
        }
    }
}
