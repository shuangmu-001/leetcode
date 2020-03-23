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
            postIndex = postorder.length;
            
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(new ConstructBTFromInorderAndPostorderTraversal().buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}));
        System.out.println(new ConstructBTFromInorderAndPostorderTraversal().buildTree(new int[]{3,9,11,20,15,7}, new int[]{9,11,3,15,20,7}));
        System.out.println(new ConstructBTFromInorderAndPostorderTraversal().buildTree(new int[]{3,9,11,6,20,15,7}, new int[]{9,6,11,3,15,20,7}));
        System.out.println(new ConstructBTFromInorderAndPostorderTraversal().buildTree(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}));
        System.out.println(new ConstructBTFromInorderAndPostorderTraversal().buildTree(new int[]{1,2,3,4,5,6}, new int[]{6,5,4,3,2,1}));
        System.out.println(new ConstructBTFromInorderAndPostorderTraversal().buildTree(new int[]{1}, new int[]{1}));
    }
}
