package hackerrank.interviewprep.sorting.toys;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
	
	private static class Node {
		Node right; 
		int val;
	}

    /*
     * Complete the 'maximumToys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY prices
     *  2. INTEGER k
     */

    public static int maximumToys(List<Integer> prices, int k) {
    	
    	int numToys = 0;
    	Node root = sortedList(prices, k);
    	
    	int currentPrice = 0;
    	while(root != null) {
    		
    		if( root.val + currentPrice > k) {
    			return numToys;
    		}else {
    			numToys++;
    			currentPrice = currentPrice+root.val;
    		}
    		
//			System.out.println(root.val + " ->");
    		root = root.right;
    		
    	}
    	
    	
    	return numToys;

    }
    
    private static Node sortedList(List<Integer> prices, int k){
    	
    	Node root = null;
    	for(int i : prices) {
    		
    		if(i > k) { // No need to put it in the list, too big for the budget
    			continue;
    		}else if(root == null){ // Case of first element
    			root = new Node();
    			root.val = i;
    			continue;
    		}else if(root.val > i){ // Case of new root
        		Node newNode = new Node();
        		newNode.val = i;
        		newNode.right = root;
    			root = newNode;
    			continue;
    		}
    		
    		
    		Node node = root;
    		while(node.right != null) {
    			if(node.right.val > i) {
    				break;
    			}
    			node = node.right;
    		}
    		
    		// Didn't find a place to insert, add a new node
    		Node newNode = new Node();
    		newNode.val = i;
    		newNode.right = node.right;
			node.right = newNode;
    		
    	}
    	
    	return root;
    	
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> prices = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.maximumToys(prices, k);

        System.out.println(String.valueOf(result));

        bufferedReader.close();
    }
}
