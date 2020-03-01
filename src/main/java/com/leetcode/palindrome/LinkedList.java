package com.leetcode.palindrome;


import com.leetcode.graph.tree.linkedList.ListNode;

/**
 * @author wcl
 * @date 4:19 PM 2019/12/23
 */
public class LinkedList {
    /**
     * Given a singly linked list, determine if it is a palindrome.
     */
    public static boolean isPalindrome(ListNode head) {

        if(head == null) {
            return true;
        }

        ListNode midNode = head.next;

        if(midNode == null) {
            return true;
        }

        ListNode rightFirst = midNode.next;
        if(rightFirst == null) {
            return head.val == midNode.val;
        }
        ListNode rightEnd = rightFirst;
        ListNode leftFirst = head;
        leftFirst.next = null;

        while(rightEnd != null && rightEnd.next != null) {
            midNode.next = leftFirst;
            leftFirst = midNode;
            midNode = rightFirst;
            rightFirst = rightFirst.next;
            rightEnd = rightEnd.next.next;
        }
        if(rightEnd == null) {
            rightFirst = midNode;
        }

        while(rightFirst != null ) {
            if(leftFirst == null) {
                return false;
            }
            if(rightFirst.val != leftFirst.val) {
                return false;
            }
            rightFirst = rightFirst.next;
            leftFirst = leftFirst.next;
        }

        return true;
    }

    public static void main(String[] args) {
//        ListNode listNode4 = new ListNode(4);
        ListNode listNode3 = new ListNode(0);
//        listNode3.next = listNode4;
//        ListNode listNode2 = new ListNode(-129);
//        listNode2.next = null;
//        ListNode listNode1 = new ListNode(-129);
//        listNode1.next = listNode2;
        ListNode listNode2 = new ListNode(0);
        listNode2.next = listNode3;
        ListNode listNode1 = new ListNode(1);
        listNode1.next = listNode2;
        System.out.println(isPalindrome(listNode1));

    }

}
