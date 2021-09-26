package com.leetcode.graph.tree.bt;

/**
 * @author zms
 * @date 11:25 AM 2020/3/18
 * @see InorderTraversal
 * @see PreorderTraversal
 * @see PostorderTraversal
 */
public class MorrisTraversal {
    /**
     *
     *           1
     *         /   \
     *        2     3
     *       / \   /
     *      4   5 6
     *
     *          2
     *         / \
     *        4   5
     *             \
     *              1
     *               \
     *                3
     *               /
     *              6
     *
     *         4
     *          \
     *           2
     *            \
     *             5
     *              \
     *               1
     *                \
     *                 3
     *                /
     *               6
     */
    public void inOrder(TreeNode root) {
        TreeNode cur = root;
        TreeNode before;
        while (cur != null) {
            if(cur.left == null) {
                System.out.println(cur.val);
                cur = cur.right;
            } else {
                before = cur.left;
                while(before.right != null) {
                    before = before.right;
                }
                before.right = cur;
                TreeNode temp = cur;
                cur = cur.left;
                temp.left = null;
            }
        }
    }

    public void preOrder(TreeNode root) {
        TreeNode cur = root;
        TreeNode before;
        while (cur != null) {
            System.out.println(cur.val);
            if(cur.left == null) {
                cur = cur.right;
            } else {
                before = cur.left;
                while(before.right != null) {
                    before = before.right;
                }
                before.right = cur.right;
                cur = cur.left;
            }
        }
    }
    //TODO postOrder
    public void postOrder(TreeNode root) {

    }
}
