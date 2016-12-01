package acm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/30.
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int[] r = twoSum(nums,26);
        System.out.println(r[0]+","+r[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> vals = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer indice = vals.get((target-nums[i]));
            if (indice != null) {
                int[] r = new int[2];
                r[0]=indice;
                r[1]=i;
                return r;
            }
            vals.put(nums[i],i);
        }
        return null;
    }

}
