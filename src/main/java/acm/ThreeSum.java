package acm;

import sample.crawler.Link;

import java.util.*;

/**
 * Created by Administrator on 2016/11/30.
 */
public class ThreeSum {
    List<List<Integer>> ret = new ArrayList<>();
    public static void main(String[] args) {
        int[] s ={-1,0,1,2,-1,-4};
        ThreeSum ca = new ThreeSum();
        ca.threeSum(s);
        List<List<Integer>> r = ca.ret;
        for (List<Integer> integers : r) {
            System.out.print("[");
            for (Integer integer : integers) {
                System.out.print(integer+",");
            }
            System.out.println("]");

        }


    }
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums == null||nums.length<3) return ret;
        Arrays.sort(nums);

        int len = nums.length;
        for (int i = 0; i < len-2; i++) {
            if(i>0&&nums[i]==nums[i-1]) continue;
            find(nums,i+1,len-1,nums[i]);
        }
        return ret;
    }

    private void find(int[] nums, int start, int end, int target) {
        int l = start,r=end;
        while (l < r) {
            if (nums[l] + nums[r] + target == 0) {
                List<Integer> ans = new ArrayList<>();
                ans.add(target);
                ans.add(nums[l]);
                ans.add(nums[r]);
                ret.add(ans);
                while (l<r && nums[l]==nums[l+1]) l++;
                while (l<r && nums[r]==nums[r-1])r--;
                l++;
                r--;
            }else if (nums[l]+nums[r]+target<0)
                l++;
            else
                r--;
        }
    }


}
