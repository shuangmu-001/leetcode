package com.leetcode.graph.tree.linkedList;

/**
 * @author wcl
 * @date 6:21 PM 2020/4/7
 * TODO <a href="https://leetcode.com/problems/reverse-linked-list-ii/">
 *     Reverse Linked List II</a>
 */
public class ReverseII {
    /**
     * Reverse a linked list from position m to n. Do it in one-pass.
     *
     * Note: 1 ≤ m ≤ n ≤ length of list.
     *
     * Example:
     *
     * Input: 1->2->3->4->5->NULL, m = 2, n = 4
     * Output: 1->4->3->2->5->NULL
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || m == n) {
            return head;
        }

        int index = 1;
        ListNode dummy = head;

        while(index < m) {
            head = head.next;
            index++;
        }
        ListNode begin = reverseHelper(head, n - m + 1);

        return m > 1 ? dummy : begin;
    }

    public ListNode reverseHelper(ListNode head, int length) {
        if(length == 0) {
            return head;
        }
        int len = 1;
        ListNode dummy = head;
        ListNode next = head.next;
        ListNode begin = next;
        head.next = null;
        while(len <= length) {
            ListNode temp = next.next;
            next.next = head;
            head = next;
            next = temp;
            begin = next;
            dummy.next = next;
            len++;
        }
        return begin;
    }
}
