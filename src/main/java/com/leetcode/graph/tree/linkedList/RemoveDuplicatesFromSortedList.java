package com.leetcode.graph.tree.linkedList;

/**
 * @author zms
 * @date 11:44 AM 2020/4/9
 * <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-list/">
 *     Remove Duplicates from Sorted List</a>
 */
public class RemoveDuplicatesFromSortedList {
    /**
     * Given a sorted linked list, delete all duplicates such that each element appear only once.
     *
     * Example 1:
     *      Input: 1->1->2
     *      Output: 1->2
     *
     * Example 2:
     *      Input: 1->1->2->3->3
     *      Output: 1->2->3
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode itr = head.next, prev = head;
        while(itr != null) {
            if(itr.val == prev.val) {
                prev.next = itr.next;
            } else {
                prev = prev.next;
            }
            itr = itr.next;
        }
        return head;
    }
}
