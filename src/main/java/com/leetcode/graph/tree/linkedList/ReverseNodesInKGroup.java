package com.leetcode.graph.tree.linkedList;

/**
 * @author wcl
 * @date 5:59 PM 2020/2/19
 * {@link "https://leetcode.com/problems/reverse-nodes-in-k-group/"}
 */
public class ReverseNodesInKGroup {
    /**
     * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
     * k is a positive integer and is less than or equal to the length of the linked list.
     * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
     *
     * Example:
     *      Given this linked list: 1->2->3->4->5
     *      For k = 2, you should return: 2->1->4->3->5
     *      For k = 3, you should return: 3->2->1->4->5
     *
     * Note:
     *      Only constant extra memory is allowed.
     *      You may not alter the values in the list's nodes, only nodes itself may be changed.
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k <= 1) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        int size = 0;
        while(head != null) {
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

        if(size % k != 0) {
            endNode.next = next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode4 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        listNode3.next = listNode4;
        ListNode listNode2 = new ListNode(2);
        listNode2.next = listNode3;
        ListNode listNode1 = new ListNode(1);
        listNode1.next = listNode2;
        System.out.println(reverseKGroup(listNode1, 3));
    }
}
