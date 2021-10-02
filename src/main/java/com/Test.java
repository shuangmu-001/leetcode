package com;

import com.leetcode.graph.tree.bt.TreeNode;
import com.leetcode.graph.tree.linkedList.ListNode;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zms
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
     * 随意生成一个二维数组（只包含正整数）
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

    default int[][] genRandomTwoArr(int row, int col, int value, boolean flag) {
        int[][] arr = new int[row][col];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (flag) {
                    arr[i][j] = (int) (Math.random() * value);
                } else {
                    arr[i][j] = (int) (Math.random() * value) * (Math.random() > 0.5 ? -1 : 1);
                }
            }
        }
        return arr;
    }

    /**
     * 随意生成一个二维数组（包含正、负、0）
     */
    default int[][] genRandomTwoArr(int row, int col, int value) {
        return genRandomTwoArr(row, col, value, false);
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

    default String getSourceStr() {
        return DEFAULT_STR;
    }

    /**
     * 随机生成长度为len的字符串
     *
     * @param len 字符串长度
     * @return 目标字符串
     */
    default String genTargetStr(int len) {
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = getSourceStr().charAt(new Random().nextInt(getSourceStr().length()));
        }
        return new String(chars);
    }

    /**
     * 随机生成长度为len的字符串数组
     *
     * @param n 字符串数组长度
     * @return 目标字符串数组
     */
    default String[] genTargetStrArr(int n) {
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = genTargetStr(n);
        }
        return arr;
    }

    /**
     * 随机生成节点数为n的二叉树
     *
     * @param n 二叉树的节点数
     * @return 目标字符串数组
     */
    default TreeNode genRandomTreeNode(int n) {
        return null;
    }

    // 可以生成一个两个随机数组
}
