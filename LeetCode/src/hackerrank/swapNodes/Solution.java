package hackerrank.swapNodes;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

// https://www.hackerrank.com/challenges/swap-nodes-algo/problem
class Result {

	// Node in a Tree
	public static class Node {
		Node right;
		Node left;
		int index = Integer.MIN_VALUE;

		public Node() {
		}
	}

	/*
	 * Complete the 'swapNodes' function below.
	 *
	 * The function is expected to return a 2D_INTEGER_ARRAY. The function accepts
	 * following parameters: 1. 2D_INTEGER_ARRAY indexes 2. INTEGER_ARRAY queries
	 */

	public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {

		List<List<Integer>> solution = new ArrayList<>();

		// Sets the incoming map as a TreeMap
		Node root = getRoot(indexes);
		System.out.println("Current order : " + orderedTraversal(root).toString());

		for (int i : queries) {

			// Swaps the children based on the query
			swap(root, i, 1);
			System.out.println("Swap on query " + i + ": " + orderedTraversal(root).toString());

			solution.add(orderedTraversal(root));

		}

		return solution;
	}

	// Gets the root of a tree with values set as the Map of Integers
	private static Node getRoot(List<List<Integer>> indexes) {

		Node root = new Node();
		root.index = 1;

		Queue<Node> nextToPopulate = new LinkedList<>();
		nextToPopulate.add(root);

		Queue<List<Integer>> data = new LinkedList<>();
		data.addAll(indexes);

		while (!data.isEmpty() && !nextToPopulate.isEmpty()) {

			List<Integer> list = data.poll();
			Node node = nextToPopulate.poll();

			setData(node, list);
			if (node.left.index != -1) {
				nextToPopulate.add(node.left);
			}
			if (node.right.index != -1) {
				nextToPopulate.add(node.right);
			}

		}

		return root;

	}

	// Sets the children data of a node with the integers in the list
	private static void setData(Node root, List<Integer> data) {

		// Searches for the next available spot to put data
		root.left = new Node();
		root.left.index = data.get(0);
		root.right = new Node();
		root.right.index = data.get(1);

	}

	// Swaps the nodes based on the query
	private static void swap(Node root, int query, int depth) {

		if (depth % query == 0) {

			Node left = root.left;
			Node right = root.right;

			root.left = right;
			root.right = left;

		}

		if (root.left.index != -1) {
			swap(root.left, query, depth + 1);
		}

		if (root.right.index != -1) {
			swap(root.right, query, depth + 1);
		}

	}

	// Turns a tree into an ordered traversal as described in the problem
	private static List<Integer> orderedTraversal(Node root) {

		List<Integer> ordered = new ArrayList<>();
		if (root.left.index != -1) {
			ordered.addAll(orderedTraversal(root.left));
		}

		if (root.index != -1) {
			ordered.add(root.index);
		}

		if (root.right.index != -1) {
			ordered.addAll(orderedTraversal(root.right));
		}

		return ordered;
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		List<List<Integer>> indexes = new ArrayList<>();

		IntStream.range(0, n).forEach(i -> {
			try {
				indexes.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList()));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).map(String::trim).map(Integer::parseInt).collect(toList());

		List<List<Integer>> result = Result.swapNodes(indexes, queries);

		result.stream().map(r -> r.stream().map(Object::toString).collect(joining(" "))).map(r -> r + "\n")
				.collect(toList()).forEach(e -> {
					try {
						bufferedWriter.write(e);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				});

		bufferedReader.close();
		bufferedWriter.close();
	}
}
