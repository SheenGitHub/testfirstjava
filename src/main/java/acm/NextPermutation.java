package acm;

import java.util.Collections;

/**
 * Created by Administrator on 2016/9/27.
 */
public class NextPermutation implements Problem {

    private int[] arr;

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        NextPermutation permutation = new NextPermutation(arr);
        permutation.reverse(arr,0,2);
        permutation.printResult();
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

    void swap(int[] a, int x, int y) {
        a[x]^=a[y];
        a[y]^=a[x];
        a[x]^=a[y];
    }
}
