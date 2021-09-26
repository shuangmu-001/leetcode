package com.question.day01;


import com.Test;
import com.Utils;

/**
 * 给定一个二维数组matrix，你可以从任何位置出发，走向上下左右四个方向，返回能走出来的最长的递增链长度
 * <a href="https://leetcode.com/problems/longest-increasing-path-in-a-matrix/">
 * Longest Increasing Path in a Matrix</a>
 *
 * @author zms
 * @date 3:46 下午 2021/9/15
 */
public class Code05LongestIncreasingPath implements Test {

    public int longestIncreasingPath1(int[][] matrix) {
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res = Math.max(res, longestIncreasingPath1Helper(matrix, i, j));
            }
        }
        return res;
    }

    /**
     * 从m[i][j]出发的最长递增链的长度
     *
     * @param m 数组
     * @param i 外部数组索引
     * @param j 内部数组索引
     * @return 最长递增链的长度
     */
    public int longestIncreasingPath1Helper(int[][] m, int i, int j) {
        // 边界问题
        if (i >= m.length || i < 0 || j < 0 || j >= m[0].length) {
            return 0;
        }

        // 第一步向上的最长递增链
        int up = i > 0 && m[i - 1][j] > m[i][j] ? longestIncreasingPath1Helper(m, i - 1, j) : 0;
        // 第一步向下的最长递增链
        int down = i < m.length - 1 && m[i + 1][j] > m[i][j] ? longestIncreasingPath1Helper(m, i + 1, j) : 0;
        // 第一步向左的最长递增链
        int left = j > 0 && m[i][j - 1] > m[i][j] ? longestIncreasingPath1Helper(m, i, j - 1) : 0;
        // 第一步向右的最长递增链
        int right = j < m[0].length - 1 && m[i][j + 1] > m[i][j] ? longestIncreasingPath1Helper(m, i, j + 1) : 0;

        return Math.max(up, Math.max(down, Math.max(left, right))) + 1;
    }

    public int longestIncreasingPath2(int[][] matrix) {
        int res = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res = Math.max(res, longestIncreasingPath2Helper(matrix, i, j, dp));
            }
        }
        return res;
    }

    public int longestIncreasingPath2Helper(int[][] m, int i, int j, int[][] dp) {
        // 边界问题
        if (i >= m.length || i < 0 || j < 0 || j >= m[0].length) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        // 第一步向上的最长递增链
        int up = i > 0 && m[i - 1][j] > m[i][j] ? longestIncreasingPath2Helper(m, i - 1, j, dp) : 0;
        // 第一步向下的最长递增链
        int down = i < m.length - 1 && m[i + 1][j] > m[i][j] ? longestIncreasingPath2Helper(m, i + 1, j, dp) : 0;
        // 第一步向左的最长递增链
        int left = j > 0 && m[i][j - 1] > m[i][j] ? longestIncreasingPath2Helper(m, i, j - 1, dp) : 0;
        // 第一步向右的最长递增链
        int right = j < m[0].length - 1 && m[i][j + 1] > m[i][j] ? longestIncreasingPath2Helper(m, i, j + 1, dp) : 0;
        int res = Math.max(up, Math.max(down, Math.max(left, right))) + 1;
        dp[i][j] = res;
        return res;
    }

    @Override
    public void test(int n) {
        int[][] ints = genRandomTwoArr(n);
        if (longestIncreasingPath1(ints) != longestIncreasingPath2(ints)) {
            Utils.printTwoArrays(ints);
        }
    }
}
