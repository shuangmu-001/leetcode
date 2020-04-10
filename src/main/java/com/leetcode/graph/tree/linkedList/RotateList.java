package com.leetcode.graph.tree.linkedList;

/**
 * @author wcl
 * @date 1:39 PM 2020/4/9
 * <a href="https://leetcode.com/problems/rotate-list/">
 *     Rotate List</a>
 */
public class RotateList {
    /**
     * Given a linked list, rotate the list to the right by k places, where k is non-negative.
     *
     * Example 1:
     *
     * Input: 1->2->3->4->5->NULL, k = 2
     * Output: 4->5->1->2->3->NULL
     * Explanation:
     * rotate 1 steps to the right: 5->1->2->3->4->NULL
     * rotate 2 steps to the right: 4->5->1->2->3->NULL
     * Example 2:
     *
     * Input: 0->1->2->NULL, k = 4
     * Output: 2->0->1->NULL
     * Explanation:
     * rotate 1 steps to the right: 2->0->1->NULL
     * rotate 2 steps to the right: 1->2->0->NULL
     * rotate 3 steps to the right: 0->1->2->NULL
     * rotate 4 steps to the right: 2->0->1->NULL
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) {
            return head;
        }
        int len = 0;
        ListNode dummy = head;
        ListNode end = head;
        while(head != null) {
            len++;
            end = head;
            head = head.next;
        }
        int n = k % len;
        if(n > 0) {
            end.next = dummy;
            n = len - n;
            while(n > 0) {
                if(n == 1) {
                    ListNode temp = dummy.next;
                    dummy.next = null;
                    dummy = temp;
                } else {
                    dummy = dummy.next;
                }
                n--;
            }
        }
        return dummy;
    }
}
