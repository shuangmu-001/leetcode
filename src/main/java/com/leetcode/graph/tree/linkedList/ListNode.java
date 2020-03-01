package com.leetcode.graph.tree.linkedList;

/**
 * @author wcl
 * @date 6:30 PM 2020/2/17
 */
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
