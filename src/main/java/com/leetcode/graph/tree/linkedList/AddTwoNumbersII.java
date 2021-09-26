package com.leetcode.graph.tree.linkedList;

import com.Utils;

import java.util.Stack;

/**
 * @author zms
 * @date 4:22 PM 2020/4/11
 * <a href="https://leetcode.com/problems/add-two-numbers-ii/">
 *     Add Two Numbers II</a>
 */
public class AddTwoNumbersII {
    /**
     * You are given two non-empty linked lists representing two non-negative integers.
     * The most significant digit comes first and each of their nodes contain a single digit.
     * Add the two numbers and return it as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * Follow up:
     * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
     *
     * Example:
     *
     * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 8 -> 0 -> 7
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while(l1 != null || l2 != null) {
            if(l1 != null) {
                stack1.push(l1);
                l1 = l1.next;
            }
            if(l2 != null) {
                stack2.push(l2);
                l2 = l2.next;
            }
        }
        int pre = 0;
        ListNode head = null;
        while(!stack1.isEmpty() || !stack2.isEmpty()) {
            int sum = pre;
            if(!stack1.isEmpty()) {
                ListNode pop1 = stack1.pop();
                sum += pop1.val;
            }
            if(!stack2.isEmpty()) {
                ListNode pop2 = stack2.pop();
                sum += pop2.val;
            }

            if(sum >= 10) {
                pre = 1;
                sum -= 10;
            } else {
                pre = 0;
            }
            ListNode node = new ListNode(sum);
            node.next = head;
            head = node;

        }
        if(pre != 0) {
            ListNode pop = new ListNode(0);
            pop.val += pre;
            pop.next = head;
            return pop;
        }
        return head;
    }

    // 补齐 让两个链表同长度相加
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        while(temp1 != null & temp2 != null) {
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        if(temp1 != null) {
            while(temp1 != null) {
                ListNode root = new ListNode(0);
                root.next = l2;
                l2 = root;
                temp1 = temp1.next;
            }
        } else {
            while(temp2 != null) {
                ListNode root = new ListNode(0);
                root.next = l1;
                l1 = root;
                temp2 = temp2.next;
            }
        }
        int carry = recurse(l1, l2);
        if(carry > 0) {
            ListNode head = new ListNode(carry);
            head.next = l1;
            l1 = head;
        }
        return l1;
    }

    public static int recurse(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return 0;
        }
        int carry = recurse(l1.next, l2.next);
        int sum = l1.val + l2. val + carry;
        if(sum >= 10) {
            l1.val = sum - 10;
            return 1;
        }
        l1.val = sum;
        return 0;
    }

    public static void main(String[] args) {
        ListNode l1 = Utils.arrayToListNode(new int[]{7, 2, 4, 3});
        ListNode l2 = Utils.arrayToListNode(new int[]{3, 5, 6, 5});
        Utils.printLinkedList(addTwoNumbers(l1, l2));

        l1 = Utils.arrayToListNode(new int[]{1});
        l2 = Utils.arrayToListNode(new int[]{9, 9});
        Utils.printLinkedList(addTwoNumbers(l1, l2));
    }
}
