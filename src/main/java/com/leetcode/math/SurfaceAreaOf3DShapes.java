package com.leetcode.math;

/**
 * @author wcl
 * @date 10:53 AM 2020/3/25
 * <a href="https://leetcode.com/problems/surface-area-of-3d-shapes/">
 *     Surface Area of 3D Shapes</a>
 */
public class SurfaceAreaOf3DShapes {
    /**
     * On a N * N grid, we place some 1 * 1 * 1 cubes.
     * Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
     * Return the total surface area of the resulting shapes.
     *
     * Example 1:
     *      Input: [[2]]
     *      Output: 10
     *
     * Example 2:
     *      Input: [[1,2],[3,4]]
     *      Output: 34
     *
     * Example 3:
     *      Input: [[1,0],[0,2]]
     *      Output: 16
     *
     * Example 4:
     *      Input: [[1,1,1],[1,0,1],[1,1,1]]
     *      Output: 32
     *
     * Example 5:
     *      Input: [[2,2,2],[2,1,2],[2,2,2]]
     *      Output: 46
     *
     * Note:
     *      1 <= N <= 50
     *      0 <= grid[i][j] <= 50
     */
    public int surfaceArea(int[][] grid) {
        int surfaceArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int cur = grid[i][j] != 0 ? ((grid[i][j]) << 2) + 2 : 0;
                if(i != 0) {
                    cur -= (Math.min(grid[i - 1][j], grid[i][j]) << 1);
                }
                if(j != 0) {
                    cur -= (Math.min(grid[i][j - 1], grid[i][j]) << 1);
                }
                surfaceArea += cur;
            }
        }
        return surfaceArea;
    }
}
