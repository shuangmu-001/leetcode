package com.leetcode.graph.tree.linkedList;

import com.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zms
 * @date 7:01 PM 2020/2/17
 * {@link "https://leetcode.com/problems/remove-nth-node-from-end-of-list/"}
 *
 * 第n个节点，
 * 从一个节点到另一个节点相差n个节点，
 * 保持两个指针一直相差n节点
 * 当一个指针移动到末尾时，另一个指针就是所要删除的节点
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
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Nth TreeNode From End of List.
     * Memory Usage: 37.9 MB, less than 6.37% of Java online submissions for Remove Nth TreeNode From End of List.
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
    // 先走一步
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
        ListNode head = Utils.arrayToListNode(new int[]{4, 3, 2, 1});
        Utils.printLinkedList(removeNthFromEnd(head, 1));

    }


}
