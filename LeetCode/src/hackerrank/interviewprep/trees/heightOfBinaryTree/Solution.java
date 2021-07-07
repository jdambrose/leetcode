package hackerrank.interviewprep.trees.heightOfBinaryTree;

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

// https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree/problem
class Solution {

	/*
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
	public static int height(Node root) {
		
		return getMaxHeight(root, -1);
    }
	
	private static int getMaxHeight(Node root, int currentHeight) {
		
		if(root == null) {
			return currentHeight;
		}
		
		int leftHeight = getMaxHeight(root.left, currentHeight+1);
		int rightHeight = getMaxHeight(root.right, currentHeight+1);	
		
		return Math.max(leftHeight, rightHeight);
		
	}

	public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
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
        int size = scan.nextInt();
        Node root = null;
        while(size-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
    }	
}