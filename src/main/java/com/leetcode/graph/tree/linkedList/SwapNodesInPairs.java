package com.leetcode.graph.tree.linkedList;

import com.Utils;

/**
 * <a href="https://leetcode.com/problems/swap-nodes-in-pairs/"> Swap Nodes in Pairs</a>
 *
 * @author zms
 * @date 2:53 PM 2019/12/23
 */
public class SwapNodesInPairs {
    /**
     * Given a linked list, swap every two adjacent nodes and return its head.
     * <p>
     * You may not modify the values in the list's nodes, only nodes itself may be changed.
     * <p>
     * Example:
     * <p>
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     */
    public static ListNode swapPairs01(ListNode head) {

        ListNode result = head;
        boolean headFlag = true;
        ListNode pre = null;

        while (head != null && head.next != null) {
            System.out.println(head);
            ListNode next = head.next;
            head.next = next.next;
            next.next = head;
            if (headFlag) {
                result = next;
                headFlag = false;
            } else {
                pre.next = next;
            }
            pre = head;
            head = head.next;
        }

        return result;
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode parent = dummy;
        while (head != null && head.next != null) {
            ListNode temp = head.next.next;
            parent.next = head.next;
            parent = parent.next;
            parent.next = head;
            parent = parent.next;
            head = temp;
        }
        parent.next = head;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode = Utils.arrayToListNode(new int[]{1, 2, 3, 4});
        Utils.printLinkedList(swapPairs(listNode));
        listNode = Utils.arrayToListNode(new int[]{1, 2, 3, 4, 5});
        Utils.printLinkedList(swapPairs(listNode));
    }
}
