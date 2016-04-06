/**
 * https://leetcode.com/problems/house-robber-iii/
 * 
 * @author ymi28
 *
 */
public class Problem337HouseRobberIII {
	public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }
    
    /**
     * DFS the whole tree. For each node, return one number which is the max value while rob the node
     * and another number which is the max value while not rob the node.
     */
    private int[] dfs(TreeNode node) {
        if(node == null) return new int[2];
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        
        int[] cur = new int[2];
        //Rob the current node
        cur[0] = left[1] + right[1] + node.val;
        //Not rob the current bode
        cur[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return cur;
    }
}