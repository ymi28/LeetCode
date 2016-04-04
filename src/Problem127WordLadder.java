import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author ymi28
 * 
 * https://leetcode.com/problems/word-ladder/
 *
 */
public class Problem127WordLadder {
	/** Solve this problem in a single-end BFS way
	 * Runtime: 98 ms
	 */
	public int ladderLengthSingleEnd(String beginWord, String endWord, Set<String> wordDict) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1;
        
        while(!queue.isEmpty()) {
            int l = queue.size();
            for(int i = 0; i < l; i++) {
                String s = queue.remove();
                char[] chars = s.toCharArray();
                for (int j = 0; j < beginWord.length(); j++) {
                	char temp = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[j] = c;
                        String str = String.copyValueOf(chars);
                        if (str.equals(endWord)) {
                            return level + 1;
                        }
                        
                        if (wordDict.contains(str)) {
                            queue.add(str);
                            wordDict.remove(str);
                        }
                    }
                    chars[j] = temp;
                }
            }
            level++;
        }
        return 0;
    }
	
	/** Solve this problem in a two-end BFS way
	 * Runtime: 24 ms
	 */
	public int ladderLengthTwoEnd(String beginWord, String endWord, Set<String> wordDict) {
		Set<String> beginSet = new HashSet<>();
		Set<String> endSet = new HashSet<>();
		int level = 1;
		beginSet.add(beginWord);
		endSet.add(endWord);
		
		while(!beginSet.isEmpty() && !endSet.isEmpty()) {
			if (beginSet.size() > endSet.size()) {
				Set<String> temp = beginSet;
				beginSet = endSet;
				endSet = temp;
			}
			Set<String> nextSet = new HashSet<>();
			for(String word: beginSet) {
				char[] chars = word.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					char temp = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String str = String.copyValueOf(chars);
                        
                        if (endSet.contains(str)) {
                            return level + 1;
                        }
                        
                        if (wordDict.contains(str)) {
                        	nextSet.add(str);
                        	wordDict.remove(str);
                        }
                    }
                    chars[i] = temp;
				}
			}
			beginSet = nextSet;
			level++;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Problem127WordLadder solution = new Problem127WordLadder();
		String beginWord = "hit";
		String endWord = "cog";
		String[] str = {"hot","dot","dog","lot","log"};
		Set<String> wordDict = new HashSet<>(Arrays.asList(str));
		
		System.out.println(solution.ladderLengthSingleEnd(beginWord, endWord, new HashSet<String>(wordDict)));
		System.out.println(solution.ladderLengthTwoEnd(beginWord, endWord, new HashSet<String>(wordDict)));
	}
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
