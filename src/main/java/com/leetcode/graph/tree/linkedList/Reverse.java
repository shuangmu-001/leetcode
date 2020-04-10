package com.leetcode.graph.tree.linkedList;

/**
 * @author wcl
 * @date 6:01 PM 2020/4/7
 * <a href="https://leetcode.com/problems/reverse-linked-list/">
 *     Reverse Linked List</a>
 */
public class Reverse {
    /**
     * Reverse a singly linked list.
     *
     * Example:
     *
     * Input: 1->2->3->4->5->NULL
     * Output: 5->4->3->2->1->NULL
     * Follow up:
     *
     * A linked list can be reversed either iteratively or recursively. Could you implement both?
     */
    public ListNode reverseList(ListNode head) {
        return iteratorReverseList(head);
    }

    public ListNode iteratorReverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = null;
        while(next != null) {
            ListNode temp = next.next;
            next.next = head;
            head = next;
            next = temp;
        }
        return head;
    }
    private ListNode newHead;
    public ListNode recursiveReverseList(ListNode head) {
        if(head == null || head.next == null) {
            newHead = head;
            return head;
        }
        ListNode parent = recursiveReverseList(head.next);
        parent.next = head;
        head.next = null;
        return head;
    }
}
