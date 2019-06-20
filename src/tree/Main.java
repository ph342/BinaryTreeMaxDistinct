package tree;

import java.util.*;

class Solution {
    public int solution(Tree T) {
		// call Preorder traversal with the root node
		return countNode(T);		
    }

	// wrapper method for the recursive method
	private int countNode(Tree t) {
		// if the tree is null, exit immediately
		if (t == null) 
	        return 0; 
	  
	    // map that stores value and corresponding counts
	    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	  
	    // call recursive method
	    return countNodesUntil(t, map); 
	}
	
	// recursive method for Preorder traversal
	private int countNodesUntil(Tree t, HashMap<Integer, Integer> map) {

		// if we reached a leaf, exit with the existing map
		if (t == null) 
	        return map.size(); 
	  
	    // put this tree node into the map, and increment the occurrence
		int count = map.containsKey(t.x) ? map.get(t.x) : 0;
		count++;
		map.put(t.x, count);

	    // calculate the maximum of below trees by calling the function recursively
		int maxPath = Math.max(countNodesUntil(t.l, map), countNodesUntil(t.r, map)); 
	  
	    // fix: remove current node from the path (get() cannot be null here)
	    map.put(t.x, map.get(t.x) - 1);
	    
	    // never leave 0-occurrences in the map
	    if (map.get(t.x) == 0) 
	        map.remove(t.x); 
	  
	    return maxPath; 
	}
}