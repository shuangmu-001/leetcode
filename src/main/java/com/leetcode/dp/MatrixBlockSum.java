package com.leetcode.dp;

import com.Utils;

/**
 * {@link "https://leetcode.com/problems/matrix-block-sum/"}
 *
 * @author zms
 * @date 2:48 PM 2020/2/27
 */
public class MatrixBlockSum {
    /**
     * Given a m * n matrix mat and an integer K,
     * return a matrix answer where each answer[i][j] is the sum of all elements
     * mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
     * Example 1:
     * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
     * Output: [[12,21,16],[27,45,33],[24,39,28]]
     * <p>
     * Example 2:
     * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
     * Output: [[45,45,45],[45,45,45],[45,45,45]]
     * <p>
     * Constraints:
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n, K <= 100
     * 1 <= mat[i][j] <= 100
     */
    public static int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            int l = 0, r = 0, sum = 0;
            for (int j = 0; j < n; j++) {
                while (j - l > K) {
                    sum -= mat[i][l++];
                }
                while (r < n && r - j <= K) {
                    sum += mat[i][r++];
                }
                res[i][j] = sum;
            }
        }
        int[][] dp = new int[m][n];
        for (int j = 0; j < n; j++) {
            int l = 0, r = 0, sum = 0;
            for (int i = 0; i < m; i++) {
                while (i - l > K) {
                    sum -= res[l++][j];
                }
                while (r < m && r - i <= K) {
                    sum += res[r++][j];
                }
                dp[i][j] = sum;
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        Utils.printTwoArrays(matrixBlockSum(new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        }, 2));
    }
}
