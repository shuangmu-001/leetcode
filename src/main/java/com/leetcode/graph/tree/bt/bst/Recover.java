package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * @author wcl
 * @date 12:57 PM 2020/3/21
 * TODO <a href="https://leetcode.com/problems/recover-binary-search-tree/">
 *     Recover Binary Search Tree</a>
 */
public class Recover {
    /**
     * Two elements of a binary search tree (BST) are swapped by mistake.
     * Recover the tree without changing its structure.
     *
     * Example 1:
     * Input: [1,3,null,null,2]
     *    1
     *   /
     *  3
     *   \
     *    2
     * Output: [3,1,null,null,2]
     *    3
     *   /
     *  1
     *   \
     *    2
     *
     * Example 2:
     * Input: [3,1,4,null,null,2]
     *   3
     *  / \
     * 1   4
     *    /
     *   2
     * Output: [2,1,4,null,null,3]
     *   2
     *  / \
     * 1   4
     *    /
     *   3
     * Follow up:
     *      A solution using O(n) space is pretty straight forward.
     *      Could you devise a constant space solution?
     */
    int temp;
    TreeNode maxNode;
    public void recoverTree(TreeNode root) {
        if(root == null) {
            return;
        }
        if(root.left != null) {
            recoverTree(root.left);
        }
        if(maxNode!= null && maxNode.val > root.val) {
            temp = maxNode.val;
            maxNode.val = root.val;
            root.val = temp;
        }
        maxNode = root;
        if(root.right != null) {
            recoverTree(root.right);
        }
    }
}
