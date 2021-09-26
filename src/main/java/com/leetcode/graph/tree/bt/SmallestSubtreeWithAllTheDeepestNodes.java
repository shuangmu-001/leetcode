package com.leetcode.graph.tree.bt;

/**
 * @author zms
 * @date 11:36 PM 2020/3/21
 * <a href="https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/">
 *     Smallest Subtree with all the Deepest Nodes</a>
 * @see LowestCommonAncestorOfDeepestLeaves
 */
public class SmallestSubtreeWithAllTheDeepestNodes {
    /**
     * Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
     *
     * A node is deepest if it has the largest depth possible among any node in the entire tree.
     *
     * The subtree of a node is that node, plus the set of all descendants of that node.
     *
     * Return the node with the largest depth such that it contains all the deepest nodes in its subtree.
     *
     *
     *
     * Example 1:
     *
     * Input: [3,5,1,6,2,0,8,null,null,7,4]
     * Output: [2,7,4]
     * Explanation:
     *
     *
     *
     * We return the node with value 2, colored in yellow in the diagram.
     * The nodes colored in blue are the deepest nodes of the tree.
     * The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
     * The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
     * Both the input and output have TreeNode type.
     *
     *
     * Note:
     *
     * The number of nodes in the tree will be between 1 and 500.
     * The values of each node are unique.
     */
    private int maxDepth;
    private TreeNode subtree;
    private int count;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        maxDepth = maxDepth(root);
        subtreeWithAllDeepestHelper(root, 1, root);
        return subtree;
    }

    public void subtreeWithAllDeepestHelper(TreeNode root, int depth, TreeNode ancestor) {
        if(root == null) {
            return;
        }
        if(maxDepth == depth) {
            subtree = ancestor;
            count++;
        }
        if(count > 0) {
            subtreeWithAllDeepestHelper(root.left, depth + 1, ancestor);
        } else {
            subtreeWithAllDeepestHelper(root.left, depth + 1, root.left);
        }

        if(count > 0) {
            subtreeWithAllDeepestHelper(root.right, depth + 1, ancestor);
        } else {
            subtreeWithAllDeepestHelper(root.right, depth + 1, root.right);
        }


    }

    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
