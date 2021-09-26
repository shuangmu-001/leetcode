package com.leetcode.graph.tree.bt;

/**
 * @author zms
 * @date 3:57 PM 2020/3/17
 * <a href="https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/">
 *     Sum of Nodes with Even-Valued Grandparent</a>
 */
public class SumOfNodesWithEvenValuedGrandparent {
    /**
     * Given a binary tree, return the sum of values of nodes with even-valued grandparent.
     * (A grandparent of a node is the parent of its parent, if it exists.)
     * If there are no nodes with an even-valued grandparent, return 0.
     *
     * Example 1:
     *      Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
     *      Output: 18
     *      Explanation: The red nodes are the nodes with even-value grandparent
     *                  while the blue nodes are the even-value grandparents.
     * Constraints:
     *      The number of nodes in the tree is between 1 and 10^4.
     *      The value of nodes is between 1 and 100.
     */
    private int sum;
    public int sumEvenGrandparent(TreeNode root) {
        postorder(root, null, null);
        return sum;
    }

    public void postorder(TreeNode root, TreeNode gp, TreeNode p) {
        if(root == null) {
            return;
        }
        if(root.left != null) {
            postorder(root.left, p, root);
        }

        if(root.right != null) {
            postorder(root.right, p, root);
        }
        if(gp != null && (gp.val & 1) == 0) {
            sum += root.val;
        }
    }
}
