package leetcode.hard.findMediumSortedArrays;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/ Given two sorted
 * arrays nums1 and nums2 of size m and n respectively, return the median of the
 * two sorted arrays.
 * 
 * Example 1: Input: nums1 = [1,3], nums2 = [2] Output: 2.00000 Explanation:
 * merged array = [1,2,3] and median is 2.
 * 
 * Example 2: Input: nums1 = [1,2], nums2 = [3,4] Output: 2.50000 Explanation:
 * merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * 
 * Example 3: Input: nums1 = [0,0], nums2 = [0,0] Output: 0.00000
 * 
 * Example 4: Input: nums1 = [], nums2 = [1] Output: 1.00000
 * 
 * Example 5: Input: nums1 = [2], nums2 = [] Output: 2.00000
 *
 */
public class Solution {

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {

		int[] combined = this.combine(nums1, nums2);
		return getMedian(combined);

	}

	/**
	 * Combines two arrays into one sorted array
	 * 
	 * @param nums1
	 * @param nums2
	 * @return int[]
	 */
	public int[] combine(int[] nums1, int[] nums2) {

		int[] combined = new int[nums1.length + nums2.length];

		int n = 0; // index in nums1
		int m = 0; // index in nums2
		int c = 0; // index of combined array

		while (n < nums1.length || m < nums2.length) {

			if (n == nums1.length) { // Nums1 has been fully added to combined array

				combined[c] = nums2[m];
				c++;
				m++;

			} else if (m == nums2.length) { // Nums2 has been fully added to combined array

				combined[c] = nums1[n];
				c++;
				n++;

			} else if (nums1[n] <= nums2[m]) { // Nums1 should be added to combined array first

				combined[c] = nums1[n];
				c++;
				n++;

			} else { // Nums2 should be added to combined array first

				combined[c] = nums2[m];
				c++;
				m++;

			}

		}

		System.out.println("Combined array is " + Arrays.toString(combined));
		return combined;

	}

	/**
	 * Gets median of a single sorted integer array 
	 * 
	 * @param combined
	 * @return median
	 */
	public double getMedian(int[] combined) {

		int length = combined.length;

		if (length % 2 == 0) { // Case 1: Combined is even

			int val1 = combined[length / 2 - 1];
			int val2 = combined[length / 2];
			return (val1 + val2) / 2.0;

		} else { // Case 2: Combined is even
			return combined[length / 2];
		}

	}

	public static void main(String[] args) {

		int[] example1jnums1 = { 1, 3 };
		int[] example1jnums2 = { 2 };
		int[] example2jnums1 = { 1, 2 };
		int[] example2jnums2 = { 3, 4 };
		int[] example3jnums1 = { 0, 0 };
		int[] example3jnums2 = { 0, 0 };
		int[] example4jnums1 = {};
		int[] example4jnums2 = { 1 };
		int[] example5jnums1 = { 2 };
		int[] example5jnums2 = {};

		Solution s = new Solution();
		System.out.println("Median of " + Arrays.toString(example1jnums1) + " and " + Arrays.toString(example1jnums2)
				+ ": " + s.findMedianSortedArrays(example1jnums1, example1jnums2));
		System.out.println("Median of " + Arrays.toString(example2jnums1) + " and " + Arrays.toString(example2jnums2)
				+ ": " + s.findMedianSortedArrays(example2jnums1, example2jnums2));
		System.out.println("Median of " + Arrays.toString(example3jnums1) + " and " + Arrays.toString(example3jnums2)
				+ ": " + s.findMedianSortedArrays(example3jnums1, example3jnums2));
		System.out.println("Median of " + Arrays.toString(example4jnums1) + " and " + Arrays.toString(example4jnums2)
				+ ": " + s.findMedianSortedArrays(example4jnums1, example4jnums2));
		System.out.println("Median of " + Arrays.toString(example5jnums1) + " and " + Arrays.toString(example5jnums2)
				+ ": " + s.findMedianSortedArrays(example5jnums1, example5jnums2));

	}

}
