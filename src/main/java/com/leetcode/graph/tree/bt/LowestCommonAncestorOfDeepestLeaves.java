package com.leetcode.graph.tree.bt;

/**
 * @author wcl
 * @date 5:51 PM 2020/3/20
 * <a href="https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/">
 *     Lowest Common Ancestor of Deepest Leaves</a>
 * @see LowestCommonAncestor
 */
public class LowestCommonAncestorOfDeepestLeaves {
    /**
     * Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.
     * Recall that:
     *      The node of a binary tree is a leaf if and only if it has no children
     *      The depth of the root of the tree is 0, and if the depth of a node is d, the depth of each of its children is d+1.
     *      The lowest common ancestor of a set S of nodes is the node A with the largest depth such that every node in S is in the subtree with root A.
     *
     * Example 1:
     *      Input: root = [1,2,3]
     *      Output: [1,2,3]
     *      Explanation:
     *          The deepest leaves are the nodes with values 2 and 3.
     *          The lowest common ancestor of these leaves is the node with value 1.
     *          The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".
     *
     * Example 2:
     *      Input: root = [1,2,3,4]
     *      Output: [4]
     *
     * Example 3:
     *      Input: root = [1,2,3,4,5]
     *      Output: [2,4,5]
     *
     * Constraints:
     *      The given tree will have between 1 and 1000 nodes.
     *      Each node of the tree will have a distinct value between 1 and 1000.
     */
    int maxDepth;
    int count;
    TreeNode ancestor;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        maxDepth = maxDepth(root);
        traversal(root,1,root);
        return ancestor;
    }
    public void traversal(TreeNode root, int depth, TreeNode ancestor) {
        if(root == null) {
            return;
        }
        if(depth == maxDepth) {
            this.ancestor = ancestor;
            count++;
        }
        if(count > 0) {
            traversal(root.left, depth + 1, ancestor);
        } else {
            traversal(root.left, depth + 1, root.left);
        }
        if(count > 0) {
            traversal(root.right, depth + 1, ancestor);
        } else {
            traversal(root.right, depth + 1, root.right);
        }
    }
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode root01 = new TreeNode(1);
        TreeNode left11 = new TreeNode(3);
        TreeNode right11 = new TreeNode(2);
        TreeNode left12 = new TreeNode(4);

        root01.left = left11;
        root01.right = right11;
        left11.left = left12;
        System.out.println(new LowestCommonAncestorOfDeepestLeaves().lcaDeepestLeaves(root01));


    }
}
