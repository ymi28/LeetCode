import java.util.ArrayList;
import java.util.List;

/**
 * Backtracking + Trie Problem. Runtime 19ms
 * 
 * https://leetcode.com/problems/word-search-ii/
 * 
 * @author ymi28
 *
 */
public class Problem212WordSearchII {
	public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                if(root.next[c - 'a'] != null) {
                    dfs(board, i, j, root.next[c - 'a'], res);
                }
            }
        }
        return res;
    }
    
	/**
	 * Thanks to @yavinci post https://leetcode.com/discuss/77851/easiest-java-solution-98-91
	 * Key code optimization:
	 * 1. Reuse board[][] to check visited.
	 * 2. Store words in Trie nodes.
	 * 
	 */
    private void dfs(char[][] board, int row, int col, TrieNode node, List<String> res) {
        if(node.word != null) {
            res.add(node.word);
            node.word = null;
        }
        
        char c = board[row][col];
        board[row][col] = '#';
        
        //left
        if(col - 1 >= 0 && board[row][col - 1] != '#') {
            int idx = board[row][col - 1] - 'a';
            if (node.next[idx] != null) dfs(board, row, col - 1, node.next[idx], res);
        }
        
        //right
        if(col + 1 < board[0].length && board[row][col + 1] != '#') {
            int idx = board[row][col + 1] - 'a';
            if (node.next[idx] != null) dfs(board, row, col + 1, node.next[idx], res);
        }
        
        //up
        if(row - 1 >= 0 && board[row - 1][col] != '#') {
            int idx = board[row - 1][col] - 'a';
            if (node.next[idx] != null) dfs(board, row - 1, col, node.next[idx], res);
        }
        
        //down
        if(row + 1 < board.length && board[row + 1][col] != '#') {
            int idx = board[row + 1][col] - 'a';
            if (node.next[idx] != null) dfs(board, row + 1, col, node.next[idx], res);
        }
        
        board[row][col] = c;
        
        return;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        
        for(String word: words) {
            TrieNode node = root;
            for(char c: word.toCharArray()) {
                int idx = c - 'a';
                if(node.next[idx] == null) {
                    node.next[idx] = new TrieNode();
                }
                node = node.next[idx];
            }
            node.word = word;
        }
        return root;
    }
    
    class TrieNode {
        TrieNode[] next;
        String word;
        TrieNode() {
            next = new TrieNode[26];
            word = null;
        }
    }
}