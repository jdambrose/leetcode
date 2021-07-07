package leetcode.medium.longestSubstringWithoutRepeatingCharacters;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Given a string s, find the length of the longest substring without repeating
 * characters.
 *
 * Example 1: Input: s = "abcabcbb" Output: 3 Explanation: The answer is "abc",
 * with the length of 3.
 * 
 * Example 2: Input: s = "bbbbb" Output: 1 Explanation: The answer is "b",
 * with the length of 1.
 * 
 * Example 3: Input: s = "pwwkew" Output: 3 Explanation: The answer is "wke",
 * with the length of 3. Notice that the answer must be a substring, "pwke" is a
 * subsequence and not a substring.
 * 
 * Example 4: Input: s = "" Output: 0
 */
public class Solution {

	public int lengthOfLongestSubstring(String s) {
		
		char[] carray = s.toCharArray();
		int maxlength = 0;
		
		for(int i = 0; i < carray.length; i++) {
			
			int currentlength = 1;
			ArrayList<Character> currentVals = new ArrayList<>();
			currentVals.add(carray[i]);
			
			for(int j = i+1; j < carray.length; j++) {
				
				if(!currentVals.contains(carray[j])) {
					currentVals.add(carray[j]);
					currentlength++;
				}else {
					break;
				}
				
			}
			
			if(currentlength > maxlength) {
				maxlength = currentlength;
			}
			
		}
		
		return maxlength;
	}

	public static void main(String[] args) {
		
		Solution s = new Solution();
		
		String example1 = "bbbb";
		String example2 = "abcabcbb";
		String example3 = "pwwkew";
		String example4 = "aab";
				
		System.out.println(example1 + ": " + s.lengthOfLongestSubstring(example1));
		System.out.println(example2 + ": " + s.lengthOfLongestSubstring(example2));
		System.out.println(example3 + ": " + s.lengthOfLongestSubstring(example3));
		System.out.println(example4 + ": " + s.lengthOfLongestSubstring(example4));


	}

}
