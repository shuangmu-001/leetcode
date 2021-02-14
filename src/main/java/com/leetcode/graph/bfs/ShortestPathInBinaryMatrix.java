package com.leetcode.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zms
 * @date 10:35 下午 2021/2/13
 * TODO 广度优先和深度优先的区别是什么？（在二维是数组甚至多维数组中）
 * <a href="https://leetcode.com/problems/shortest-path-in-binary-matrix/">
 * Shortest Path in Binary Matrix</a>
 */
public class ShortestPathInBinaryMatrix {
    /**
     * In an N by N square grid, each cell is either empty (0) or blocked (1).
     * <p>
     * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
     * <p>
     * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
     * C_1 is at location (0, 0) (ie. has value grid[0][0])
     * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
     * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
     * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: [[0,1],[1,0]]
     * <p>
     * <p>
     * Output: 2
     * <p>
     * Example 2:
     * <p>
     * Input: [[0,0,0],[1,1,0],[1,1,0]]
     * <p>
     * <p>
     * Output: 4
     * <p>
     * <p>
     * <p>
     * Note:
     * <p>
     * 1 <= grid.length == grid[0].length <= 100
     * grid[r][c] is 0 or 1
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }

        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        // 8个方向
        int[] dx = new int[]{1, 1, 0, -1, -1, -1, 0, 1};
        int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};

        q.add(new int[]{0, 0});
        grid[0][0] = 1;
        int level = 1;

        while (q.size() > 0) {
            for (int i = q.size(); i > 0; i--) {
                int[] xy = q.poll();
                if (xy[0] == m - 1 && xy[1] == n - 1) {
                    return level;
                }
                for (int j = 0; j < 8; j++) {
                    int nx = xy[0] + dx[j];
                    int ny = xy[1] + dy[j];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 0) {
                        grid[nx][ny] = 1;
                        q.add(new int[]{nx, ny});
                    }
                }
            }

            level++;
        }

        return -1;
    }
}
