package com.leetcode.graph.tree.linkedList;

/**
 * @author wcl
 * @date 2:03 PM 2020/4/9
 * <a href="https://leetcode.com/problems/partition-list/">
 *     Partition List</a>
 */
public class PartitionList {
    /**
     * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
     * You should preserve the original relative order of the nodes in each of the two partitions.
     *
     * Example:
     *
     * Input: head = 1->4->3->2->5->2, x = 3
     * Output: 1->2->2->4->3->5
     */
    public ListNode partition1(ListNode head, int x) {
        ListNode res = null;
        ListNode min = null;
        ListNode part = null;
        ListNode max = null;
        while(head != null) {
            if(head.val >= x) {
                if(max == null) {
                    max = head;
                    part = head;
                } else {
                    max.next = head;
                    max = max.next;
                }
            } else {
                if(min == null) {
                    min = head;
                    res = head;
                } else {
                    min.next = head;
                    min = min.next;
                }
            }
            head = head.next;
        }
        if(min == null) {
            res = max;
        } else {
            min.next = part;
        }
        if(max != null) {
            max.next = null;
        }
        return res;
    }
    public ListNode partition(ListNode head, int x) {

        // before and after are the two pointers used to create the two list
        // before_head and after_head are used to save the heads of the two lists.
        // All of these are initialized with the dummy nodes created.
        ListNode before_head = new ListNode(0);
        ListNode before = before_head;
        ListNode after_head = new ListNode(0);
        ListNode after = after_head;

        while (head != null) {

            // If the original list node is lesser than the given x,
            // assign it to the before list.
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                // If the original list node is greater or equal to the given x,
                // assign it to the after list.
                after.next = head;
                after = after.next;
            }

            // move ahead in the original list
            head = head.next;
        }

        // Last node of "after" list would also be ending node of the reformed list
        after.next = null;

        // Once all the nodes are correctly assigned to the two lists,
        // combine them to form a single list which would be returned.
        before.next = after_head.next;

        return before_head.next;
    }
}
