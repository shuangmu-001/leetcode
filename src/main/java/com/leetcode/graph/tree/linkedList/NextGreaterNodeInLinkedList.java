package com.leetcode.graph.tree.linkedList;

import com.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author wcl
 * @date 9:33 PM 2020/4/11
 * <a href="https://leetcode.com/problems/next-greater-node-in-linked-list/">
 *     Next Greater Node In Linked List</a>
 */
public class NextGreaterNodeInLinkedList {
    /**
     * We are given a linked list with head as the first node.
     * Let's number the nodes in the list: node_1, node_2, node_3, ... etc.
     * Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i,
     * node_j.val > node_i.val, and j is the smallest possible choice.
     * If such a j does not exist, the next larger value is 0.
     * Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).
     * Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2, second node value of 1, and third node value of 5.
     *
     * Example 1:
     *      Input: [2,1,5]
     *      Output: [5,5,0]
     *
     * Example 2:
     *      Input: [2,7,4,3,5]
     *      Output: [7,0,5,5,0]
     *
     * Example 3:
     *      Input: [1,7,5,1,9,2,5,1]
     *      Output: [7,9,9,9,0,5,0,0]
     *
     * Note:
     *      1 <= node.val <= 10^9 for each node in the linked list.
     *      The given list has length in the range [0, 10000].
     */
    // 暴力破解
    public static int[] nextLargerNodes1(ListNode head) {
        List<Integer> vals = new ArrayList<>();
        while (head != null) {
            vals.add(head.val);
            head = head.next;
        }
        int[] res = new int[vals.size()];
        for (int i = 0; i < vals.size() - 1; i++) {
            for (int j = i + 1; j < vals.size(); j++) {
                if (vals.get(i) < vals.get(j)) {
                    res[i] = vals.get(j);
                    break;
                }
            }
        }
        return res;
    }

    public static int[] nextLargerNodes2(ListNode head) {
        List<Integer> vals = new ArrayList<>();
        while (head != null) {
            int val = head.val;
            vals.add(val);
            head = head.next;
        }

        int[] res = new int[vals.size()];
        for (int i = vals.size() - 2; i >= 0 ; i--) {
            if(vals.get(i) < vals.get(i + 1)) {
                res[i] = vals.get(i + 1);
            } else if (vals.get(i).equals(vals.get(i + 1))){
                res[i] = res[i + 1];
            } else {
                for (int j = i + 1; j < vals.size(); j++) {
                    if(vals.get(i) < res[j]) {
                        res[i] = res[j];
                        break;
                    }
                }
            }
        }
        return res;
    }

    public static int[] nextLargerNodes(ListNode head) {
        if(head == null) {
            return new int[0];
        }
        int len = 0;
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            int val = head.val;
            stack.push(val);
            head = head.next;
            len++;
        }

        int[] res = new int[len];
        Stack<Integer> stack1 = new Stack<>();
        for (int i = len - 1; i >= 0 ; i--) {
            int cur = stack.pop();
            while(!stack1.isEmpty()) {
                int before = stack1.pop();
                if(before > cur) {
                    res[i] = before;
                    stack1.push(before);
                    break;
                }
            }
            stack1.push(cur);
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode node1 = Utils.arrayToListNode(new int[]{2,1,5});
        System.out.println(Arrays.toString(nextLargerNodes(node1)));

        ListNode node2 = Utils.arrayToListNode(new int[]{2,7,4,3,5});
        System.out.println(Arrays.toString(nextLargerNodes(node2)));

        ListNode node3 = Utils.arrayToListNode(new int[]{1,7,5,1,9,2,5,1});
        System.out.println(Arrays.toString(nextLargerNodes(node3)));

        ListNode node4 = Utils.arrayToListNode(new int[]{9,7,6,7,6,9});
        System.out.println(Arrays.toString(nextLargerNodes(node4)));
    }

}
