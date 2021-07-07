package hackerrank.interviewprep.hashmaps.frequenceyQueries;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution2 {
	// Complete the freqQuery function below.
		static List<Integer> freqQuery(List<List<Integer>> queries) {

			// Map accessed by <Value, Count in Data>
			HashMap<Integer, Integer> map = new HashMap<>();

			// Results
			List<Integer> resultsFromZFunc = new ArrayList<>();

			// Performs each query in order
			for (List<Integer> query : queries) {

				int functype = query.get(0);
				int val = query.get(1);

				if (functype == 1) { // Value one indicates Operation X

					operationX(map, val);

				} else if (functype == 2) { // Value two indicates operation Y

					operationY(map, val);

				} else if (functype == 3) { // Value two indicates operation Z

					resultsFromZFunc.add(operationZ(map, val));

				}

			}

			return resultsFromZFunc;

		}

		private static void operationX(HashMap<Integer, Integer> map, int val) {

			Integer count = map.getOrDefault(val, 0);
			map.put(val, count + 1);
		}

		private static void operationY(HashMap<Integer, Integer> map, int val) {

			Integer count = map.getOrDefault(val, 0);
			if (count == 1) {
				map.remove(val);
			} else if (count < 1) {
				// DO NOTHING; DATA NOT PRESENT
			} else {
				map.put(val, count - 1);
			}

		}

		private static int operationZ(HashMap<Integer, Integer> map, int val) {

			if (map.values().contains(val)) {
				return 1;
			}
			return 0;
		}

		public static void main(String[] args) throws IOException {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

			int q = Integer.parseInt(bufferedReader.readLine().trim());

			List<List<Integer>> queries = new ArrayList<>();

			IntStream.range(0, q).forEach(i -> {
				try {
					queries.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
							.map(Integer::parseInt).collect(toList()));
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			});

			List<Integer> ans = freqQuery(queries);

			System.out.print(ans.stream().map(Object::toString).collect(joining("\n")) + "\n");

			bufferedReader.close();
		}
}
