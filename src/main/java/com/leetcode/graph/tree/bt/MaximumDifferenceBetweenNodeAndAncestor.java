package com.leetcode.graph.tree.bt;

/**
 * @author zms
 * @date 4:26 PM 2020/3/20
 * <a href="https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/">
 *     Maximum Difference Between TreeNode and Ancestor</a>
 */
public class MaximumDifferenceBetweenNodeAndAncestor {
    /**
     * Given the root of a binary tree,
     * find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
     * (A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)
     *
     * Example 1:
     * Input: [8,3,10,1,6,null,14,null,null,4,7,13]
     * Output: 7
     * Explanation:
     * We have various ancestor-node differences, some of which are given below :
     * |8 - 3| = 5
     * |3 - 7| = 4
     * |8 - 1| = 7
     * |10 - 13| = 3
     * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
     *
     * Note:
     *      The number of nodes in the tree is between 2 and 5000.
     *      Each node will have value between 0 and 100000.
     */
    private int maxAncestorDiff = 0;
    public int maxAncestorDiff(TreeNode root) {
//        traversal(root);
//        return maxAncestorDiff;
        return traversal(root, root.val, root.val);
    }
    public NodeInfo traversal(TreeNode root) {
        if(root == null) {
            return null;
        }
        NodeInfo left = traversal(root.left);
        NodeInfo right = traversal(root.right);
        Integer min = null;
        Integer max = root.val;
        if(left != null) {
            if(left.min != null) {
                maxAncestorDiff = Math.max(Math.abs(root.val - left.min), maxAncestorDiff);
                min = Math.min(root.val, left.max);
            }
            if(left.max != null) {
                maxAncestorDiff = Math.max(Math.abs(root.val - left.max), maxAncestorDiff);
                max = Math.max(root.val, left.max);
            }

        }
        if(right != null) {
            if(right.min != null) {
                maxAncestorDiff = Math.max(Math.abs(root.val - right.min), maxAncestorDiff);
                min = min != null ? Math.min(min, right.max) : Math.min(root.val, right.max);
            }
            if(right.max != null) {
                maxAncestorDiff = Math.max(Math.abs(root.val - right.max), maxAncestorDiff);
                max = Math.max(max, right.max);
            }
        }

        return new NodeInfo(min, max);
    }
    class NodeInfo {
        Integer max;
        Integer min;

        public NodeInfo(Integer max, Integer min) {
            this.max = max;
            this.min = min;
        }
    }
    // other
    public int traversal(TreeNode root, int max, int min) {
        if(root == null) {
            return 0;
        }
        max = Math.max(max, root.val);
        min = Math.min(min, root.val);
        int left = traversal(root.left, max, min);
        int right = traversal(root.right, max, min);

        return Math.max(max - min, Math.max(left, right));
    }

}
