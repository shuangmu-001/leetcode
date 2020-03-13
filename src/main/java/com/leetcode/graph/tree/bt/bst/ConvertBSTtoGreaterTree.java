package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

import static com.leetcode.graph.tree.bt.bst.ConvertSortedArrayToBST.sortedArrayToBST;

/**
 * @author wcl
 * @date 1:06 PM 2020/3/13
 * {@link "https://leetcode.com/problems/convert-bst-to-greater-tree/"}
 */
public class ConvertBSTtoGreaterTree {
    /**
     * Given a Binary Search Tree (BST),
     * convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
     *
     * Example:
     *
     * Input: The root of a Binary Search Tree like this:
     *               5
     *             /   \
     *            2     13
     *
     * Output: The root of a Greater Tree like this:
     *              18
     *             /   \
     *           20     13
     */
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = sortedArrayToBST(new int[]{2});
        System.out.println("before : " + treeNode1);
        System.out.println("after : " + new ConvertBSTtoGreaterTree().convertBST(treeNode1));
        TreeNode treeNode2 = sortedArrayToBST(new int[]{2, 3});
        System.out.println("before : " + treeNode2);
        System.out.println("after : " + new ConvertBSTtoGreaterTree().convertBST(treeNode2));
        TreeNode treeNode3 = sortedArrayToBST(new int[]{2, 5, 13});
        System.out.println("before : " + treeNode3);
        System.out.println("after : " + new ConvertBSTtoGreaterTree().convertBST(treeNode3));
        TreeNode treeNode4 = sortedArrayToBST(new int[]{2, 5, 13, 17});
        System.out.println("before : " + treeNode4);
        System.out.println("after : " + new ConvertBSTtoGreaterTree().convertBST(treeNode4));
    }

    class Solution {
        /* Get the node with the smallest value greater than this one. */
        private TreeNode getSuccessor(TreeNode node) {
            TreeNode succ = node.right;
            while (succ.left != null && succ.left != node) {
                succ = succ.left;
            }
            return succ;
        }

        public TreeNode convertBST(TreeNode root) {
            int sum = 0;
            TreeNode node = root;

            while (node != null) {
                /*
                 * If there is no right subtree, then we can visit this node and
                 * continue traversing left.
                 */
                if (node.right == null) {
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
                /*
                 * If there is a right subtree, then there is at least one node that
                 * has a greater value than the current one. therefore, we must
                 * traverse that subtree first.
                 */
                else {
                    TreeNode succ = getSuccessor(node);
                    /*
                     * If the left subtree is null, then we have never been here before.
                     */
                    if (succ.left == null) {
                        succ.left = node;
                        node = node.right;
                    }
                    /*
                     * If there is a left subtree, it is a link that we created on a
                     * previous pass, so we should unlink it and visit this node.
                     */
                    else {
                        succ.left = null;
                        sum += node.val;
                        node.val = sum;
                        node = node.left;
                    }
                }
            }

            return root;
        }
    }

}
