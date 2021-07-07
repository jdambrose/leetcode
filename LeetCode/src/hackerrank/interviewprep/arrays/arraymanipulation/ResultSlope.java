package hackerrank.interviewprep.arrays.arraymanipulation;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// Same problem, this was not my original approach, but I took a hint for solving using slope and figured a valley approach might work: 
/*
 * After contemplating the popular approach for solving this, here is how I wrapped my head around it.
 * 
 * For every input line of a-b-k, you are given the range (a to b) where the values increase by k. 
 * So instead of keeping track of actual values increasing, just keep track of the rate of change 
 * (i.e. a slope) in terms of where the rate started its increase and where it stopped its increase. 
 * This is done by adding k to the "a" position of your array and adding -k to the "b+1" position of 
 * your array for every input line a-b-k, and that's it. "b+1" is used because the increase still applied at "b".
 * 
 * The maximum final value is equivalent to the maximum accumulated "slope" starting from the first position, 
 * because it is the spot which incremented more than all other places. Accumulated "slope" means to you add 
 * slope changes in position 0 to position 1, then add that to position 2, and so forth, looking for the point where 
 * it was the greatest.
 */
public class ResultSlope {

	/*
	 * Complete the 'arrayManipulation' function below.
	 *
	 * The function is expected to return a LONG_INTEGER. The function accepts
	 * following parameters: 1. INTEGER n 2. 2D_INTEGER_ARRAY queries
	 */

	public static long arrayManipulation(int n, List<List<Integer>> queries) {

		// Stores data in hashmap
		HashMap<Integer, Long> data = new HashMap<>();

		for (List<Integer> query : queries) {
			executeQuery(query, data);
		}

		// Once all slopes are added, get the maximum
		return getMax(n, data);

	}

	private static void executeQuery(List<Integer> query, HashMap<Integer, Long> data) {

		int a = query.get(0);
		int b = query.get(1);
		long num = query.get(2);

		// Marks the increase
		Long valIncrease = data.getOrDefault(a, 0L) + num;
		data.put(a, valIncrease);

		// Marks the increase
		Long valDecrease = data.getOrDefault(b + 1, 0L) - num;
		data.put(b + 1, valDecrease);

	}

	private static long getMax(int n, HashMap<Integer, Long> data) {

		// k great or equal to 0, min value will be 0
		long max = 0;
		long currentVal = 0;

		/*
		 * This is where I got stuck, I was doing long l : data.getValues
		 * TURNS OUT maps do not return values in order of their keys (:
		 */
		for (int i = 0; i <= n; i++) {
			long change = data.getOrDefault(i, 0L);
			currentVal = currentVal + change;

			// If current position is greater, set as new max
			if (currentVal > max) {
				max = currentVal;
			}
		}

		return max;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int m = Integer.parseInt(firstMultipleInput[1]);

		List<List<Integer>> queries = new ArrayList<>();

		IntStream.range(0, m).forEach(i -> {
			try {
				queries.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList()));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		long result = ResultSlope.arrayManipulation(n, queries);

		System.out.println(String.valueOf(result));

		bufferedReader.close();
	}

}
