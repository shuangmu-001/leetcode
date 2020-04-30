package com.leetcode.dp;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 10:54 AM 2020/4/28
 * TODO <a href="https://leetcode.com/problems/maximal-rectangle/">
 *     Maximal Rectangle</a>
 */
public class MaximalRectangle {
    /**
     * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
     *
     * Example:
     *
     * Input:
     * [
     *   ["1","0","1","0","0"],
     *   ["1","0","1","1","1"],
     *   ["1","1","1","1","1"],
     *   ["1","0","0","1","0"]
     * ]
     * Output: 6
     */
    public static int maximalRectangle1(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        // 长，宽
        int[][][] dp = new int[m][n][2];
        if(matrix[0][0] == '1') {
            dp[0][0][0] = 1;
            dp[0][0][1] = 1;
        }
        int max = 0;
        for (int i = 1; i < m; i++) {
            if(matrix[i][0] == '1') {
                dp[i][0][0] = dp[i - 1][0][0] + 1;
                dp[i][0][1] = 1;
                max = Math.max(dp[i][0][0], max);
            }
        }
        for (int i = 1; i < n; i++) {
            if(matrix[0][i] == '1') {
                dp[0][i][1] = dp[0][i - 1][1] + 1;
                dp[0][i][0] = 1;
                max = Math.max(dp[0][i][1], max);
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j][0] = dp[i - 1][j][0] + 1;
                    dp[i][j][1] = dp[i][j - 1][1] + 1;
                    int curMax = 0;
                    int width = Integer.MAX_VALUE;
                    for (int k = 0; k < dp[i][j][0]; k++) {
                        width = Math.min(dp[i - k][j][1], width);
                        curMax = Math.max(width * (k + 1), curMax);
                    }
//                    int length = Integer.MAX_VALUE;
//                    for (int k = 0; k < dp[i][j][1]; k++) {
//                        length = Math.min(dp[i][j - k][0], length);
//                        curMax = Math.max(length * (k + 1), curMax);
//                    }
                    System.out.println(curMax);
                    max = Math.max(max, curMax);
                }
            }
        }
        Utils.printThreeArrays(dp);
        return max;
    }

    public static int maximalRectangle2(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        // 长
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(j == 0) {
                    dp[i][j] = matrix[i][j] - '0';
                } else {
                    dp[i][j] = matrix[i][j] == '1' ? dp[i][j - 1] + 1 : 0;
                }
            }
        }
        int max = dp[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i != 0) {
                    int width = 1;
                    int length = dp[i][j];
                    while(i - width + 1 >= 0 && dp[i - width + 1][j] != 0) {
                        length = Math.min(dp[i - width + 1][j], length);
                        max = Math.max(max, length * width);
                        width++;
                    }
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
    // TODO
    public static int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        // left  最左边，
        // right 最右边
        // height
        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maximalRectangle(new char[][]{
                {'0','1','1','0','1'},
                {'1','1','0','1','0'},
                {'0','1','1','1','0'},
                {'1','1','1','1','0'},
                {'1','1','1','1','1'},
                {'0','0','0','0','0'}
        }) == 9);

        System.out.println(maximalRectangle(new char[][]{
                {'1','0','1','0','0'},
                {'1','0','0','1','1'},
                {'1','1','1','1','1'},
                {'0','0','0','1','0'}
        }) == 5);
    }
}
