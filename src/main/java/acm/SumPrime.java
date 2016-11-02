package acm;

/**
 * Created by Administrator on 2016/9/21.
 */
public class SumPrime {
    public static int sum,num,count,limit;
    public static int[] a = {3,7,12,19};
    public static void main(String[] args) {
        int r = solve(a,3);
        System.out.println(r);
    }
    public static boolean isPrime(int x){
        if(x==2) return true;
        for(int i =2;i<= (int)Math.sqrt(x);i++)
            if(x%i == 0)
                return false;
        return true;
    }
    public static int solve(int[] arr,int k){
        count=0;
        sum = 0;
        num = 0;
        limit = k;
        dfs(arr,0);
        return count;
    }

    public static void dfs(int[] arr,int x){
        if(x>= arr.length) return ;
        else{

            sum = sum+arr[x];
            num++;
            if(num<limit)
                dfs(arr,x+1);
            else
                if(isPrime(sum))
                    count++;
            sum = sum -arr[x];
            num--;
            dfs(arr,x+1);
        }

    }
}
