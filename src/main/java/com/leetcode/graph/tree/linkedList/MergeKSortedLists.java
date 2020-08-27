package com.leetcode.graph.tree.linkedList;

/**
 * @author wcl
 * @date 4:19 PM 2020/4/24
 * <a href="https://leetcode.com/problems/merge-k-sorted-lists/">
 * Merge k Sorted Lists</a>
 * @see MergeTwoSortedLists
 */
public class MergeKSortedLists {
    /**
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     * <p>
     * Example:
     * <p>
     * Input:
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * Output: 1->1->2->3->4->4->5->6
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode res = new ListNode(0);
        res.next = lists[0];
        ListNode dummy = res;
        ListNode first = res.next;
        for (int i = 1; i < lists.length; i++) {
            ListNode temp = lists[i];
            while (first != null && temp != null) {
                if (temp.val < first.val) {
                    dummy.next = temp;
                    temp = temp.next;
                } else {
                    dummy.next = first;
                    first = first.next;
                }
                dummy = dummy.next;
            }
            if (first != null) {
                dummy.next = first;
            } else if (temp != null) {
                dummy.next = temp;
            }
            dummy = res;
            first = res.next;
        }
        return res.next;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }


}
