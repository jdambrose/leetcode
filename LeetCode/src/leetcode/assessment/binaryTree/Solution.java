package leetcode.assessment.binaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	
	public List<Integer> largestValues(TreeNode root) {

		Map<Integer, List<Integer>> map = new HashMap<>();
		convertTreeToMap(root, map, 0);

		List<Integer> solution = new ArrayList<>();
		for (List<Integer> list : map.values()) {

            if(!list.isEmpty()){
                   solution.add(getLargest(list));
               }			

		}


		return solution;
	}

	// Gets the largest Integer out of a list of integers
	private int getLargest(List<Integer> list) {

		int max = Integer.MIN_VALUE;
		for (int i : list) {
			if (i > max) {
				max = i;
			}
		}

		return max;

	}

	// Converts a node to a treeMap
	private Map<Integer, List<Integer>> convertTreeToMap(TreeNode node, Map<Integer, List<Integer>> map, int level) {

		if (!map.containsKey(level)) {
			map.put(level, new ArrayList<>());
		}

        if(node == null){
            return map;
        }
		
		map.get(level).add(node.val);

		if (node.left != null) {
			convertTreeToMap(node.left, map, level + 1);
		}

		if (node.right != null) {
			convertTreeToMap(node.right, map, level + 1);
		}
		
		return map;
	}

	public static void main(String args[]) {

		Solution s = new Solution();

		// ROW 4
		TreeNode node7 = new TreeNode(-1);
		TreeNode node8 = new TreeNode(0);
		TreeNode node9 = null;
		TreeNode node10 = null;
		TreeNode node11 = null;
		TreeNode node12 = null;
		TreeNode node13 = null;
		TreeNode node14 = new TreeNode(-100);

		// ROW 3
		TreeNode node3 = new TreeNode(5, node7, node8);
		TreeNode node4 = new TreeNode(3, node9, node10);
		TreeNode node5 = null;
		;
		TreeNode node6 = new TreeNode(9, node13, node14);

		// ROW 2
		TreeNode node1 = new TreeNode(3, node3, node4);
		TreeNode node2 = new TreeNode(2, node5, node6);

		// ROOT
		TreeNode root = new TreeNode(1, node1, node2);

		List<Integer> largest = s.largestValues(root);
		for (int i = 0; i < largest.size(); i++) {
			System.out.println("Largest of row " + i + " = " + largest.get(i));
		}
	}
}
