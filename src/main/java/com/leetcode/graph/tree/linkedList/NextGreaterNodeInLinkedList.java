package com.leetcode.graph.tree.linkedList;

import java.util.ArrayList;
import java.util.List;

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
    public int[] nextLargerNodes(ListNode head) {
      List<Integer> vals = new ArrayList<>();
      while(head != null && head.next != null) {
        if(head.next.val < head.val) {
            vals.add(0);
        }
      }
      vals.add(0);
      int[] res = new int[vals.size()];
      int index = 0;
      for(int val : vals) {
          res[index++] = val;
      }
      return res;
    }

    private int max;
    public void nextLargerNodesHelper(ListNode head, List<Integer> nodes) {
//        if(head == null) {
//            return;
//        }
//        nextLargerNodesHelper(head.next, nodes);
//
//        if(head.next == null || max > head.val) {
//            nodes.add(0);
//        } else {
//            max = Math.max()
//            nodes.add(Math.max())
//        }
    }

}
