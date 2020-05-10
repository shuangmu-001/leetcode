package com.leetcode.graph.tree.bt;


/**
 * @author wcl
 * @date 3:58 PM 2020/3/13
 * {@link "https://leetcode.com/problems/cousins-in-binary-tree/"}
 */
public class Cousins {
    /**
     * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
     * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
     * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
     * Return true if and only if the nodes corresponding to the values x and y are cousins.
     *
     * Example 1:
     *      Input: root = [1,2,3,4], x = 4, y = 3
     *      Output: false
     *
     * Example 2:
     *      Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
     *      Output: true
     *
     * Example 3:
     *      Input: root = [1,2,3,null,4], x = 2, y = 3
     *      Output: false
     *
     * Note:
     *      The number of nodes in the tree will be between 2 and 100.
     *      Each node has a unique integer value from 1 to 100.
     */
    // 深度相同，父节点不同
    int xP = -1;
    int yP = -1;
    int xDepth = -1;
    int yDepth = -1;
    public boolean isCousins(TreeNode root, int x, int y) {
        cousinsHelper(root, root, 0, x, y);
        return xP != yP && xDepth == yDepth;
    }

    public void cousinsHelper(TreeNode root, TreeNode parent, int depth, int x, int y) {
        if(xP != -1 && yP != -1) {
            return;
        }
        if(root != null) {
            int len = depth + 1;
            cousinsHelper(root.left, root, len, x, y);
            if(root.val == x) {
                xP = parent.val;
                xDepth = depth;
            }
            if(root.val == y) {
                yP = parent.val;
                yDepth = depth;
            }
            cousinsHelper(root.right, root, len, x, y);
        }
    }

}
