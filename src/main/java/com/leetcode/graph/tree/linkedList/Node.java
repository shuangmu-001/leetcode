package com.leetcode.graph.tree.linkedList;

import java.util.Objects;

/**
 * Definition for a linked list node.
 * @author zms
 * @date 2:18 PM 2020/4/9
 */
public class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return val == node.val &&
                Objects.equals(next, node.next) &&
                Objects.equals(random, node.random);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next, random);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", next=" + next +
                ", random=" + random +
                '}';
    }
}
