package hackerrank.interviewprep.hashmaps.sherlockanagrams;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.IntStream;

// https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
// Works SO MUCH slower than the last one RIP
public class ResultHashSet {

	/*
	 * Complete the 'sherlockAndAnagrams' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts STRING s
	 * as parameter.
	 */
	public static int sherlockAndAnagrams(String s) {
		
		System.out.println("Analyzing String " + s);

		int numAnagrams = 0;
		
		HashMap<Integer, HashSet<String>> anagrams = new HashMap<>();

		// For each size we are going through
		for (int size = 1; size < s.length(); size++) {
			
			System.out.println("\t Size= " + size);
			
			// Puts all substrings into a map
			HashMap<Integer, String> subString = new HashMap<>();
			for (int i = 0; i <= s.length() - size; i++) {
				String sub = s.substring(i, i + size);
				subString.put(i, sub);
			}

			// Compares all the subStrings and counts anagrams
			for (int i = 0; i <= s.length() - size; i++) {
				
				System.out.println("\t\t Working on substring= " + i);

				String currentString = subString.get(i);
				HashSet<String> set = anagrams.get(i);
				HashSet<String> newSet = updateHashSetWithNewString(anagrams.get(i), currentString.substring(currentString.length()-1));
				anagrams.put(i, newSet);
				set = anagrams.get(i);

				for (int j = i + 1; j <= s.length() - size; j++) {

					if (set.contains(subString.get(j))) {
						numAnagrams++;
					}
				}
			}
		}

		return numAnagrams;

	}
	
	private static HashSet<String> updateHashSetWithNewString(HashSet<String> set, String c) {
		
		HashSet<String> newSet = new HashSet<>();
		if(set == null) {
			newSet.add(c);
			return newSet;
		}
		
		for(String s : set) {
			for(int i = 0; i <= s.length(); i++) {
				
				String newAnagram = s.substring(0, i) + c + s.substring(i, s.length());
				newSet.add(newAnagram);
				
			}
		}
		
		return newSet;
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int q = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, q).forEach(qItr -> {
			try {
				String s = bufferedReader.readLine();

				int result = ResultHashSet.sherlockAndAnagrams(s);

				System.out.println(String.valueOf(result));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();

	}

}
