package com.data.structure.linkedList;

import com.leetcode.graph.tree.linkedList.ListNode;

/**
 * 给定两个可能有环，也可能无环的单链表，头节点h1和h2。
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。
 * 如果不相交，返回null
 * 时间复杂度O(N),额外空间复杂度O(1)
 * TODO 约瑟夫环
 *
 * @author zms
 * @date 2:45 下午 2021/5/8
 */
public class Cycle {

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // 0 --> x  0 --> 2x  y 环节点 z 节点数
    // x - y + z = 2x
    // z - x = y
    // 快慢指针相交点一定在环里
    // 找到入环点
    public static ListNode getLoopNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        while (head != fast) {
            head = head.next;
            fast = fast.next;
        }
        return fast;
    }

    // 无环两个链表相交
    public static ListNode noLoop(ListNode h1, ListNode h2) {
        if (h1 == null || h2 == null) {
            return null;
        }
        while (h1 != h2) {
            h1 = h1 == null ? h2 : h1.next;
            h2 = h2 == null ? h1 : h2.next;
        }
        return h1;
    }

    /**
     * 有环两个链表相交
     *
     * @param h1 链表1
     * @param h2 链表2
     * @param l1 链表1的入环点
     * @param l2 链表2的入环点
     * @return 链表1和链表2相交点
     */
    public static ListNode bothLoop(ListNode h1, ListNode h2, ListNode l1, ListNode l2) {
        // 入环点相同的情况
        if (l1 == l2) {
            while (h1 != h2) {
                h1 = h1 == l1 ? h2 : h1.next;
                h2 = h2 == l2 ? h1 : h2.next;
            }
            return h2;
        }
        ListNode next = l1.next;
        // 入环点不同的情况
        while (next != l1 && next != l2) {
            next = next.next;
        }
        return next == l1 ? null : l2;
    }

    public static ListNode getIntersectNode(ListNode h1, ListNode h2) {
        if (h1 == null || h2 == null) {
            return null;
        }
        ListNode l1 = getLoopNode(h1);
        ListNode l2 = getLoopNode(h2);
        if (l1 == null && l2 == null) {
            return noLoop(h1, h2);
        }
        if (l1 != null && l2 != null) {
            return bothLoop(h1, h2, l1, l2);
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = head;
        System.out.println(getLoopNode(head).val);
    }
}
