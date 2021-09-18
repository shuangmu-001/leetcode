package com.data.structure.linkedList;

import com.Utils;
import com.leetcode.graph.tree.linkedList.ListNode;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 *
 * @author wcl
 * @date 1:28 下午 2021/5/8
 */
public class Partition {

    public static ListNode partition(ListNode head, int pivot) {
        if (head == null) {
            return null;
        }
        ListNode beforeStart = new ListNode(0);
        ListNode beforeEnd = beforeStart;
        ListNode midStart = new ListNode(0);
        ListNode midEnd = midStart;
        ListNode afterStart = new ListNode(0);
        ListNode afterEnd = afterStart;
        while (head != null) {
            if (head.val == pivot) {
                midEnd.next = head;
                midEnd = midEnd.next;
            } else if (head.val < pivot) {
                beforeEnd.next = head;
                beforeEnd = beforeEnd.next;
            } else {
                afterEnd.next = head;
                afterEnd = afterEnd.next;
            }
            head = head.next;
        }
        beforeEnd.next = midStart.next;
        midEnd.next = afterStart.next;
        // 重点
        afterEnd.next = null;
        return beforeStart.next;
    }

    public static void main(String[] args) {
        ListNode head = Utils.arrayToListNode(new int[]{7, 8, 4, 4, 1, 2, 3, 6, 7, 8});
        Utils.printLinkedList(partition(head, 4));
    }
}
