package com.leetcode.graph.tree.linkedList;

import com.Utils;

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

    public static ListNode mergeTwoLists01(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode res;
        ListNode l1P = null;
        if (l1.val < l2.val) {
            res = l1;
            l1P = l1;
            l1 = l1.next;
        } else {
            res = l2;
            l1P = l2;
            l2 = l2.next;
            l1P.next = l1;
        }
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l1P = l1;
                l1 = l1.next;
            } else {
                l1P.next = l2;
                l2 = l2.next;
                l1P = l1P.next;
                l1P.next = l1;
            }
        }
        if (l2 != null) {
            l1P.next = l2;
        }
        return res;
    }

    public static ListNode mergeTwoLists02(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode res = new ListNode(0);
        ListNode l1P = res;
        res.next = l1;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l1P = l1;
                l1 = l1.next;
            } else {
                l1P.next = l2;
                l2 = l2.next;
                l1P = l1P.next;
                l1P.next = l1;
            }
        }
        if (l2 != null) {
            l1P.next = l2;
        }
        return res.next;
    }

    public static void main(String[] args) {
        ListNode l1 = Utils.arrayToListNode(new int[]{1, 7, 9});
        ListNode l2 = Utils.arrayToListNode(new int[]{4, 5, 6});
        Utils.printLinkedList(mergeTwoLists02(l1, l2));
//        ListNode listNode13 = new ListNode(4);
//        ListNode listNode12 = new ListNode(3);
//        ListNode listNode11 = new ListNode(2);
//        listNode12.next = listNode13;
//        listNode11.next = listNode12;
//
//        ListNode listNode23 = new ListNode(6);
//        ListNode listNode22 = new ListNode(5);
//        ListNode listNode21 = new ListNode(4);
//        listNode22.next = listNode23;
//        listNode21.next = listNode22;
//
//        ListNode listNode32 = new ListNode(9);
//        ListNode listNode31 = new ListNode(7);
//        listNode31.next = listNode32;
//
//        ListNode listNode43 = new ListNode(10);
//        ListNode listNode42 = new ListNode(8);
//        ListNode listNode41 = new ListNode(4);
//        listNode42.next = listNode43;
//        listNode41.next = listNode42;
//
//        System.out.println(mergeTwoLists01(listNode11, listNode21));
//        System.out.println(mergeTwoLists01(listNode11, null));
//        System.out.println(mergeTwoLists01(listNode41, listNode31));
    }
}
