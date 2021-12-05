package com.leetcode.graph.tree.linkedList;

/**
 * {@link "https://leetcode.com/problems/add-two-numbers/"}
 *
 * @author zms
 * @date 8:56 PM 2020/2/17
 */
public class AddTwoNumbers {

    /**
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order and each of their nodes contain a single digit.
     * Add the two numbers and return it as a linked list.
     * <p>
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * <p>
     * Example:
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     * <p>
     * Example 2:
     * Input: l1 = [0], l2 = [0]
     * Output: [0]
     * <p>
     * Example 3:
     * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * Output: [8,9,9,9,0,0,0,1]
     * <p>
     * Constraints:
     * The number of nodes in each linked list is in the range [1, 100].
     * 0 <= Node.val <= 9
     * It is guaranteed that the list represents a number that does not have leading zeros.
     */
    public static ListNode addTwoNumbers01(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        boolean flag = false;
        int target;
        ListNode parentNode = new ListNode(-1);
        ListNode childNode = parentNode;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                target = l2.val;
                l2 = l2.next;
            } else if (l2 == null) {
                target = l1.val;
                l1 = l1.next;
            } else {
                target = l1.val + l2.val;
                l2 = l2.next;
                l1 = l1.next;
            }

            if (flag) {
                target += 1;
            }
            flag = target >= 10;
            childNode.next = flag ? new ListNode(target - 10) : new ListNode(target);
            childNode = childNode.next;
        }
        if (flag) {
            childNode.next = new ListNode(1);
        }
        return parentNode.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode parent = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int res = carry;
            if(l1 != null) {
                res += l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                res += l2.val;
                l2 = l2.next;
            }
            carry = res / 10;
            res %= 10;
            ListNode node = new ListNode(res);
            parent.next = node;
            parent = node;
        }
        if (carry != 0) {
            parent.next = new ListNode(1);
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode13 = new ListNode(3);
        ListNode listNode12 = new ListNode(4);
        ListNode listNode11 = new ListNode(2);
        listNode12.next = listNode13;
        listNode11.next = listNode12;
        ListNode listNode23 = new ListNode(4);
        ListNode listNode22 = new ListNode(6);
        ListNode listNode21 = new ListNode(5);
        listNode22.next = listNode23;
        listNode21.next = listNode22;
        ListNode listNode = addTwoNumbers(listNode11, listNode21);
        System.out.println(listNode);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
