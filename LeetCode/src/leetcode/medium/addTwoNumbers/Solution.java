package leetcode.medium.addTwoNumbers;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * 
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order, and each of their nodes
 * contains a single digit. Add the two numbers and return the sum as a linked
 * list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * Example 1: Input: l1 = [2,4,3], l2 = [5,6,4] Output: [7,0,8] Explanation: 342
 * + 465 = 807.
 * 
 * Example 2: Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9] Output:
 * [8,9,9,9,0,0,0,1]
 * 
 */
public class Solution {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		int solution = 0;
		ListNode l = null;

		while (l1 != null || l2 != null) {

			if (l1 != null) {
				solution = solution + l1.val;
				l1 = l1.next;
			}

			if (l2 != null) {
				solution = solution + l2.val;
				l2 = l2.next;
			}

			l = new ListNode((int) solution % 10, l);
			solution = solution / 10;
		}

		if (solution > 0) {
			l = new ListNode((int) solution, l);
		}

		return flip(l);

	}

	public ListNode flip(ListNode n) {

		ListNode flip = null;
		while (n != null) {
			flip = new ListNode(n.val, flip);
			n = n.next;
		}

		return flip;
	}

	public static void main(String args[]) {

		ListNode a = new ListNode(3);
		ListNode a1 = new ListNode(4, a);
		ListNode a2 = new ListNode(2, a1);

		ListNode b = new ListNode(4);
		ListNode b1 = new ListNode(6, b);
		ListNode b2 = new ListNode(5, b1);

		Solution addTwo = new Solution();
		ListNode solution = addTwo.addTwoNumbers(a2, b2);

		while (solution != null) {
			System.out.print(solution.val + " > ");
			solution = solution.next;
		}

		System.out.print("\n");

		ListNode n = new ListNode(9);
		ListNode m = new ListNode(9);
		for (int i = 0; i < 10; i++) {
			m = new ListNode(9, m);
		}
		m = new ListNode(1, m);

		solution = addTwo.addTwoNumbers(n, m);
		while (solution != null) {
			System.out.print(solution.val + " > ");
			solution = solution.next;
		}

	}

}
