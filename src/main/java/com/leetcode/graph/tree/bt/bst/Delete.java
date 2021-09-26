package com.leetcode.graph.tree.bt.bst;

/**
 * @author zms
 * @date 3:53 PM 2020/3/20
 * <a href="https://leetcode.com/problems/delete-node-in-a-bst/">
 *     Delete TreeNode in a BST</a>
 */
public class Delete {
    /**
     * Given a root node reference of a BST and a key,
     * delete the node with the given key in the BST.
     * Return the root node reference (possibly updated) of the BST.
     *
     * Basically, the deletion can be divided into two stages:
     *      Search for a node to remove.
     *      If the node is found, delete the node.
     * Note: Time complexity should be O(height of tree).
     *
     * Example:
     *
     * root = [5,3,6,2,4,null,7]
     * key = 3
     *
     *     5
     *    / \
     *   3   6
     *  / \   \
     * 2   4   7
     *
     * Given key to delete is 3. So we find the node with value 3 and delete it.
     *
     * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
     *
     *     5
     *    / \
     *   4   6
     *  /     \
     * 2       7
     *
     * Another valid answer is [5,2,6,null,4,null,7].
     *
     *     5
     *    / \
     *   2   6
     *    \   \
     *     4   7
     */
    public com.leetcode.graph.tree.bt.TreeNode deleteNode(com.leetcode.graph.tree.bt.TreeNode root, int key) {
       if(root == null) {
           return null;
       }
        com.leetcode.graph.tree.bt.TreeNode dummy = root;
        com.leetcode.graph.tree.bt.TreeNode parent = null;

        while (dummy != null) {
            if(dummy.val == key) {
                break;
            }
            parent = dummy;
            if(dummy.val < key) {
                dummy = dummy.right;
            } else {
                dummy = dummy.left;
            }
        }

        if(dummy == null) {
            return root;
        }
        com.leetcode.graph.tree.bt.TreeNode left = dummy.left;
        com.leetcode.graph.tree.bt.TreeNode right = dummy.right;
        if(parent == null) {
            root = left == null ? right : left;
        } else if(parent.val < dummy.val) {
            parent.right = left == null ? right : left;
        } else {
            parent.left = left == null ? right : left;
        }
        if(left != null) {
            while(left.right != null) {
                left = left.right;
            }
            left.right = right;
        }

        return root;
    }
    // Recursive
    public com.leetcode.graph.tree.bt.TreeNode delNode(com.leetcode.graph.tree.bt.TreeNode root, int key) {
        if(root == null) {
            return null;
        }
        if(root.val == key) {
            com.leetcode.graph.tree.bt.TreeNode left = root.left;
            com.leetcode.graph.tree.bt.TreeNode right = root.right;
            if(left != null) {
                while(left.right != null) {
                    left = left.right;
                }
                left.right = right;
            }
            return root.left != null ? root.left : right;
        }
        if(root.val > key) {
            root.left = delNode(root.left ,key);
        } else {
            root.right = delNode(root.right,key);
        }
        return root;
    }
}
