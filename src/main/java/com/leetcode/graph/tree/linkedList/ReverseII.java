package com.leetcode.graph.tree.linkedList;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 6:21 PM 2020/4/7
 * <a href="https://leetcode.com/problems/reverse-linked-list-ii/">
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
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || m >= n) {
            return head;
        }

        int index = 1;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while(index < m && head != null) {
            prev = prev.next;
            head = head.next;
            index++;
        }
        if(head == null) {
            return dummy.next;
        }
        ListNode start = null;
        ListNode end = head;
        while(index <= n && head != null) {
            ListNode next = head.next;
            head.next = start;
            start = head;
            head = next;
            if(index == m) {
                end = start;
            }

            index++;
        }
        end.next = head;
        prev.next = start;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node = Utils.arrayToListNode(new int[]{1, 2, 3, 4, 6, 7});
        Utils.printLinkedList(reverseBetween(node, 3, 5));
    }
}
