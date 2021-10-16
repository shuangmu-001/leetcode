package com.leetcode.dp;

import com.Utils;

/**
 * <a href="https://leetcode.com/problems/01-matrix/">0 1Matrix</a>
 *
 * @author zms
 * @date 10:52 下午 2021/10/16
 */
public class Matrix {

    /**
     * 给定一个由 0 和 1 组成的矩阵 mat，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
     * 两个相邻元素间的距离为 1 。
     * <p>
     * 示例 1：
     * 输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
     * 输出：[[0,0,0],[0,1,0],[0,0,0]]
     * <p>
     * 示例 2：
     * 输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
     * 输出：[[0,0,0],[0,1,0],[1,2,1]]
     * <p>
     * 提示：
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 10^4
     * 1 <= m * n <= 10^4
     * mat[i][j] is either 0 or 1.
     * mat 中至少有一个 0
     */
    public static int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = Integer.MAX_VALUE - 10000;
            }
        }
        dp[0][0] = mat[0][0] == 0 ? 0 : dp[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = mat[0][i] == 0 ? 0 : Math.min(dp[0][i], dp[0][i - 1] + 1);
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = mat[i][0] == 0 ? 0 : Math.min(dp[i][0], dp[i - 1][0] + 1);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (mat[i][j] != 0) {
                    dp[i][j] = Math.min(dp[i][j], Math.min(dp[i - 1][j], dp[i][j - 1]) + 1);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = m - 2; i >= 0; i--) {
            dp[n - 1][i] = Math.min(dp[n - 1][i], dp[n - 1][i + 1] + 1);
        }
        for (int i = n - 2; i >= 0; i--) {
            dp[i][m - 1] = Math.min(dp[i][m - 1], dp[i + 1][m - 1] + 1);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    dp[i][j] = Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1]) + 1);
                }
            }
        }

        return dp;
    }

    public static void main(String[] args) {
        int[][] mat = {{0, 1, 0}, {1, 1, 1}, {1, 1, 1}};
        Utils.printTwoArrays(updateMatrix(mat));
    }

}
