package hackerrank.levelOrderTraversal;

import java.util.*;
import java.io.*;

class Node {
	Node left;
	Node right;
	int data;

	Node(int data) {
		this.data = data;
		left = null;
		right = null;
	}
}

// https://www.hackerrank.com/challenges/tree-level-order-traversal/problem
class Solution {

	/*
	 * 
	 * class Node int data; Node left; Node right;
	 */
	public static void levelOrder(Node root) {

		// Map accessed by <Level, Integers>
		HashMap<Integer, ArrayList<Integer>> map = getTreeMap(root);
		for(List<Integer> list : map.values()) {
			for(int i : list) {
				System.out.print(i + " ");
			}
		}

	}

	private static HashMap<Integer, ArrayList<Integer>> getTreeMap(Node root) {

		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		if (root != null) {
			populateMap(map, root, 0);
		}
		return map;

	}

	private static void populateMap(Map<Integer, ArrayList<Integer>> map, Node node, int level) {

		if (!map.containsKey(level)) {
			map.put(level, new ArrayList<>());
		}

		map.get(level).add(node.data);

		// Must do node left first, otherwise, it won't be ordered
		if (node.left != null) {
			populateMap(map, node.left, level + 1);
		}

		if (node.right != null) {
			populateMap(map, node.right, level + 1);
		}

	}

	public static Node insert(Node root, int data) {
		if (root == null) {
			return new Node(data);
		} else {
			Node cur;
			if (data <= root.data) {
				cur = insert(root.left, data);
				root.left = cur;
			} else {
				cur = insert(root.right, data);
				root.right = cur;
			}
			return root;
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		Node root = null;
		while (t-- > 0) {
			int data = scan.nextInt();
			root = insert(root, data);
		}
		scan.close();
		levelOrder(root);
	}
}
