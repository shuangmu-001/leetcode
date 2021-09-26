package com.leetcode.graph.tree.bt;

/**
 * @author zms
 * @date 3:07 PM 2020/3/23
 * <a href="https://leetcode.com/problems/smallest-string-starting-from-leaf/">
 *     Smallest String Starting From Leaf</a>
 */
public class SmallestStringStartingFromLeaf {
    /**
     * Given the root of a binary tree,
     * each node has a value from 0 to 25 representing the letters 'a' to 'z':
     * a value of 0 represents 'a',
     * a value of 1 represents 'b', and so on.
     * Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
     * (As a reminder, any shorter prefix of a string is lexicographically smaller: for example,
     * "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)
     *
     * Example 1:
     *      Input: [0,1,2,3,4,3,4]
     *      Output: "dba"
     *
     * Example 2:
     *      Input: [25,1,3,1,3,0,2]
     *      Output: "adz"
     *
     * Example 3:
     *      Input: [2,2,1,null,1,0,null,0]
     *      Output: "abc"
     *
     * Note:
     *      The number of nodes in the given tree will be between 1 and 8500.
     *      Each node in the tree will have a value between 0 and 25.
     */
    private String minStr;
    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return minStr;
    }
    public void dfs(TreeNode root, StringBuilder builder) {
        if(root == null) {
            return;
        }
        builder = new StringBuilder()
                .append((char)('a' + root.val))
                .append(builder);
        if(null == root.left && null == root.right) {
            if(minStr == null || minStr.compareTo(builder.toString()) > 0) {
                minStr = builder.toString();
            }
        }
        dfs(root.left, builder);
        dfs(root.right, builder);
    }

    public static void main(String[] args) {
        System.out.println((char)('a' + 2));
        System.out.println(new StringBuilder().append((char)('a' + 2)).append(new StringBuilder()).toString());
    }
}
