package com.leetcode.dp.count;

import com.Utils;

/**
 * @author wcl
 * @date 3:09 PM 2020/2/28
 * {@link "https://leetcode.com/problems/unique-paths-ii/"}
 * @see UniquePaths
 */
public class UniquePathsII {
    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time.
     * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
     *
     * Note: m and n will be at most 100.
     *
     * Example 1:
     *      Input:
     *          [
     *              [0,0,0],
     *              [0,1,0],
     *              [0,0,0]
     *          ]
     *      Output: 2
     *      Explanation:
     *          There is one obstacle in the middle of the 3x3 grid above.
     *          There are two ways to reach the bottom-right corner:
     *          1. Right -> Right -> Down -> Down
     *          2. Down -> Down -> Right -> Right
     *
     *  边界问题
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        if(n == 1) {
            for (int i = 0; i < m; i++) {
                if(obstacleGrid[0][i] == 1) {
                    return 0;
                }
            }
            return 1;
        }
        if(obstacleGrid[n - 1][m - 1] == 1){
            return 0;
        }
        int[][] dp = new int[n][m];
        boolean obFlag = false;
        for (int i = n - 1; i >= 0; i--) {
            if(!obFlag) {
                dp[i][m - 1] = 1;
            }
            if(obstacleGrid[i][m - 1] == 1) {
               obFlag = true;
            }
        }

        obFlag = false;
        for (int i = m - 2; i >= 0 ; i--) {
            if(obstacleGrid[n - 1][i] == 1) {
                obFlag = true;
            }
            if(!obFlag) {
                dp[n - 2][i] = 1;
            }
        }
        int result = 0;
        for (int i = n - 3; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                if(obstacleGrid[i + 1][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
                }
            }
        }
        obFlag = false;
        for (int i = 0; i < m; i++) {
            if(obstacleGrid[0][i] == 1) {
               obFlag = true;
            }
            if(obFlag) {
                dp[0][i] = 0;
            }
            result+= dp[0][i];
        }
        Utils.printTwoArrays(dp);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles(new int[][]{
                {0,1,0},
                {0,1,0},
                {0,0,0}
        }));
        System.out.println(uniquePathsWithObstacles(new int[][]{
                {0,1,0,0},
                {0,1,0,1},
                {0,0,0,0},
                {0,0,0,0}
        }) == 4);
        System.out.println(uniquePathsWithObstacles(new int[][]{
                {0,1,0,0,0,1,0,0,0,0,1,1,0,0},
                {0,1,0,0,0,0,0,0,0,0,0,1,1,0},
                {0,1,1,1,0,1,0,1,1,0,0,1,1,0},
                {0,0,0,1,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,1,0,0,0,0,1},
                {0,1,1,0,1,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,0,0,1,0,1,0},
                {0,0,1,0,0,0,0,0,0,0,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,1,0,0,0},
                {1,0,0,0,0,0,0,0,0,1,0,1,0,0},
                {1,0,0,0,1,0,1,0,1,0,0,1,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,1,0,0},
                {0,1,1,1,0,0,0,0,0,0,1,1,1,0},
                {1,1,0,0,0,0,0,0,0,1,0,0,1,0},
                {0,0,0,0,1,0,0,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,1,0},
                {0,1,0,0,0,0,0,1,0,1,0,0,0,0},
                {1,0,0,0,0,1,0,0,0,0,0,0,1,0},
                {0,1,0,0,0,1,0,0,0,1,0,1,1,0},
                {0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {1,0,1,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,1,0},
                {0,0,1,0,0,0,0,0,0,1,1,0,0,0},
                {1,1,0,0,0,0,0,0,0,0,0,0,0,0}
        }) == 10812);
    }

}
