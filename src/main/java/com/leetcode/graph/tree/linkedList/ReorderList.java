package com.leetcode.graph.tree.linkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 2:57 PM 2020/4/9
 * <a href="https://leetcode.com/problems/reorder-list/">
 *      Reorder List</a>
 */
public class ReorderList {
    /**
     * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
     * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
     *
     * You may not modify the values in the list's nodes, only nodes itself may be changed.
     *
     * Example 1:
     *
     * Given 1->2->3->4, reorder it to 1->4->2->3.
     * Example 2:
     *
     * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
     */
    /**
     * Runtime: 4 ms, faster than 14.03% of Java online submissions for Reorder List.
     * Memory Usage: 41.5 MB, less than 6.82% of Java online submissions for Reorder List.
     */
    public void reorderList1(ListNode head) {
        List<ListNode> nodes = new ArrayList<>();
        while(head != null && head.next != null) {
            nodes.add(head);
            nodes.add(head.next);
            head = head.next.next;
        }
        if(head != null) {
            nodes.add(head);
        }

        int len = nodes.size();
        int mid = len >> 1;
        if((len & 1) != 0) {
            nodes.get(mid).next = null;
        }
        for (int i = 0; i < mid; i++) {
            if(len - 1 - i > i) {
                nodes.get(i).next = nodes.get(len - 1 - i);
            } else {
                nodes.get(i).next = null;
            }
            if(len - 1 - i > i + 1) {
                nodes.get(len - 1 - i).next = nodes.get(i + 1);
            } else {
                nodes.get(len - 1 - i).next = null;
            }
        }
    }


}
