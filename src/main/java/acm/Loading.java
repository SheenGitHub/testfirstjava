package acm;

import java.util.LinkedList;

/**
 * Created by Administrator on 2016/9/23.
 */
public class Loading {
    public static void main(String[] args) {
        int[] w = {0,20,10,26,15};
        int result = maxLoading(w,70,4);
        System.out.println(result);
    }
    public static int maxLoading(int[] w, int c, int n){
        LinkedList<Integer> Q = new LinkedList<>();
        Q.add(-1);
        int r = 0;
        for(int i = 2;i<w.length;i++)
            r+=w[i];

        int i = 1;
        int ew = 0;
        int bestw = 0;

        while (true) {
            int wt = ew + w[i];
            if(wt < c){
                if(wt>bestw)
                    bestw = wt;
                if(i<n)
                    Q.add(wt);
            }

            if(ew+r>bestw&&i<n)
                Q.add(ew);
            ew = Q.removeFirst();
            if(ew==-1){
                if(Q.isEmpty()) return bestw;
                Q.add(-1);
                ew = Q.removeFirst();
                i++;
                r-=w[i];
            }
        }
    }
}
