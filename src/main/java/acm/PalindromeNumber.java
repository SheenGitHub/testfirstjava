package acm;

/**
 * Created by Administrator on 2016/12/1.
 */
public class PalindromeNumber {
    public static void main(String[] args) {
        boolean flag = isPalindrome(-1456541);
        System.out.println(flag);
    }

    public static boolean isPalindrome(int x) {
        if(x<0) return false;
        int len =1;
        while (x/len>=10)
            len*=10;
        int left,right;
        while (x > 0) {
            left = x/len;
            right=x%10;
            if(left!=right)
                return false;
            else{
                x = (x%len)/10;
                len/=100;
            }
        }
        return true;
    }
}
