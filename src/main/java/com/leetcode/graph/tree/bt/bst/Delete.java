package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * <a href="https://leetcode.com/problems/delete-node-in-a-bst/">Delete TreeNode in a BST</a>
 *
 * @author zms
 * @date 3:53 PM 2020/3/20
 */
public class Delete {
    /**
     * Given a root node reference of a BST and a key,
     * delete the node with the given key in the BST.
     * Return the root node reference (possibly updated) of the BST.
     * <p>
     * Basically, the deletion can be divided into two stages:
     * Search for a node to remove.
     * If the node is found, delete the node.
     * Note: Time complexity should be O(height of tree).
     * <p>
     * Example:
     * <p>
     * root = [5,3,6,2,4,null,7]
     * key = 3
     * <p>
     * 5
     * / \
     * 3   6
     * / \   \
     * 2   4   7
     * <p>
     * Given key to delete is 3. So we find the node with value 3 and delete it.
     * <p>
     * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
     * <p>
     * 5
     * / \
     * 4   6
     * /     \
     * 2       7
     * <p>
     * Another valid answer is [5,2,6,null,4,null,7].
     * <p>
     * 5
     * / \
     * 2   6
     * \   \
     * 4   7
     */
    public TreeNode deleteNode01(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        TreeNode dummy = root;
        TreeNode parent = null;

        while (dummy != null) {
            if (dummy.val == key) {
                break;
            }
            parent = dummy;
            if (dummy.val < key) {
                dummy = dummy.right;
            } else {
                dummy = dummy.left;
            }
        }

        if (dummy == null) {
            return root;
        }
        TreeNode left = dummy.left;
        TreeNode right = dummy.right;
        if (parent == null) {
            root = left == null ? right : left;
        } else if (parent.val < dummy.val) {
            parent.right = left == null ? right : left;
        } else {
            parent.left = left == null ? right : left;
        }
        if (left != null) {
            while (left.right != null) {
                left = left.right;
            }
            left.right = right;
        }

        return root;
    }

    // Recursive
    public TreeNode delNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            if (left != null) {
                while (left.right != null) {
                    left = left.right;
                }
                left.right = right;
            }
            return root.left != null ? root.left : right;
        }
        if (root.val > key) {
            root.left = delNode(root.left, key);
        } else {
            root.right = delNode(root.right, key);
        }
        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        TreeNode node = new TreeNode(-1);
        node.left = root;
        TreeNode dummy = root;
        TreeNode parent = node;
        boolean l = true;
        while (dummy != null) {
            if (dummy.val == key) {
                break;
            }
            parent = dummy;
            if (dummy.val < key) {
                l = false;
                dummy = dummy.right;
            } else {
                l = true;
                dummy = dummy.left;
            }
        }
        // 节点不存在
        if (dummy == null) {
            return root;
        }
        // 后继节点
        TreeNode child = dummy.right;
        TreeNode successor = successor(dummy);
        if(successor == null) {
            child = dummy.left;
        } else {
            successor.left = dummy.left;
        }
        if(l) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        return parent == node ? child : root;
    }

    /**
     * 获取节点的后继节点
     *
     * @param node 目标节点
     * @return 后继节点
     */
    public static TreeNode successor(TreeNode node) {
        if (node == null || node.right == null) {
            return null;
        }
        TreeNode successor = node.right;
        while (successor.left != null) {
            successor = successor.left;
        }
        return successor;
    }
}
