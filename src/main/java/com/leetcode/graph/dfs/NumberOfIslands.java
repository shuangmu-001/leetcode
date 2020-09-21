package com.leetcode.graph.dfs;

/**
 * @author wcl
 * @date 3:26 PM 2020/4/17
 * <a href="https://leetcode.com/problems/number-of-islands/">
 *      Number of Islands</a>
 * TODO 连通图的个数
 */
public class NumberOfIslands {
    /**
     * Given a 2d grid map of '1's (land) and '0's (water),
     * count the number of islands.
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.
     *
     * Example 1:
     *
     * Input:
     * 11110
     * 11010
     * 11000
     * 00000
     *
     * Output: 1
     * Example 2:
     *
     * Input:
     * 11000
     * 11000
     * 00100
     * 00011
     *
     * Output: 3
     */
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] id = new int[m * n];
        int result = 0;
        id[0] = grid[0][0] == '1' ? ++result : 0;
        for (int j = 1; j < m; j++) {
            if(grid[0][j] == '1') {
                id[j] = grid[0][j - 1] == '1' ? id[j - 1] : ++result;
            }
        }

        for (int i = 1; i < n; i++) {
            if(grid[i][0] == '1') {
                id[i * m] = grid[i - 1][0] == '1' ? id[(i - 1) * m] : ++result;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if(grid[i][j] == '1') {
                    int a = id[i * m + j - 1];
                    int b = id[(i - 1) * m + j];

                    if(grid[i][j - 1] == '0' && grid[i - 1][j] == '0') {
                        id[i * m + j] = ++result;
                    } else if(grid[i][j - 1] == '0') {
                        id[i * m + j] = a;
                    } else if(grid[i - 1][j] == '0') {
                        id[i * m + j] = b;
                    } else if(a == b) {
                        id[i * m + j] = a;
                    } else {
                        result--;
                        id[i * m + j] = Math.min(a, b);
                    }
                }
            }
        }
        return result;
    }

//    dirs为上下左右方向
//    dirs = [(-1, 0), (1, 0), (0, 1), (0, -1)]
}
