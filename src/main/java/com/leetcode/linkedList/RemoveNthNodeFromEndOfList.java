package com.leetcode.linkedList;

import com.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 7:01 PM 2020/2/17
 * {@link "https://leetcode.com/problems/remove-nth-node-from-end-of-list/"}
 */
public class RemoveNthNodeFromEndOfList {
    /**
     * Given a linked list, remove the n-th node from the end of list and return its head.
     *
     * Example:
     *
     * Given linked list: 1->2->3->4->5, and n = 2.
     *
     * After removing the second node from the end, the linked list becomes 1->2->3->5.
     * Note:
     *
     * Given n will always be valid.
     *
     * Follow up:
     * Could you do this in one pass?
     *
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Nth Node From End of List.
     * Memory Usage: 37.9 MB, less than 6.37% of Java online submissions for Remove Nth Node From End of List.
     */
    public static ListNode removeNthFromEnd1(ListNode head, int n) {

        List<ListNode> nodes = new ArrayList<>();
        nodes.add(head);
        int length = 1;
        while (head.next != null) {
            length++;
            head = head.next;
            nodes.add(head);
        }
        if(length - n != 0) {
            nodes.get(length - n - 1).next = n != 1 ? nodes.get(length - n + 1) : null;
            return nodes.get(0);
        } else{
            if (length == 1) {
                return null;
            }
            return nodes.get(1);
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // first 和 second 相差 n + 1 个单位
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
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
        System.out.println(removeNthFromEnd(listNode1, 1));

    }


}
