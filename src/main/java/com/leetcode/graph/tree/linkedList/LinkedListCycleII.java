package com.leetcode.graph.tree.linkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zms
 * @date 6:51 PM 2020/4/2
 * <a href="https://leetcode.com/problems/linked-list-cycle-ii/">
 *      Linked List Cycle II</a>
 */
public class LinkedListCycleII {
    /**
     * Given a linked list, return the node where the cycle begins.
     * If there is no cycle, return null.
     * To represent a cycle in the given linked list,
     * we use an integer pos which represents the position (0-indexed)
     * in the linked list where tail connects to. If pos is -1,
     * then there is no cycle in the linked list.
     *
     * Note: Do not modify the linked list.
     *
     * Example 1:
     * Input: head = [3,2,0,-4], pos = 1
     * Output: tail connects to node index 1
     * Explanation: There is a cycle in the linked list, where tail connects to the second node.
     *
     * Example 2:
     * Input: head = [1,2], pos = 0
     * Output: tail connects to node index 0
     * Explanation: There is a cycle in the linked list, where tail connects to the first node.
     *
     * Example 3:
     * Input: head = [1], pos = -1
     * Output: no cycle
     * Explanation: There is no cycle in the linked list.
     *
     * Follow-up:Can you solve it without using extra space?
     */
    public ListNode detectCycle1(ListNode head) {
        if(head == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        while(head != null) {
            if(set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }
    // 0 --> x  0 --> 2x  y 环节点 z 节点数
    // x - y + z = 2x
    // z - x = y
    public ListNode detectCycle(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode first = head;
        ListNode second = head;
        while(second != null && second.next != null) {
            first = first.next;
            second = second.next.next;
            if(first == second) {
                break;
            }
        }
        if(second == null || second.next == null) {
            return null;
        }
        while(head != second) {
            head = head.next;
            second = second.next;
        }
        return second;
    }
}
