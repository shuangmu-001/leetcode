package com.leetcode.dp;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 7:38 AM 2020/2/28
 * {@link "https://leetcode.com/problems/count-square-submatrices-with-all-ones/"}
 * @see MaximalSquare
 */
public class CountSquareSubmatricesWithAllOnes {
    /**
     * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
     * Example 1:
     *      Input: matrix =
     *          [
     *              [0,1,1,1],
     *              [1,1,1,1],
     *              [0,1,1,1]
     *          ]
     *      Output: 15
     *      Explanation:
     *          There are 10 squares of side 1.
     *          There are 4 squares of side 2.
     *          There is  1 square of side 3.
     *          Total number of squares = 10 + 4 + 1 = 15.
     * Example 2:
     *      Input: matrix =
     *          [
     *              [1,0,1],
     *              [1,1,0],
     *              [1,1,0]
     *          ]
     *      Output: 7
     *      Explanation:
     *          There are 6 squares of side 1.
     *          There is 1 square of side 2.
     *          Total number of squares = 6 + 1 = 7.
     * Constraints:
     *      1 <= arr.length <= 300
     *      1 <= arr[0].length <= 300
     *      0 <= arr[i][j] <= 1
     */
    public static int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(matrix[i - 1][j - 1] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    res += dp[i][j];
                }
            }
        }
        Utils.printTwoArrays(dp);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(countSquares(new int[][]{
                {1,0,1},
                {1,1,0},
                {1,1,0}
        }));

        System.out.println(countSquares(new int[][]{
                {0,1,1,1},
                {1,1,1,1},
                {0,1,1,1}
        }));
    }
}
