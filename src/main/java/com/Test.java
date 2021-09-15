package com;

import com.leetcode.graph.tree.linkedList.ListNode;

import java.util.Random;

/**
 * @author wcl
 * @date 5:09 下午 2021/5/12
 */
public interface Test {

    default void test(int n) {

    }

    /**
     * 随意生成一个长度为n的数组
     *
     * @param n 数组的长度
     * @return 数组
     */
    default int[] genRandomArr(int n) {
        int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            int num = new Random().nextInt(50);
            arr1[i] = num;
        }
        return arr1;
    }

    /**
     * 随机生成两个长度为n的链表
     *
     * @param n 链表的长度
     * @return 链表数组
     */
    default ListNode[] genRandomListNode(int n) {
        ListNode[] listNodes = new ListNode[2];
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);
        ListNode node1 = head1;
        ListNode node2 = head2;
        for (int i = 0; i < n; i++) {
            int num = new Random().nextInt(50);
            node1.next = new ListNode(num);
            node1 = node1.next;
            node2.next = new ListNode(num);
            node2 = node2.next;
        }
        listNodes[0] = head1.next;
        listNodes[1] = head2.next;
        return listNodes;
    }

    String DEFAULT_STR = "abcdefghijklmnopqrstwvuxyzABCDEFGHIJKLMNOPQRSTWUVXYZ";

    /**
     * 随机生成长度为len的字符串
     *
     * @param len 字符串长度
     * @return 目标字符串
     */
    default String genTargetStr(int len) {
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = DEFAULT_STR.charAt(new Random().nextInt(DEFAULT_STR.length()));
        }
        return new String(chars);
    }
}
