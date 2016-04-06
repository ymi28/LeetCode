/**
 * This problem can be decomposed into two HouseRobberI problems and
 * the solution is max(rob houses 0 to n - 2, rob houses 1 to n - 1)
 * Runtime 1ms
 * 
 * https://leetcode.com/problems/house-robber-ii/
 * 
 * @author ymi28
 *
 */
public class Problem213HouseRobberII {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        
        //Rob houses 0 to n - 2
        int[] value1 = {0, nums[0]};
        for(int i = 1; i < nums.length - 1; i++) {
            int temp = value1[1];
            value1[1] = Math.max(value1[1], value1[0] + nums[i]);
            value1[0] = temp;
        }
        
        //Rob houses 1 to n - 1
        int[] value2 = {0, nums[1]};
        for(int i = 2; i < nums.length; i++) {
            int temp = value2[1];
            value2[1] = Math.max(value2[1], value2[0] + nums[i]);
            value2[0] = temp;
        }
        
        return Math.max(value1[1], value2[1]);
    }
}