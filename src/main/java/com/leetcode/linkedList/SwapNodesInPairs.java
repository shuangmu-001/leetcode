package com.leetcode.linkedList;

import com.leetcode.ListNode;

/**
 * @author wcl
 * @date 2:53 PM 2019/12/23
 */
public class SwapNodesInPairs {
    /**
     * Given a linked list, swap every two adjacent nodes and return its head.
     *
     * You may not modify the values in the list's nodes, only nodes itself may be changed.
     *
     * Example:
     *
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     */
    public static ListNode swapPairs(ListNode head) {

        ListNode result = head;
        boolean headFlag = true;
        ListNode pre = null;

        while(head != null && head.next != null) {
            System.out.println(head);
            ListNode next = head.next;
            head.next = next.next;
            next.next = head;
            if(headFlag) {
                result = next;
                headFlag = false;
            } else {
                pre.next = next;
            }
            pre = head;
            head = head.next;
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode listNode4 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        listNode3.next = listNode4;
        ListNode listNode2 = new ListNode(2);
        listNode2.next = listNode3;
        ListNode listNode1 = new ListNode(1);
        listNode1.next = listNode2;
        System.out.println(swapPairs(listNode4));

//        while (listNode1.next != null) {
//            System.out.println(listNode1);
//            listNode1 = listNode1.next;
//        }
    }



}
