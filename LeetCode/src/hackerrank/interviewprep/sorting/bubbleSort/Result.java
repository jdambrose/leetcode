package hackerrank.interviewprep.sorting.bubbleSort;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

// https://www.hackerrank.com/challenges/ctci-bubble-sort/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
class Result {

    /*
     * Complete the 'countSwaps' function below.
     *
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static void countSwaps(List<Integer> a) {
    	
    	int numSwaps = 0;
    	for (int i = 0; i < a.size(); i++) {
    	    
    	    for (int j = 0; j < a.size() - 1; j++) {
    	        // Swap adjacent elements if they are in decreasing order
    	        if (a.get(j) > a.get(j+1)) {
    	        	numSwaps++;
    	            swap(a, j, j+1);
    	        }
    	    }
    	    
    	}
    	
    	System.out.println("Array is sorted in " + numSwaps + " swaps.  \r\n"
    			+ "First Element: " + a.get(0) + "\r\n"
    			+ "Last Element: " + a.get(a.size() -1) + "\n");

    }
    
    private static void swap(List<Integer> a, int i, int j) {
    	int atI = a.get(i);
    	int atJ = a.get(j);
    	a.set(j, atI);
    	a.set(i, atJ);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.countSwaps(a);

        bufferedReader.close();
    }
}
