package acm;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Administrator on 2016/9/23.
 */
public class LongestIncrementalSequence {
    public static int[] x;
    public static int[] p;
    public static void main(String[] args) {
            int[] s = {6,4,8,2,17};
        int pos = solve(s);
        System.out.println(x[pos]);
        LinkedList<Integer> list = new LinkedList<>();

        do{
            list.addLast(pos);
        }while((pos=p[pos])>=0);

        int length = list.size();
        while(length-->0)
            System.out.println(s[list.removeLast()]);
    }

    public static int solve(int[] arr) {
        x= new int[arr.length];
        p=new int[arr.length];
        Arrays.fill(p,-1);
        int max=0 ,maxp=0;
        for(int i = 0;i<arr.length;i++){
            int sum=0;
            x[i] = 1;

            for(int j = 0;j<i;j++) {
                if(arr[i]>arr[j])
                    sum = x[j]+1;
                if(sum>x[i]){
                    x[i] = sum;
                    p[i] = j;
                }
            }
            if(x[i]>max){
                max = x[i];
                maxp=i;
            }
        }
        return maxp;
    }
}
