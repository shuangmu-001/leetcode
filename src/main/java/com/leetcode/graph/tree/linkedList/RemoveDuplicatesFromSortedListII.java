package com.leetcode.graph.tree.linkedList;

/**
 * @author wcl
 * @date 11:49 AM 2020/4/9
 * <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/">
 *     Remove Duplicates from Sorted List II</a>
 */
public class RemoveDuplicatesFromSortedListII {
    /**
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
     *
     * Return the linked list sorted as well.
     *
     * Example 1:
     *
     * Input: 1->2->3->3->4->4->5
     * Output: 1->2->5
     * Example 2:
     *
     * Input: 1->1->1->2->3
     * Output: 2->3
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode parent = dummy, cur = head;
        int count = 0;
        int pre = head.val;
        while(head != null) {
            if(head.val == pre) {
                count++;
            } else {
                count = 1;
                pre = head.val;
                parent = cur;
                cur = head;
            }

            if(count > 1) {
                parent.next = head.next;
                cur = parent;
            }
            head = head.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode6 = new ListNode(4);
        ListNode listNode5 = new ListNode(4);
        listNode5.next = listNode6;
        ListNode listNode4 = new ListNode(3);
        listNode4.next = listNode5;
        ListNode listNode3 = new ListNode(3);
        listNode3.next = listNode4;
        ListNode listNode2 = new ListNode(2);
        listNode2.next = listNode3;
        ListNode listNode1 = new ListNode(1);
        listNode1.next = listNode2;

        System.out.println(deleteDuplicates(listNode3));
    }
}
