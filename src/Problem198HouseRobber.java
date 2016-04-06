/**
 * Dynamic programming problem. Rumtime 0ms
 * 
 * https://leetcode.com/problems/house-robber/
 * 
 * @author ymi28
 *
 */
public class Problem198HouseRobber {
	public int rob(int[] num) {
        if(num.length == 0) return 0;
        
        int[] value = new int[2];
        value[0] = 0;
        value[1] = num[0];
        
        for(int i = 1; i < num.length; i++) {
            int temp = value[1];
            value[1] = Math.max(value[1], value[0] + num[i]);
            value[0] = temp;
        }
        return value[1];
    }
}