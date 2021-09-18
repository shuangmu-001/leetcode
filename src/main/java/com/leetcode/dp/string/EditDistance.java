package com.leetcode.dp.string;

/**
 * @author zms
 * @date 5:23 下午 2021/4/27
 * <a href="https://leetcode.com/problems/edit-distance/">Edit Distance</a>
 */
public class EditDistance {
    /**
     * Given two strings word1 and word2,
     * return the minimum number of operations required to convert word1 to word2.
     * You have the following three operations permitted on a word:
     * Insert a character
     * Delete a character
     * Replace a character
     * <p>
     * Example 1:
     * Input: word1 = "horse", word2 = "ros"
     * Output: 3
     * Explanation:
     * horse -> rorse (replace 'h' with 'r')
     * rorse -> rose (remove 'r')
     * rose -> ros (remove 'e')
     * <p>
     * Example 2:
     * Input: word1 = "intention", word2 = "execution"
     * Output: 5
     * Explanation:
     * intention -> inention (remove 't')
     * inention -> enention (replace 'i' with 'e')
     * enention -> exention (replace 'n' with 'x')
     * exention -> exection (replace 'n' with 'c')
     * exection -> execution (insert 'u')
     * <p>
     * Constraints:
     * 0 <= word1.length, word2.length <= 500
     * word1 and word2 consist of lowercase English letters.
     */
    // 状态: dp[i][j]
    // 状态转移方程：
    // 情况一：当w2[j] == w1[i]时 dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i][j - 1] + 1, dp[i - 1][j] + 1)
    // 情况二：当w2[j] != w1[i]时 dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, dp[i][j - 1] + 1, dp[i - 1][j] + 1)
    // 初始化：
    // 当w2为空，最少需要的次数是w1的长度
    // 当w1为空，最少需要的次数是w2的长度
    public static int minDistance(String w1, String w2) {
        int n = w1.length();
        int m = w2.length();
        if (m == 0 || n == 0) {
            return Math.max(m, n);
        }
        char[] c1 = w1.toCharArray();
        char[] c2 = w2.toCharArray();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1][0] = i + 1;
        }
        for (int i = 0; i < m; i++) {
            dp[0][i + 1] = i + 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1);
                if(c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i][j]);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, dp[i][j]);
                }
            }
//            Utils.printTwoArrays(dp);
        }
        return dp[n][m];
    }
    // TODO 空间压缩

    public static void main(String[] args) {
        System.out.println(0 == minDistance("", ""));
        System.out.println(3 == minDistance("", "122"));
        System.out.println(4 == minDistance("1111", ""));
        System.out.println(5 == minDistance("intention", "execution"));
        System.out.println(3 == minDistance("horse", "ros"));
    }
}
