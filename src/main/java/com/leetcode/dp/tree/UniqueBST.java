package com.leetcode.dp.tree;


/**
 * @author zms
 * @date 4:34 下午 2020/9/3
 * <a href="https://leetcode.com/problems/unique-binary-search-trees/">
 * Unique Binary Search Trees</a>
 */
public class UniqueBST {
    /**
     * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
     *
     * Example:
     *
     * Input: 3
     * Output: 5
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
     * 1 <= n <= 19
     */
    // 从序列 1 ..n 取出数字 i 并以它作为当前树的根节点。
    // 那么就有 i - 1 个元素可以用来构造左子树，
    // 而另外的 n - i 个元素可以用于构造右子树。
    // 最后我们将会得到 G(i - 1) 棵不同的左子树，以及 G(n - i) 棵不同的右子树，
    public static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numTrees(3));
        System.out.println(numTrees(4));
    }
}
