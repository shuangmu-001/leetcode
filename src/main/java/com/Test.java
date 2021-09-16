package com;

import com.leetcode.graph.tree.linkedList.ListNode;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author wcl
 * @date 5:09 下午 2021/5/12
 */
public interface Test {

    default void test(int n) {

    }

    /**
     * 随机从0到n之间拿一个数
     *
     * @param n 数的最大值
     * @return 随意生成一个数
     */
    default int genRandomNum(int n) {
        return new Random().nextInt(n);
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
     * TODO 随意生成一个二维数组
     *
     * @param n 数组的长度
     * @return 数组
     */
    default int[][] genRandomTwoArr(int n) {
        int N = ThreadLocalRandom.current().nextInt(1, n);
        int M = ThreadLocalRandom.current().nextInt(1, n);
        int[][] ints = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ints[i][j] = new Random().nextInt(n);
            }
        }
        return ints;
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
