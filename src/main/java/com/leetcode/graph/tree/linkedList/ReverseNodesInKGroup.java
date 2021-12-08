package com.leetcode.graph.tree.linkedList;

import com.Utils;

/**
 * {@link "https://leetcode.com/problems/reverse-nodes-in-k-group/"}
 *
 * @author zms
 * @date 5:59 PM 2020/2/19
 */
public class ReverseNodesInKGroup {
    /**
     * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
     * k is a positive integer and is less than or equal to the length of the linked list.
     * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
     * <p>
     * Example:
     * Given this linked list: 1->2->3->4->5
     * For k = 2, you should return: 2->1->4->3->5
     * For k = 3, you should return: 3->2->1->4->5
     * <p>
     * Note:
     * Only constant extra memory is allowed.
     * You may not alter the values in the list's nodes, only nodes itself may be changed.
     */
    public static ListNode reverseKGroup01(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }

        int count = size / k;
        ListNode current = null;
        ListNode next = dummy.next;
        ListNode endNode = dummy;
        for (int i = 0; i < count; i++) {
            ListNode begin = next;
            for (int j = 0; j < k; j++) {
                ListNode temp = current;
                current = next;
                next = next.next;
                current.next = temp;
            }
            endNode.next = current;
            endNode = begin;

            current = null;
        }

        if (size % k != 0) {
            endNode.next = next;
        }

        return dummy.next;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        ListNode tail = dummy;
        while (true) {
            int count = 0;
            while (tail != null && count != k) {
                count++;
                tail = tail.next;
            }
            if (tail == null) {
                break;
            }
            ListNode start = head.next;
            while (head.next != tail) {
                ListNode temp = start.next;
                start.next = temp.next;
                temp.next = head.next;
                head.next = temp;
            }
            tail = start;
            head = start;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode = Utils.arrayToListNode(new int[]{1, 2, 3, 4, 5});
        Utils.printLinkedList(reverseKGroup(listNode, 3));
    }
}
