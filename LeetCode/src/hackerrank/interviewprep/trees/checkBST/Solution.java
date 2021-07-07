package hackerrank.interviewprep.trees.checkBST;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.IntStream;

// https://www.hackerrank.com/challenges/ctci-is-binary-search-tree/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=trees
// There is something broken with the problem (:
public class Solution {

	class Node {
		int data;
		Node left;
		Node right;
	}

	static boolean checkBST(Node root) {

		// Pretends root is off an imaginary impossible Node
		return checkBST(root, -100000, false);

	}

	static boolean checkBST(Node root, int parentVal, boolean isLeft) {

		if (root.left != null) {

			if (root.left.data >= root.data) {
				return false;
			}

			if (root.left.data >= parentVal && isLeft) {
				return false;
			}

			if (root.left.data <= parentVal && !isLeft) {
				return false;
			}

			if (!checkBST(root.left, root.data, true)) {
				return false;
			}

		}

		if (root.right != null) {

			if (root.right.data <= root.data) {
				return false;
			}

			if (root.right.data >= parentVal && isLeft) {
				return false;
			}

			if (root.right.data <= parentVal && !isLeft) {
				return false;
			}

			if (!checkBST(root.right, root.data, false)) {
				return false;
			}

		}

		return true;

	}
}
