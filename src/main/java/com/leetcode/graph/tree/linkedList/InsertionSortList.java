package com.leetcode.graph.tree.linkedList;

/**
 * @author wcl
 * @date 10:58 AM 2020/4/15
 * <a href="https://leetcode.com/problems/insertion-sort-list/">
 * Insertion Sort List</a>
 */
public class InsertionSortList {
    /**
     * Algorithm of Insertion Sort:
     * 1、Insertion sort iterates, consuming one input element each repetition,
     * and growing a sorted output list.
     * 2、At each iteration, insertion sort removes one element from the input data,
     * finds the location it belongs within the sorted list, and inserts it there.
     * 3、It repeats until no input elements remain.
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode res = new ListNode(Integer.MIN_VALUE);
        ListNode sortList = res;
        while (head != null) {
            if (sortList.val > head.val) {
                sortList = res;
            }

            while (sortList.next != null && sortList.next.val < head.val) {
                sortList = sortList.next;
            }
            ListNode next = sortList.next;
            sortList.next = head;
            head = head.next;
            sortList.next.next = next;
        }
        return res.next;
    }
}
