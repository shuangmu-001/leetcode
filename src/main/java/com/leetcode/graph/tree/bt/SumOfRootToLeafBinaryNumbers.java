package com.leetcode.graph.tree.bt;

/**
 * @author wcl
 * @date 4:05 PM 2020/3/9
 * {@link "https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/"}
 */
public class SumOfRootToLeafBinaryNumbers {
    /**
     * Given a binary tree, each node has value 0 or 1.
     * Each root-to-leaf path represents a binary number starting with the most significant bit.
     * For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
     * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
     * Return the sum of these numbers.
     *
     * Example 1:
     *      Input: [1,0,1,0,1,0,1]
     *      Output: 22
     *      Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
     *
     * Note:
     *      The number of nodes in the tree is between 1 and 1000.
     *      node.val is 0 or 1.
     *      The answer will not exceed 2^31 - 1.
     */
    private int sum;
    public int sumRootToLeaf(TreeNode root) {
        sum = 0;
        postOrder(root, root.val);
        return sum;
    }

    public void postOrder(TreeNode root, int before) {
        if(root.left != null) {
            postOrder(root.left, (before << 1) + root.left.val);
        }

        if(root.right != null) {
            postOrder(root.right, (before << 1) + root.right.val);
        } else if(root.left == null) {
            sum += (before << 1) + root.val;
        }
    }
}
