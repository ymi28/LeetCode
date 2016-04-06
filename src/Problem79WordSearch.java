/**
 * Backtracking problem. Runtime 9ms
 * 
 * https://leetcode.com/problems/word-search/
 * 
 * @author ymi28
 *
 */
public class Problem79WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null) return false;
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        char[] chars = word.toCharArray();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == chars[0]) {
                    if(find(board, i, j, chars, 1, visited)) return true; 
                }
            }
        }
        return false;
    }
    
    private boolean find(char[][] board, int row, int col, char[] target, int idx, boolean[][] visited) {
        if (idx == target.length) return true;
        visited[row][col] = true;
        //left
        if(col - 1 >= 0 && target[idx] == board[row][col - 1] && !visited[row][col - 1] && find(board, row, col - 1, target, idx + 1, visited)) return true;
        //right
        if(col + 1 < board[0].length && target[idx] == board[row][col + 1] && !visited[row][col + 1] && find(board, row, col + 1, target, idx + 1, visited)) return true;
        //up
        if(row - 1 >= 0 && target[idx] == board[row - 1][col] && !visited[row - 1][col] && find(board, row - 1, col, target, idx + 1, visited)) return true;
        //down
        if(row + 1 < board.length && target[idx] == board[row + 1][col] && !visited[row + 1][col] && find(board, row + 1, col, target, idx + 1, visited)) return true;
        visited[row][col] = false;
        return false;
    }
}