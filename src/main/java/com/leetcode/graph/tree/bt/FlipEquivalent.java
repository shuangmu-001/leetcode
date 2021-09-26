package com.leetcode.graph.tree.bt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zms
 * @date 9:03 PM 2020/3/21
 * <a href="https://leetcode.com/problems/flip-equivalent-binary-trees/">
 *     Flip Equivalent Binary Trees</a>
 */
public class FlipEquivalent {
    /**
     * For a binary tree T, we can define a flip operation as follows:
     * choose any node, and swap the left and right child subtrees.
     * A binary tree X is flip equivalent to a binary tree Y if
     * and only if we can make X equal to Y after some number of flip operations.
     * Write a function that determines whether two binary trees are flip equivalent.
     * The trees are given by root nodes root1 and root2.
     *
     * Example 1:
     *
     * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
     * Output: true
     * Explanation: We flipped at nodes with values 1, 3, and 5.
     * Flipped Trees Diagram
     *
     * Note:
     *      Each tree will have at most 100 nodes.
     *      Each value in each tree will be a unique integer in the range [0, 99].
     */
    public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if((root1 == null && root2 != null) || (root1 != null && root2 == null)) {
            return false;
        }
        if(root1 == null ) {
            return true;
        }

        Queue<TreeNode> queue1 = new LinkedList<>();
        queue1.add(root1);
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue2.add(root2);
        while(!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode poll1 = queue1.poll();
            TreeNode poll2 = queue2.poll();
            if(poll1.val != poll2.val) {
                return false;
            }
            if(poll1.left == null && poll2.left == null && poll1.right == null && poll2.right == null) {
                continue;
            }
            if(poll1.left != null && poll2.left != null && poll1.left.val == poll2.left.val) {
                queue1.add(poll1.left);
                queue2.add(poll2.left);
            } else if(poll1.left != null && poll2.right != null) {
                if(poll1.left.val != poll2.right.val) {
                    return false;
                } else {
                    queue1.add(poll1.left);
                    queue2.add(poll2.right);
                }
            } else if(poll1.left != null) {
                return false;
            }
            if(poll1.right != null && poll2.right != null && poll1.right.val == poll2.right.val) {
                queue1.add(poll1.right);
                queue2.add(poll2.right);
            } else if(poll1.right != null && poll2.left != null) {
                if(poll1.right.val != poll2.left.val) {
                    return false;
                } else {
                    queue1.add(poll1.right);
                    queue2.add(poll2.left);
                }
            } else if(poll1.right != null) {
                return false;
            }

        }

        return queue1.isEmpty() && queue2.isEmpty();
    }
    // other
    public boolean flipEquiv1(TreeNode root1, TreeNode root2) {
        if (root1 == root2) {
            return true;
        }
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }

        return (flipEquiv1(root1.left, root2.left) && flipEquiv1(root1.right, root2.right) ||
                flipEquiv1(root1.left, root2.right) && flipEquiv1(root1.right, root2.left));
    }
    public static void main(String[] args) {
        TreeNode root01 = new TreeNode(0);
        TreeNode left11 = new TreeNode(3);
        TreeNode right11 = new TreeNode(1);
        TreeNode left12 = new TreeNode(2);
        root01.left = left11;
        root01.right = right11;
        left11.left = left12;

        TreeNode root02 = new TreeNode(0);
        TreeNode left21 = new TreeNode(3);
        TreeNode right21 = new TreeNode(1);
        root02.left = left21;
        root02.right = right21;
        TreeNode right24 = new TreeNode(2);
        right21.right = right24;

        System.out.println(flipEquiv(root01, root02));
    }
}
