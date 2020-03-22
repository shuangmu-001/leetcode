package com.leetcode.graph.tree.bt;

import com.leetcode.graph.tree.linkedList.ListNode;

/**
 * @author wcl
 * @date 8:57 PM 2020/3/22
 * <a href="https://leetcode.com/problems/linked-list-in-binary-tree/">
 *     Linked List in Binary Tree</a>
 */
public class LinkedListInBinaryTree {
    /**
     * Given a binary tree root and a linked list with head as the first node.
     * Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.
     * In this context downward path means a path that starts at some node and goes downwards.
     *
     * Example 1:
     *      Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
     *      Output: true
     *      Explanation: Nodes in blue form a subpath in the binary Tree.
     *
     * Example 2:
     *      Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
     *      Output: true
     *
     * Example 3:
     *      Input: head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
     *      Output: false
     *      Explanation: There is no path in the binary tree that contains all the elements of the linked list from head.
     *
     * Constraints:
     *      1 <= node.val <= 100 for each node in the linked list and binary tree.
     *      The given linked list will contain between 1 and 100 nodes.
     *      The given binary tree will contain between 1 and 2500 nodes.
     */
    private boolean result;
    public boolean isSubPath1(ListNode head, TreeNode root) {
        isSubPathHelper(head, head, root);
        return result;
    }
    // error
    public void isSubPathHelper(ListNode head, ListNode cur, TreeNode root) {
        if(root == null || result) {
            return;
        }
        if(root.val == cur.val) {
            cur = cur.next;
            // head 2 2 1  root 2 2 2 1 return true 不能是false
        } else if(root.val == head.val) {
            cur = head.next;
        } else {
            cur = head;
        }
        if(cur == null) {
            result = true;
        }
        isSubPathHelper(head, cur, root.left);
        isSubPathHelper(head, cur, root.right);
    }

    public boolean isSubPath2(ListNode head, TreeNode root) {
        if(root == null) {
            return false;
        }
        if(isSubPathHelper(head, root)) {
            return true;
        }
        return isSubPath2(head, root.left) || isSubPath2(head, root.right);
    }
    public boolean isSubPathHelper(ListNode cur, TreeNode root) {
       if(cur == null) {
           return true;
       }
       if(root == null) {
           return false;
       }
       if(root.val != cur.val) {
           return false;
       }
       return isSubPathHelper(cur.next, root.left) || isSubPathHelper(cur.next, root.right);
    }
    // other error 0 ms 提交了错误的测试用例
    public boolean isSubPath(ListNode node, TreeNode root) {
        if(root!=null && node!=null && root.val==node.val && node.next==null) {
            return true;
        }
        if(root==null && node!=null) {
            return  false;
        }
        // node 2, 1, 3 root 2, 1, 1, 3
        if(node.val == root.val && node.next!=null) {
            if (
                    (root.left!=null && root.left.val==node.next.val) ||
                            (root.right!=null &&  root.right.val == node.next.val)) {
                node = node.next;
            }
        }
        return isSubPath(node,root.left) || isSubPath(node,root.right);
    }
}
