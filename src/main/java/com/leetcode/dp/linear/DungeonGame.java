package com.leetcode.dp.linear;

import com.leetcode.dp.linear.MinimumPathSum;

/**
 * @author zms
 * @date 5:56 PM 2020/4/19
 * <a href="https://leetcode.com/problems/dungeon-game/">
 *     Dungeon Game</a>
 * @see MinimumPathSum
 */
public class DungeonGame {
    /**
     * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
     * The dungeon consists of M x N rooms laid out in a 2D grid.
     * Our valiant knight (K) was initially positioned in the top-left room and must
     * fight his way through the dungeon to rescue the princess.
     * The knight has an initial health point represented by a positive integer.
     * If at any point his health point drops to 0 or below, he dies immediately.
     * Some of the rooms are guarded by demons,
     * so the knight loses health (negative integers) upon entering these rooms;
     * other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
     * In order to reach the princess as quickly as possible,
     * the knight decides to move only rightward or downward in each step.
     *
     * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
     * For example, given the dungeon below,
     * the initial health of the knight must be at least 7
     * if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
     *
     * -2 (K)   -3	    3
     * -5	    -10	    1
     *  10	     30	   -5 (P)
     *
     *
     * Note:
     * The knight's health has no upper bound.
     * Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
     */
    public static int calculateMinimumHP(int[][] dungeon) {
        if(dungeon == null || dungeon.length == 0) {
            return 0;
        }
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] > 0 ? - 1 : dungeon[m - 1][n - 1] - 1;
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = dungeon[i][n - 1] + dp[i + 1][n - 1] >=0 ? -1 : dungeon[i][n - 1] + dp[i + 1][n - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] = dungeon[m - 1][i] + dp[m - 1][i + 1] >= 0 ? -1 : dungeon[m - 1][i] + dp[m - 1][i + 1];
        }
        for (int i = m - 2; i >= 0 ; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int min = Math.max(dp[i][j + 1], dp[i + 1][j]);
                dp[i][j] = min + dungeon[i][j] >= 0 ? -1 : dungeon[i][j] + min;
            }
        }
        return -dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(calculateMinimumHP(new int[][]{
                {-2, -3,  3},
                {-5, -10, 1},
                {10, 30, -5}
        }) == 7);
        System.out.println(calculateMinimumHP(new int[][]{
                {1,-4,5,-99},
                {2,-2,-2,-1}
        }) == 3);
        //[[1,-3,3],[0,-2,0],[-3,-3,-3]]
        System.out.println(calculateMinimumHP(new int[][]{
                { 1, -3,  3},
                { 0, -2,  0},
                {-3, -3, -3}
        }) == 3);
    }
}
