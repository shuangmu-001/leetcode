package com.leetcode.graph.tree.linkedList;

/**
 * @author zms
 * @date 10:51 AM 2020/2/18
 * {@link <https://leetcode.com/problems/merge-two-sorted-lists/>}
 */
public class MergeTwoSortedLists {
    /**
     * Merge two sorted linked lists and return it as a new list.
     * The new list should be made by splicing together the nodes of the first two lists.
     * <p>
     * Example:
     * Input: 1->2->4, 1->3->4
     * Output: 1->1->2->3->4->4
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode head = result;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }
        if (l1 != null) {
            head.next = l1;
        }
        if (l2 != null) {
            head.next = l2;
        }
        return result.next;
    }

    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode listNode13 = new ListNode(4);
        ListNode listNode12 = new ListNode(3);
        ListNode listNode11 = new ListNode(2);
        listNode12.next = listNode13;
        listNode11.next = listNode12;

        ListNode listNode23 = new ListNode(6);
        ListNode listNode22 = new ListNode(5);
        ListNode listNode21 = new ListNode(4);
        listNode22.next = listNode23;
        listNode21.next = listNode22;

        ListNode listNode32 = new ListNode(9);
        ListNode listNode31 = new ListNode(7);
        listNode31.next = listNode32;

        ListNode listNode43 = new ListNode(10);
        ListNode listNode42 = new ListNode(8);
        ListNode listNode41 = new ListNode(4);
        listNode42.next = listNode43;
        listNode41.next = listNode42;

        System.out.println(mergeTwoLists(listNode11, listNode21));
        System.out.println(mergeTwoLists(listNode11, null));
        System.out.println(mergeTwoLists(listNode41, listNode31));
    }
}
