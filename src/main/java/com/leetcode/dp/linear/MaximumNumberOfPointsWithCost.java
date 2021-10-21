package com.leetcode.dp.linear;

/**
 * <a href="https://leetcode.com/problems/maximum-number-of-points-with-cost/">Maximum Number of Points with Cost</a>
 *
 * @author zms
 * @date 2:13 下午 2021/10/21
 */
public class MaximumNumberOfPointsWithCost {
    /**
     * You are given an m x n integer matrix points (0-indexed). Starting with 0 points,
     * you want to maximize the number of points you can get from the matrix.
     * To gain points, you must pick one cell in each row.
     * Picking the cell at coordinates (r, c) will add points[r][c] to your score.
     * However, you will lose points if you pick a cell too far from the cell that you picked in the previous row.
     * For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2)
     * will subtract abs(c1 - c2) from your score.
     * Return the maximum number of points you can achieve.
     * <p>
     * abs(x) is defined as:
     * x for x >= 0.
     * -x for x < 0.
     * <p>
     * Example 1:
     * Input: points = [[1,2,3],[1,5,1],[3,1,1]]
     * Output: 9
     * Explanation:
     * The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
     * You add 3 + 5 + 3 = 11 to your score.
     * However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
     * Your final score is 11 - 2 = 9.
     * <p>
     * Example 2:
     * Input: points = [[1,5],[2,3],[4,2]]
     * Output: 11
     * Explanation:
     * The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
     * You add 5 + 3 + 4 = 12 to your score.
     * However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
     * Your final score is 12 - 1 = 11.
     * <p>
     * Constraints:
     * m == points.length
     * n == points[r].length
     * 1 <= m, n <= 10^5
     * 1 <= m * n <= 10^5
     * 0 <= points[r][c] <= 10^5
     */
    public long maxPoints(int[][] points) {
        return process01(points, 0, 0);
    }

    // row ：当前是第几行开始选择
    // pre : 前一列选择的第几列
    // 返回从row行开始选的最大值
    public static long process01(int[][] points, int row, int pre) {
        if (row == points.length) {
            return 0;
        }
        long ans = 0;
        for (int i = 0; i < points[0].length; i++) {
            long res = points[row][i] + process01(points, row + 1, i + 1);
            if (pre != 0) {
                res -= Math.abs(pre - i);
            }
            ans = Math.max(ans, res);
        }
        return ans;
    }

    public long maxPoints02(int[][] points) {
        int n = points.length;
        int m = points[0].length;
        long[][] dp = new long[n + 1][m + 1];
        for (int row = n - 1; row >= 0; row--) {
            for (int pre = 0; pre <= m; pre++) {
                long ans = 0;
                for (int i = 0; i < m; i++) {
                    long res = points[row][i] + dp[row + 1][i + 1];
                    if (pre != 0) {
                        res -= Math.abs(pre - i - 1);
                    }
                    ans = Math.max(ans, res);
                }
                dp[row][pre] = ans;
            }
        }
        return dp[0][0];
    }

    // 怎么进行斜率优化 ？
    // a b - 1 c - 2
    // a - 1 b c - 1
    // a - 2 b - 1 c
    public long maxPoints03(int[][] points) {
        int n = points.length;
        int m = points[0].length;
        long[] dp = new long[m];
        long ans = Integer.MIN_VALUE;
        for (int row = n - 1; row >= 0; row--) {
            long[] left = new long[m];
            left[0] = dp[0] + points[row][0];
            for (int i = 1; i < m; i++) {
                left[i] = Math.max(left[i - 1] - points[row][i - 1] - 1, dp[i]) + points[row][i];
            }
            long[] right = new long[m];
            right[m - 1] = dp[m - 1] + points[row][m - 1];
            for (int i = m - 2; i >= 0; i--) {
                right[i] = Math.max(right[i + 1] - points[row][i + 1] - 1, dp[i]) + points[row][i];
            }
            for (int col = 0; col < m; col++) {
                dp[col] = Math.max(left[col], right[col]);
                if (row == 0) {
                    ans = Math.max(ans, dp[col]);
                }
            }
        }
        return ans;
    }
}
