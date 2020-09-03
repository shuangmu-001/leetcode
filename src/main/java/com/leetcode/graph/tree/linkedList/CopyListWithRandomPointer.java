package com.leetcode.graph.tree.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wcl
 * @date 2:18 PM 2020/4/9
 * <a href="https://leetcode.com/problems/copy-list-with-random-pointer/">
 * Copy List with Random Pointer</a>
 */
public class CopyListWithRandomPointer {
    /**
     * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
     * Return a deep copy of the list.
     * The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
     * val: an integer representing Node.val
     * random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.
     * <p>
     * Example 1:
     * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * <p>
     * Example 2:
     * Input: head = [[1,1],[2,1]]
     * Output: [[1,1],[2,1]]
     * <p>
     * Example 3:
     * Input: head = [[3,null],[3,0],[3,null]]
     * Output: [[3,null],[3,0],[3,null]]
     * <p>
     * Example 4:
     * Input: head = []
     * Output: []
     * Explanation: Given linked list is empty (null pointer), so return null.
     * <p>
     * Constraints:
     * -10000 <= Node.val <= 10000
     * Node.random is null or pointing to a node in the linked list.
     * Number of Nodes will not exceed 1000.
     */
    public Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node res = new Node(0);
        res.next = head;
        Node prev = res;
        Node dummy = head;
        while (head != null) {
            prev.next = new Node(head.val);
            map.put(head, prev.next);
            prev = prev.next;
            head = head.next;
        }
        prev = res.next;
        while (prev != null) {
            if (dummy.random != null) {
                prev.random = map.get(dummy.random);
            }
            prev = prev.next;
            dummy = dummy.next;
        }
        return res.next;
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node dummy = head;
        while (dummy != null) {
            Node node = new Node(dummy.val);
            Node next = dummy.next;
            dummy.next = node;
            node.next = next;
            dummy = next;
        }
        dummy = head;
        while (dummy != null) {
            if (dummy.random != null) {
                dummy.next.random = dummy.random.next;
            }
            dummy = dummy.next.next;
        }
        Node newHead = new Node(1);
        Node prev = newHead;
        while (head != null) {
            Node next = head.next;
            prev.next = next;
            prev = prev.next;
            head.next = next.next;
            head = head.next;
        }
        return newHead.next;
    }
}
