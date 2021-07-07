package hackerrank.interviewprep.hashmaps.sherlockanagrams;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.IntStream;

// https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
// Works; fails two run-time problems
public class Result {

	/*
	 * Complete the 'sherlockAndAnagrams' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts STRING s
	 * as parameter.
	 */

	public static int sherlockAndAnagrams(String s) {

		int numAnagrams = 0;

		// Size of the testing anagram string
		// Size does not need to exceed half the string, as then it is impossible for
		// them to be anagrams
		for (int size = 1; size < s.length(); size++) {

			if (size <= 3) {
				numAnagrams = numAnagrams + numAnagramsSmallString(size, s);
			} else {
				numAnagrams = numAnagrams + numAnagramsLargeString(size, s);
			}

		}

		return numAnagrams;

	}
	
	private static int numAnagramsSmallString(int size, String s) {
		
		int numAnagrams = 0;
		
		// Puts all substrings into a map
		HashMap<Integer, String> subString = new HashMap<>();
		for (int i = 0; i <= s.length() - size; i++) {
			String sub = s.substring(i, i + size);
			subString.put(i, sub);
		}
		
		// Compares all the subStrings and counts anagrams
		for (int i = 0; i <= s.length() - size; i++) {
			
			String sub = subString.get(i);
			
			/* For small strings, instead of doing an additional for loop, its easy to add all possible anagrams to a set */
			HashSet<String> set = getHashSetForSubString(sub);

			for (int j = i + 1; j <= s.length() - size; j++) {

				if(set.contains(subString.get(j))) {
					numAnagrams++;
				}
			}
		}
		
		return numAnagrams; 
		
	}
	
	
	

	private static HashSet<String> getHashSetForSubString(String sub) {
		
		HashSet<String> set = new HashSet<>();
		if(sub.length() == 1) {
			set.add(sub);
			return set;
		}else if(sub.length() == 2) {
			
			set.add(sub);
			String reverse = String.valueOf(sub.substring(1) + sub.substring(0,1));
			set.add(reverse);
			
		}else if(sub.length() == 3) {
			
			// Size 3 has six possible anagrams
			set.add(sub); // = sub.charAt(0) + sub.charAt(1) + sub.charAt(2))
			set.add(String.valueOf(sub.substring(0,1) + sub.substring(2) + sub.substring(1,2)));
			set.add(String.valueOf(sub.substring(1,2) + sub.substring(0,1) + sub.substring(2)));
			set.add(String.valueOf(sub.substring(1,2) + sub.substring(2) + sub.substring(0,1)));
			set.add(String.valueOf(sub.substring(2) + sub.substring(0,1) + sub.substring(1,2)));
			set.add(String.valueOf(sub.substring(2) + sub.substring(1,2) + sub.substring(0,1)));
			
		}
		// If I had more time, I would try to determine an algorithm to do this for maybe up to size 5! = 120
		
		return set;
	}

	// Brute force method
	private static int numAnagramsLargeString(int size, String s) {
		int numAnagrams = 0;

		// Stores all substrings for easier access later
		// Indexed by <Starting Array, String>
		HashMap<Integer, String> subString = new HashMap<>();
		for (int i = 0; i <= s.length() - size; i++) {
			String sub = s.substring(i, i + size);
			subString.put(i, sub);
		}

		// First index of substring; adds check for not exceeding size
		// Search front to back approach works, because any anagram of something at the
		// end would have already matched at the start string
		for (int i = 0; i <= s.length() - size; i++) {

			for (int j = i + 1; j <= s.length() - size; j++) {

				boolean isAnagram = isAnagram(subString.get(i), subString.get(j));
				if (isAnagram) {
					numAnagrams++;
				}

			}

		}

		return numAnagrams;

	}

	// Returns whether two strings are anagrams of one another
	private static boolean isAnagram(String s1, String s2) {
		
		if(s1.equals(s2)) {
			return true;
		}

		for (char c : s1.toCharArray()) {
			if (!s2.contains(String.valueOf(c))) {
				return false;
			}
			s2 = s2.replaceFirst(String.valueOf(c), "");

		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int q = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, q).forEach(qItr -> {
			try {
				String s = bufferedReader.readLine();

				int result = Result.sherlockAndAnagrams(s);

				System.out.println(String.valueOf(result));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();

	}

}
