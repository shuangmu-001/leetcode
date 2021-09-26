package com.leetcode.graph.tree.linkedList;

import com.Utils;
import com.leetcode.graph.tree.bt.TreeNode;

/**
 * @author zms
 * @date 11:05 AM 2020/4/20
 * <a href="https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/">
 *     Convert Sorted List to Binary Search Tree</a>
 */
public class ConvertSortedListToBST {
    /**
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     *
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     *
     * Example:
     *
     * Given the sorted linked list: [-10,-3,0,5,9],
     *
     * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     */
    public static TreeNode sortedListToBST(ListNode head) {
        if(head == null) {
            return null;
        }
        if(head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = head;
        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode node = new TreeNode(slow.val);
        prev.next = null;
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(slow.next);
        return node;
    }

    public static void main(String[] args) {
        Utils.printBT(sortedListToBST(Utils.arrayToListNode(new int[]{-10})));

        Utils.printBT(sortedListToBST(Utils.arrayToListNode(new int[]{-10,-3})));

        Utils.printBT(sortedListToBST(Utils.arrayToListNode(new int[]{-10,-3, 0})));

        Utils.printBT(sortedListToBST(Utils.arrayToListNode(new int[]{-10,-3,0,5,9})));

    }

}
