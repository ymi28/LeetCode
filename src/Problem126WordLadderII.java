import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author ymi28
 * 
 * https://leetcode.com/problems/word-ladder-ii/
 * 
 * The solution first constructs a graph via BFS and then finds all path via DFS 
 *
 */
public class Problem126WordLadderII {
	Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    List<List<String>> results = new ArrayList<>();
    
    /**
     * Single-end BFS. Runtime 468 ms
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {     
        if (dict.size() == 0) return results;

        boolean reach = false;
        dict.remove(start);
        
        Queue<String> queue = new ArrayDeque<String>();
        queue.add(start);
        
        while (!queue.isEmpty()) {
        	int l = queue.size();
        	
        	for(int i = 0; i < l; i++) {
        		String word = queue.remove();

        		for (int j = 0; j < word.length(); j++) {
        			StringBuilder sb = new StringBuilder(word); 
        			for (char ch = 'a';  ch <= 'z'; ch++) {
        				
        				sb.setCharAt(j, ch);
        				String newWord = sb.toString();
        				if (dict.contains(newWord)) {
        					queue.add(newWord);
        					if(newWord.equals(end)) {
        						reach = true;
        					}
        					addToMap(word, newWord);
        				}
        			}
        		}
        	}
        	for(String str: queue) {
        		dict.remove(str);
        	}
            if (reach) break;
        }

        getPath(start, end, new ArrayList<String>());

        return results;        
    }
    
    /**
     * Two-end BFS. Runtim 31 ms
     */
    public List<List<String>> findLaddersTwoEndBFS(String start, String end, Set<String> dict) {
    	boolean reach = false, isFromStartSet = true;
    	Set<String> startSet = new HashSet<>();
		Set<String> endSet = new HashSet<>();
		startSet.add(start);
		endSet.add(end);
		
		while(!startSet.isEmpty() && !endSet.isEmpty()) {
			if (startSet.size() > endSet.size()) {
				Set<String> temp = startSet;
				startSet = endSet;
				endSet = temp;
				isFromStartSet = !isFromStartSet;
			}
			
			for(String str: startSet) {
				dict.remove(str);
			}
			for(String str: endSet) {
				dict.remove(str);
			}
			
			Set<String> nextSet = new HashSet<>();
			for(String word: startSet) {
				char[] chars = word.toCharArray();
				for(int i = 0; i < word.length(); i++) {
					char temp = chars[i];
					for(char c = 'a'; c <= 'z'; c++) {
						chars[i] = c;
						String newWord = String.valueOf(chars);
						
						if(endSet.contains(newWord)) {
							reach = true;
							if(isFromStartSet) {
								addToMap(word, newWord);
							} else {
								addToMap(newWord, word);
							}
						} else if (!reach && dict.contains(newWord)) {
							nextSet.add(newWord);
							if(isFromStartSet) {
								addToMap(word, newWord);
							} else {
								addToMap(newWord, word);
							}
						}
					}
					chars[i] = temp;
				}
			}
			if(reach) {
				getPath(start, end, new ArrayList<String>());
				return results;
			}
			startSet = nextSet;
		}
		
    	return results;
    }
    
    /**
     * Find all transformation sequences
     */
    private void getPath(String word, String end, List<String> list) {
        if (word.equals(end)){
            list.add(end);
            results.add(new ArrayList<String>(list));
            list.remove(list.size() - 1);
            return;
        }
        
        list.add(word);
        if (map.get(word) != null) {
            for (String s: map.get(word)) {
            	getPath(s, end, list);
            }
        }
        list.remove(list.size() - 1);
    }
    
    /**
     * Add an edge to the graph
     */
    private void addToMap(String from, String to) {
    	if (map.containsKey(from)) map.get(from).add(to);
		else {
			Set<String> list = new HashSet<String>();
			list.add(to);
			map.put(from, list);
		}
    }
    
    public static void main(String[] args) {
    	Problem126WordLadderII solution = new Problem126WordLadderII();
    	
    	//expected output: [[red, ted, tex, tax], [red, ted, tad, tax], [red, rex, tex, tax]]
    	String beginWord = "red";
    	String endWord = "tax";
    	String[] str = {"ted","tex","red","tax","tad","den","rex","pee"};
    	
    	System.out.println(solution.findLaddersTwoEndBFS(beginWord, endWord, new HashSet<>(Arrays.asList(str))));
    }
}
