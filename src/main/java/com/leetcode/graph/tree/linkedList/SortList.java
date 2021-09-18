package com.leetcode.graph.tree.linkedList;

import com.Utils;

/**
 * @author wcl
 * @date 11:38 AM 2020/4/15
 * <a href="https://leetcode.com/problems/sort-list/"> Sort List</a>
 * @see MergeKSortedLists
 * @see MergeTwoSortedLists
 */
public class SortList {
    /**
     * Sort a linked list in O(n log n) time using constant space complexity.
     * <p>
     * Example 1:
     * Input: 4->2->1->3
     * Output: 1->2->3->4
     * <p>
     * Example 2:
     * Input: -1->5->3->4->0
     * Output: -1->0->3->4->5
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //TODO 利用快慢进行拆分
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = null;
        return merge(sortList(head), sortList(slow));
    }

    public static ListNode merge(ListNode dummy, ListNode slow) {
        if (dummy == null) {
            return slow;
        }
        if (slow == null) {
            return dummy;
        }
        if (dummy.val < slow.val) {
            dummy.next = merge(dummy.next, slow);
            return dummy;
        } else {
            slow.next = merge(dummy, slow.next);
            return slow;
        }
    }

    public static void main(String[] args) {
        ListNode head = Utils.arrayToListNode(new int[]{Integer.MIN_VALUE, 4, 3, 2, 1});
        Utils.printLinkedList(sortList(head));
    }
}
