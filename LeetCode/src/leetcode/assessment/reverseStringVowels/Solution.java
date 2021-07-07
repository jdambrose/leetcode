package leetcode.assessment.reverseStringVowels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {
	public String reverseVowels(String s) {

		char[] stringAsArray = s.toCharArray();
		List<Integer> positionOfVowels = new ArrayList<>();
		Stack<Character> vowels = new Stack<>();

		for (int i = 0; i < stringAsArray.length; i++) {
			char c = stringAsArray[i];
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O'
					|| c == 'U') {
				positionOfVowels.add(i);
				vowels.add(c);
			}
		}

		char[] solutionArray = Arrays.copyOf(stringAsArray, stringAsArray.length);
		for (int pos : positionOfVowels) {
			solutionArray[pos] = vowels.pop();
		}

		String solution = String.valueOf(solutionArray);
		return solution;
	}

	public static void main(String args[]) {

		Solution s = new Solution();

		String problem1 = "hello";
		String problem2 = "leetcode";
		String problem3 = "HELLO";
		String problem4 = "LEETCODE";

		System.out.println("Solution for " + problem1 + " = " + s.reverseVowels(problem1));
		System.out.println("Solution for " + problem2 + " = " + s.reverseVowels(problem2));
		System.out.println("Solution for " + problem3 + " = " + s.reverseVowels(problem3));
		System.out.println("Solution for " + problem4 + " = " + s.reverseVowels(problem4));

	}
}
