package com.data.structure.linkedList;

import com.leetcode.Utils;
import com.leetcode.graph.tree.linkedList.ListNode;

/**
 * 判断一个链表是否是否为回文
 *
 * @author wcl
 * @date 12:32 下午 2021/5/8
 */
public class IsPalindromeList {

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode pre = null;
        ListNode next = head;
        // 1、奇数中点，偶数下中点
        // 2、上半部分反转
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }
        ListNode temp = next;
        // 奇数中点，偶数下中点
        next = fast == null ? next : next.next;
        // 左半部分开始
        slow = pre;
        // 右半部分开始
        fast = temp;
        while (pre != null && next != null && pre.val == next.val) {
            pre = pre.next;
            next = next.next;
        }
        // 恢复链表
        while (slow != null) {
            temp = slow.next;
            slow.next = fast;
            fast = slow;
            slow = temp;
        }
        return pre == null;
    }

    public static void main(String[] args) {
        print(Utils.arrayToListNode(new int[]{1, 2, 1}));
        print(Utils.arrayToListNode(new int[]{1, 2, 1, 1}));
        print(Utils.arrayToListNode(new int[]{1, 2, 2, 1}));
        print(Utils.arrayToListNode(new int[]{1, 2, 3, 2, 1}));
        print(Utils.arrayToListNode(new int[]{1, 2, 3, 3, 1}));
        print(Utils.arrayToListNode(new int[]{1, 2, 3, 3, 2, 1}));
    }

    public static void print(ListNode head) {
        Utils.printLinkedList(head);
        System.out.println(isPalindrome(head));
        Utils.printLinkedList(head);
    }
}
