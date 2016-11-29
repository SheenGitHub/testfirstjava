package acm;

/**
 * Created by Administrator on 2016/11/29.
 */
public class ReverseArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11};
        reverseArray(arr);
        for (int item : arr) {
            System.out.println(item);
        }
    }
    /*
    only works well for even number, for odd length array,
    after the  1st iteration, the middle position is 0,
    And it would always be 0 afterwards
     */
    public static void reverseArray(int[] array) {
        int length = array.length;

        for (int i = 0; i < length; i++) {
            if((length&1)==0||i!=length/2)
                array[i] ^=array[length-1-i];
        }
        for (int i = 0; i < length/2; i++) {
            array[i] ^=array[length-1-i];
        }
    }
}
