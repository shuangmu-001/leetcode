package com.leetcode.graph.bfs;

/**
 * <a href="https://leetcode.com/problems/rotting-oranges/">Rotting Oranges</a>
 *
 * @author zms
 * @date 10:10 下午 2021/10/17
 */
public class RottingOranges {
    /**
     * You are given an m x n grid where each cell can have one of three values:
     * <p>
     * 0 representing an empty cell,
     * 1 representing a fresh orange, or
     * 2 representing a rotten orange.
     * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
     * <p>
     * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
     * If this is impossible, return -1.
     * <p>
     * Example 1:
     * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
     * Output: 4
     * <p>
     * Example 2:
     * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
     * Output: -1
     * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten,
     * because rotting only happens 4-directionally.
     * <p>
     * Example 3:
     * Input: grid = [[0,2]]
     * Output: 0
     * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
     * <p>
     * Constraints:
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 10
     * grid[i][j] is 0, 1, or 2.
     */
    public static int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Integer[][] dp = new Integer[n][m];
        boolean[][] flag = new boolean[n][m];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    ans = Math.max(ans, process(grid, i, j, flag));
                }
            }
        }
        return ans;
    }

    public static int process(int[][] grid, int row, int col, boolean[][] flag) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length
                || flag[row][col] || grid[row][col] == 0) {
            return Integer.MAX_VALUE;
        }

        flag[row][col] = true;

        if (grid[row][col] == 2) {
            flag[row][col] = false;
            return 0;
        }

        int left = process(grid, row, col - 1, flag);
        int down = process(grid, row + 1, col, flag);
        int up = process(grid, row - 1, col, flag);
        int right = process(grid, row, col + 1, flag);
        int min = Math.min(Math.min(up, down), Math.min(left, right));
        flag[row][col] = false;
        return min == Integer.MAX_VALUE ? min : min + 1;
    }

    public static void main(String[] args) {
        System.out.println(orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
    }
}
