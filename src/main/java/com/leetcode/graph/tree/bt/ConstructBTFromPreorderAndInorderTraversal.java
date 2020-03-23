package com.leetcode.graph.tree.bt;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wcl
 * @date 2:04 PM 2020/3/18
 * <a href="https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">
 *     Construct Binary Tree from Preorder and Inorder Traversal</a>
 * @see ConstructBTFromInorderAndPostorderTraversal
 */
public class ConstructBTFromPreorderAndInorderTraversal {
    /**
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     * Note:You may assume that duplicates do not exist in the tree.
     *
     * For example, given
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * Return the following binary tree:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
    private int preIndex;
    private int inIndex;
    private Set<Integer> set = new HashSet<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length <= preIndex) {
            return null;
        }

        if(preIndex > 0 && preorder[preIndex - 1] == inorder[inIndex]) {
            inIndex++;
            return null;
        }
        if(set.contains(inorder[inIndex])) {
            inIndex++;
            return null;
        }

        TreeNode root = new TreeNode(preorder[preIndex++]);
        set.add(root.val);
        root.left = buildTree(preorder, inorder);
        root.right = buildTree(preorder, inorder);
        return root;
    }

    public boolean check(int[] preorder, int end, int target) {
        if(preorder.length < end) {
            return false;
        }
        for (int i = 0; i < end; i++) {
            if(preorder[i] == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new ConstructBTFromPreorderAndInorderTraversal().buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}));
        System.out.println(new ConstructBTFromPreorderAndInorderTraversal().buildTree(new int[]{3,9,11,20,15,7}, new int[]{9,11,3,15,20,7}));
        System.out.println(new ConstructBTFromPreorderAndInorderTraversal().buildTree(new int[]{3,9,11,6,20,15,7}, new int[]{9,6,11,3,15,20,7}));
        System.out.println(new ConstructBTFromPreorderAndInorderTraversal().buildTree(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}));
        System.out.println(new ConstructBTFromPreorderAndInorderTraversal().buildTree(new int[]{1,2,3,4,5,6}, new int[]{6,5,4,3,2,1}));
        System.out.println(new ConstructBTFromPreorderAndInorderTraversal().buildTree(new int[]{1}, new int[]{1}));
    }
}
