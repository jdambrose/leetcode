package hackerrank.interviewprep.arrays.minimumSwaps2;

import java.io.IOException;
import java.util.Scanner;

// https://www.hackerrank.com/challenges/minimum-swaps-2/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
public class Result {
	
	// Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
    	
    	int swapsNeeded = 0;
    	int i = 1;
    	
    	while(i < arr.length) {
    		
    		// Swap is needed
    		if(arr[i-1] != i) {
    			
    			int swappedVal = arr[arr[i-1] -1];
    			// Sticks the current value into its correct position
    			arr[arr[i-1] -1] = arr[i-1];
    			
    			// Moves that to this place
    			arr[i-1] = swappedVal;
    			
    			// Notes a swap occurred
    			swapsNeeded++;
    			
    		}else {
    			i++;
    		}
    		
    	}
    	
    	return swapsNeeded;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        System.out.println(String.valueOf(res));
        scanner.close();
    }

}
