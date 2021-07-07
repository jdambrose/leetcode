package hackerrank.interviewprep.arrays.arraymanipulation;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

// https://www.hackerrank.com/challenges/crush/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
class Result {


    /*
     * Complete the 'arrayManipulation' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */

    public static long arrayManipulation(int n, List<List<Integer>> queries) {
    	
    	// Stores data in hashmap
		HashMap<Integer, Long> data = new HashMap<>();
		
		long maxVal = Long.MIN_VALUE;
		int querynum = 0;
    	
    	for(List<Integer> query : queries) {
    		
    		System.out.println("Working on querynum " + querynum++);
    		
    		long queryMax = executeQuery(query, data);
    		if(queryMax > maxVal) {
    			maxVal = queryMax;
    		}
    		
    	}
    	
    	return maxVal;

    }
    
    private static long executeQuery(List<Integer> query, HashMap<Integer, Long> data) {
    	
    	long currentMax = Long.MIN_VALUE;
    	
    	int a = query.get(0);
		int b = query.get(1);
		long num = query.get(2);
		
		for(int i = a; i <= b; i++) {
			
			Long val = data.getOrDefault(i, 0L) + num;
    		data.put(i, val);
    		
			if(val > currentMax) {
				currentMax = val;
			}
		}
		
		return currentMax;
    	
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = Result.arrayManipulation(n, queries);

        System.out.println(String.valueOf(result));

        bufferedReader.close();
    }

}

