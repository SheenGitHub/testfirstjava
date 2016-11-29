package acm;

import java.util.Collections;

/**
 * Created by Administrator on 2016/9/27.
 */
public class NextPermutation implements Problem {

    private int[] arr;

    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
//        NextPermutation permutation = new NextPermutation(arr);
//        permutation.reverse(arr,0,2);
//        permutation.printResult();
        recursivePermutation(arr,0);
    }

    public NextPermutation(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void solve() {
        nextPermutation(arr);
    }

    @Override
    public void printResult() {
        for (Integer item : arr) {
            System.out.print(item+" ");
        }
        System.out.println();
    }


    public void nextPermutation(int[] nums) {
        int i ,j;
        for(i = nums.length-2;i>=0;i--) {
            if(nums[i]<nums[i+1])
                break;
        }
        if(i<0){
            reverse(nums,0,nums.length-1);
            return;
        }
        for( j = nums.length-1;j>i;j--) {
            if(nums[j]>nums[i]){
                swap(nums,i,j);
                break;
            }
        }
        reverse(nums,i+1,nums.length-1);
    }

    void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums,start,end);
            start++;
            end--;
        }
    }

    static void swap(int[] a, int x, int y) {
        if(x!=y){
            a[x]^=a[y];
            a[y]^=a[x];
            a[x]^=a[y];
        }

    }

    public static void recursivePermutation(int[] arr,int i){
        int l = arr.length;
        if(i==l-1){
            for (int item : arr)
                System.out.print(item+" ");
            System.out.println();
            return;
        }
        else{
            for (int j = i; j <l ; j++) {
                swap(arr,i,j);
                recursivePermutation(arr,i+1);
                swap(arr,i,j);
            }
        }

    }

}
