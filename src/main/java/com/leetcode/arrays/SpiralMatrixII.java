package com.leetcode.arrays;

import com.Utils;

/**
 * <a href="https://leetcode.com/problems/spiral-matrix-ii/">Spiral Matrix II</a>
 *
 * @author zms
 * @date 9:22 下午 2021/11/28
 */
public class SpiralMatrixII {
    /**
     * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n^2 in spiral order.
     * <p>
     * Example 1:
     * Input: n = 3
     * Output: [[1,2,3],[8,9,4],[7,6,5]]
     * <p>
     * Example 2:
     * Input: n = 1
     * Output: [[1]]
     * <p>
     * Constraints:
     * 1 <= n <= 20
     */
    public static int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int index = 1;
        int l = 0, r = 0;
        int way = 0;
        int[][] ways = {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0},
        };
        for (int i = 0; i < n * n; i++) {
            ans[l][r] = index++;
            if (way == 0 && (r >= n - 1 || ans[l][r + 1] != 0)) {
                way = 1;
            } else if (way == 1 && (l >= n - 1 || ans[l + 1][r] != 0)) {
                way = 2;
            } else if (way == 2 && (r <= 0 || ans[l][r - 1] != 0)) {
                way = 3;
            } else if( way == 3 && (l <= 0 || ans[l - 1][r] != 0)) {
                way = 0;
            }
            l += ways[way][0];
            r += ways[way][1];
        }
        return ans;
    }

    public static void main(String[] args) {
        Utils.printTwoArrays(generateMatrix(3));
        Utils.printTwoArrays(generateMatrix(4));
        Utils.printTwoArrays(generateMatrix(1));
        Utils.printTwoArrays(generateMatrix(8));
    }
}
