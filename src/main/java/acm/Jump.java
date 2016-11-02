package acm;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Jump {
    public static void main(String[] args) {
        int[] A = {2,3,1,1,4};
        Jump jump = new Jump();

        System.out.println(jump.canJump(A));

        int[] B = {3,2,1,0,4};
        System.out.println(jump.canJump(B));

        int[] C ={0};
        System.out.println(jump.canJump(C));

        int[] D={2,0};
        System.out.println(jump.canJump(D));
    }

    public boolean canJump(int[] nums) {
        int size = nums.length;
        if(size <= 0) return false;
        int maxJump = -1;
        for (int i = 0; i<size ; i++) {
            if(nums[i] > maxJump)
                maxJump = nums[i];

            if(maxJump+i>=size-1)
                return true;

            if(maxJump==0)
                return false;

            maxJump--;
        }
        return false;
    }
}
