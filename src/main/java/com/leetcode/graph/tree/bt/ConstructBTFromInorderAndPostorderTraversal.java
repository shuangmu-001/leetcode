package com.leetcode.graph.tree.bt;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wcl
 * @date 2:05 PM 2020/3/18
 * <a href="https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/">
 *      Construct Binary Tree from Inorder and Postorder Traversal</a>
 * @see ConstructBTFromPreorderAndInorderTraversal
 */
public class ConstructBTFromInorderAndPostorderTraversal {
    /**
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     * Note:You may assume that duplicates do not exist in the tree.
     *
     * For example, given
     *      inorder = [9,3,15,20,7]
     *      postorder = [9,15,7,20,3]
     * Return the following binary tree:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
    private int postIndex;
    private int inIndex;
    private Set<Integer> set = new HashSet<>();
    boolean first = true;
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        if(first) {
            postIndex = postorder.length - 1;
            inIndex = inorder.length - 1;
            first = false;
        }
        if(postIndex < 0) {
            return null;
        }
        if (postIndex < postorder.length - 1 && postorder[postIndex + 1] == inorder[inIndex]) {
            inIndex--;
            return null;
        }
        if(set.contains(inorder[inIndex])) {
            inIndex--;
            return null;
        }
        TreeNode root = new TreeNode(postorder[postIndex--]);
        set.add(root.val);
        root.right = buildTree(inorder, postorder);
        root.left = buildTree(inorder, postorder);
        return root;
    }
    public static void main(String[] args) {


        System.out.println(new ConstructBTFromInorderAndPostorderTraversal().buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3}));
        System.out.println(new ConstructBTFromInorderAndPostorderTraversal().buildTree(new int[]{9,11,3,15,20,7}, new int[]{11,9,15,7,20,3}));
        System.out.println(new ConstructBTFromInorderAndPostorderTraversal().buildTree(new int[]{9,6,11,3,15,20,7}, new int[]{6,11,9,15,7,20,3}));
        System.out.println(new ConstructBTFromInorderAndPostorderTraversal().buildTree(new int[]{1}, new int[]{1}));
    }
}
