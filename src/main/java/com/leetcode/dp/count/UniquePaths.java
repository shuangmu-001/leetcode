package com.leetcode.dp.count;

/**
 * @author wcl
 * @date 12:40 PM 2020/2/28
 * {@link "https://leetcode.com/problems/unique-paths/"}
 */
public class UniquePaths {
    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * How many possible unique paths are there?
     * Above is a 7 x 3 grid. How many possible unique paths are there?
     *
     * Note: m and n will be at most 100.
     *
     * Example 1:
     *      Input: m = 3, n = 2
     *      Output: 3
     *      Explanation:
     *          From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
     *          1. Right -> Right -> Down
     *          2. Right -> Down -> Right
     *          3. Down -> Right -> Right
     *
     * Example 2:
     *      Input: m = 7, n = 3
     *      Output: 28
     *
     * dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
     * 4 * 4
     *          10 6 3 1
     *           4 3 2 1
     *           1 1 1 1
     */
    public static int uniquePaths(int m, int n) {
        if(n == 1) {
            return  1;
        }

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
               dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        int result = 0;
        for (int i = 0; i < m; i++) {
            result += dp[n - 1][i];
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3,2) == 3);
        System.out.println(uniquePaths(7,3) == 28);
    }
}
