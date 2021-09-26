package com.leetcode.graph.tree.bt;

import java.util.*;

/**
 * @author zms
 * @date 6:08 PM 2020/3/19
 * <a href="https://leetcode.com/problems/print-binary-tree/">
 *     Print Binary Tree</a>
 */
public class PrintBinaryTree {
    /**
     * Print a binary tree in an m*n 2D string array following these rules:
     *
     * The row number m should be equal to the height of the given binary tree.
     * The column number n should always be an odd number.
     * The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
     * Each unused space should contain an empty string "".
     * Print the subtrees following the same rules.
     * Example 1:
     * Input:
     *      1
     *     /
     *    2
     * Output:
     * [["", "1", ""],
     *  ["2", "", ""]]
     * Example 2:
     * Input:
     *      1
     *     / \
     *    2   3
     *     \
     *      4
     * Output:
     * [["", "", "", "1", "", "", ""],
     *  ["", "2", "", "", "", "3", ""],
     *  ["", "", "4", "", "", "", ""]]
     * Example 3:
     * Input:
     *       1
     *      / \
     *     2   5
     *    /
     *   3
     *  /
     * 4
     * Output:
     *
     * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
     *  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
     *  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
     *  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
     */
    public List<List<String>> printTree(TreeNode root) {
        int depth = maxDepth(root);
        List<List<String>> result = new ArrayList<>(depth);
        if(root == null) {
            return result;
        }
        int len = (1<<depth) - 1;
        List<String> str = new ArrayList<>();
        for (int i = 0; i < len; i++) {
           str.add("");
        }
        for (int i = 0; i < depth; i++) {
            result.add(new ArrayList<>(str));
        }
        traversal(result, root, 0, 0, len);

        return result;
    }

    public void traversal(List<List<String>> strs, TreeNode root, int depth, int start, int end) {
        if(root != null) {
            int mid = (start + end) >> 1;
            strs.get(depth).set(mid, "" + root.val);
            traversal(strs, root.left, depth + 1, start, mid);
            traversal(strs, root.right, depth + 1, mid + 1, end);
        }
    }

    public int maxDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }

        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode left = new TreeNode(1);
        root.left = left;
        System.out.println(new PrintBinaryTree().printTree(root));
    }
}
