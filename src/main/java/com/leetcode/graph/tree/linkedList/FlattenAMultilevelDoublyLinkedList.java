package com.leetcode.graph.tree.linkedList;

import com.leetcode.Utils;

import java.util.Stack;

/**
 * @author wcl
 * @date 11:33 AM 2020/4/20
 * <a href="https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/">
 * Flatten a Multilevel Doubly Linked List</a>
 * 看成树型结构 dfs 和 bfs
 */
public class FlattenAMultilevelDoublyLinkedList {

    /**
     * You are given a doubly linked list which in addition to the next and previous pointers,
     * it could have a child pointer, which may or may not point to a separate doubly linked list.
     * These child lists may have one or more children of their own, and so on, to produce a multilevel data structure,
     * as shown in the example below.
     * Flatten the list so that all the nodes appear in a single-level,
     * doubly linked list. You are given the head of the first level of the list.
     * <p>
     * Example 1:
     * Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
     * Output: [1,2,3,7,8,11,12,9,10,4,5,6]
     * <p>
     * Example 2:
     * Input: head = [1,2,null,3]
     * Output: [1,3,2]
     * Explanation:
     * The input multilevel linked list is as follows:
     * 1---2---NULL
     * |
     * 3---NULL
     * <p>
     * Example 3:
     * Input: head = []
     * Output: []
     * <p>
     * How multilevel linked list is represented in test case:
     * We use the multilevel linked list from Example 1 above:
     * 1---2---3---4---5---6--NULL
     * |
     * 7---8---9---10--NULL
     * |
     * 11--12--NULL
     * <p>
     * The serialization of each level is as follows:
     * [1,2,3,4,5,6,null]
     * [7,8,9,10,null]
     * [11,12,null]
     * <p>
     * To serialize all levels together we will add nulls in each level to signify no node connects to the upper node of the previous level.
     * The serialization becomes:
     * [1,2,3,4,5,6,null]
     * [null,null,7,8,9,10,null]
     * [null,11,12,null]
     * Merging the serialization of each level and removing trailing nulls we obtain:
     * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
     * <p>
     * Constraints:
     * Number of Nodes will not exceed 1000.
     * 1 <= Node.val <= 10^5
     */
    public static Node flatten1(Node head) {
        if (head == null) {
            return null;
        }
        flattenHelper(head, null, null);
        return head;
    }

    public static void flattenHelper(Node head, Node parent, Node start) {
        if (head == null) {
            return;
        }
        if (head.child != null) {
            flattenHelper(head.child, head, head.child);
        }
        if (head.next != null) {
            flattenHelper(head.next, parent, start);
        } else if (parent != null && start != null) {
            Node temp = parent.next;
            parent.next = start;
            start.prev = parent;
            parent.child = null;
            head.next = temp;
            if (temp != null) {
                temp.prev = head;
            }
        }
    }

    public static Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = null;
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            Node next = node.next;
            Node child = node.child;
            if (cur != null) {
                cur.next = node;
                node.prev = cur;
            }
            cur = node;
            if (next != null) {
                stack.push(next);
            }
            if (child != null) {
                stack.push(child);
                node.child = null;
            }
        }
        return head;
    }

    public static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    ", child=" + child +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(flatten(Utils.arrayToMultilevleDoublyNode(new Integer[]{1, 2, 3, 4, 5, 6},
                new Integer[]{null, null, 7, 8, 9, 10},
                new Integer[]{null, null, 11, 12, 333})));
    }
}

