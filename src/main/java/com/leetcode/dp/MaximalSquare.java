package com.leetcode.dp;

/**
 * @author wcl
 * @date 3:13 PM 2020/4/27
 * <a href="https://leetcode.com/problems/maximal-square/">
 *     Maximal Square</a>
 */
public class MaximalSquare {
    /**
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
     *
     * Example:
     *
     * Input:
     *
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     *
     * Output: 4
     */
    public int maximalSquare1(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(matrix[i - 1][j - 1] == '1') {
                    int min = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    min = Math.min(dp[i - 1][j - 1], min);
                    dp[i][j] = min + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max * max;
    }
    // 二维数组压缩成一位数组
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
//        int[][] dp = new int[m + 1][n + 1];
        int[] dp = new int[n + 1];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            int prev = 0;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if(matrix[i - 1][j - 1] == '1') {
                    int min = Math.min(dp[j], dp[j - 1]);
                    min = Math.min(prev, min);
                    dp[j] = min + 1;
                    max = Math.max(dp[j], max);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return max * max;
    }
}
