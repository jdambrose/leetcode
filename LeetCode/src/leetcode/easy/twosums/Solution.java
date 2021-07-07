package leetcode.easy.twosums;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/two-sum/
 * Given an array of integers nums and an integer target, return indices of the
 * two numbers such that they add up to target.
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * 
 * You can return the answer in any order.
 * 
 * Example 1:
 * 
 * Input: nums = [2,7,11,15], target = 9 Output: [0,1] Output: Because nums[0] +
 * nums[1] == 9, we return [0, 1].
 *
 */
public class Solution {

	public int[] twoSum(int[] nums, int target) {

		for (int i = 0; i < nums.length; i++) {

			for (int j = i + 1; j < nums.length; j++) {

				if (nums[i] + nums[j] == target) {
					return new int[] { i, j };
				}

			}

		}

		return null;
	}

	public static void main(String[] args) {

		Solution solution = new Solution();

		int[] nums = { -3, 4, 3, 90 };
		System.out.println("Answer: " + Arrays.toString(solution.twoSum(nums, 0)));

	}

}
