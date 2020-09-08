package com.leetcode.dp.tree;

import com.leetcode.graph.tree.bt.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wcl
 * @date 4:34 下午 2020/9/3
 * <a href="https://leetcode.com/problems/unique-binary-search-trees/">
 * Unique Binary Search Trees</a>
 */
public class UniqueBSTII {
    /**
     * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
     *
     * Example:
     *
     * Input: 3
     * Output:
     * [
     *   [1,null,3,2],
     *   [3,2,null,1],
     *   [3,1,null,null,2],
     *   [2,1,3],
     *   [1,null,2,null,3]
     * ]
     * Explanation:
     * Given n = 3, there are a total of 5 unique BST's:
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     *
     *
     * Constraints:
     *
     * 0 <= n <= 8
     */
    public static List<TreeNode> generateTrees(int n) {
        if(n == 0) {
            return new ArrayList<>();
        }
        List<TreeNode>[] dp = new List[n + 1];
        dp[0] = Collections.singletonList(null);
        dp[1] = Collections.singletonList(new TreeNode(1));
        for (int i = 2; i <= n; i++) {
            List<TreeNode> nodes = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                List<TreeNode> leftNodes = dp[j - 1];
                List<TreeNode> rightNodes = getRightNodes(dp[i - j], j);
                for (TreeNode leftNode : leftNodes) {
                    for (TreeNode rightNode : rightNodes) {
                        TreeNode treeNode = new TreeNode(j);
                        treeNode.left = leftNode;
                        treeNode.right = rightNode;
                        nodes.add(treeNode);
                    }
                }
            }
            dp[i] = nodes;
        }
        return dp[n];
    }

    public static List<TreeNode> getRightNodes(List<TreeNode> treeNodes, int i) {
        List<TreeNode> nodes = new ArrayList<>();
        for (TreeNode source : treeNodes) {
            nodes.add(copyTreeNode(source, i));
        }
        return nodes;
    }

    public static TreeNode copyTreeNode(TreeNode source, int i) {
        if(source == null) {
            return null;
        }
        TreeNode target = new TreeNode(source.val + i);
        target.left = copyTreeNode(source.left, i);
        target.right = copyTreeNode(source.right, i);
        return target;
    }

    public static void main(String[] args) {
        List<TreeNode> treeNodes = generateTrees(3);
        treeNodes.forEach(System.out::println);
    }

    public static List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> treeNodes = new ArrayList<>();
        if (start > end) {
            treeNodes.add(null);
            return treeNodes;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftNodes = generateTrees(start, i - 1);
            List<TreeNode> rightNodes = generateTrees(i + 1, end);
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = leftNode;
                    treeNode.right = rightNode;
                    treeNodes.add(treeNode);
                }
            }
        }
        return treeNodes;
    }
}

