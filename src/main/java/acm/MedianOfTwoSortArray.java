package acm;

/**
 * Created by Administrator on 2016/11/29.
 */
public class MedianOfTwoSortArray {
    public static void main(String[] args) {
        int[] a = new int[]{1,2};
        int[] b = new int[]{3,4};
        double median = findMedianSortedArrays(a,b);
        System.out.println(median);

    }

    static double findkth(int[] a, int as, int al, int[] b, int bs, int bl, int k) {
        if(al > bl)
            return findkth(b,bs,bl,a,as,al,k);
        if(al == 0)
            return b[bs+k-1];
        if(k==1)
            return min(a[as],b[bs]);
        int pa = min(k/2,al),pb = k-pa;
        if(a[as+pa-1]<b[bs+pb-1])
            return findkth(a,as+pa,al-pa,b,bs,bl,k-pa);
        else if(a[as+pa-1]>b[bs+pb-1])
            return findkth(a,as,al,b,bs+pb,bl-pb,k-pb);
        else
            return a[as+pa-1];
    }

    static int min(int a, int b) {
        return a<=b?a:b;
    }

    static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length+nums2.length;
        if((total&1)!=0)
            return findkth(nums1,0,nums1.length,nums2,0,nums2.length,total/2+1);
        else
            return (findkth(nums1,0,nums1.length,nums2,0,nums2.length,total/2)+
                    findkth(nums1,0,nums1.length,nums2,0,nums2.length,total/2+1))/2;
    }


}
