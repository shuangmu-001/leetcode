package com.question.day03;

import com.Test;
import com.Utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <a href="https://leetcode.com/problems/largest-1-bordered-square/">Largest 1-Bordered Square</a>
 * TODO 正方形遍历
 *
 * @author wcl
 * @date 3:33 下午 2021/9/19
 */
public class Code03LargestBorderedSquare implements Test {

    // 给定一个只有0和1组成的二维数组，返回边框全是1的最大正方形
    public static int largest1BorderedSquare1(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        // (i,j)位置的上方有多少个1（包括自身）
        int[][] h = new int[n][m];
        // (i,j)位置的左边有多少个1（包括自身）
        int[][] w = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    h[i][j] = grid[i][j];
                } else {
                    h[i][j] = h[i - 1][j] + 1;
                }
                if (j == 0) {
                    w[i][j] = grid[i][j];
                } else if (grid[i][j] != 0) {
                    w[i][j] = w[i][j - 1] + 1;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                for (int k = 0; i + k < n && j + k < m; k++) {
                    if (w[i][j + k] == 0 || h[i + k][j] == 0) {
                        break;
                    }
                    if (grid[i + k][j + k] == 0) {
                        continue;
                    }
                    if (w[i + k][j + k] >= k && w[i][j + k] >= k
                            && h[i + k][j + k] >= k && h[i + k][j] >= k) {
                        res = Math.max(res, (k + 1) * (k + 1));
                    }
                }
            }
        }
        return res;
    }

    public int largest1BorderedSquare(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        // (i,j)位置的下方有多少个1连续（包括自身）
        int[][] down = new int[n][m];
        // (i,j)位置的右方有多少个1连续（包括自身）
        int[][] right = new int[n][m];
        setBorderMap(grid, down, right);
        for (int size = Math.min(n, m); size > 0; size--) {
            if (hasSizeOfBorder(size, down, right)) {
                return size * size;
            }
        }
        return 0;
    }

    public boolean hasSizeOfBorder(int size, int[][] down, int[][] right) {
        int n = down.length;
        int m = down[0].length;
        for (int i = 0; i < n - size + 1; i++) {
            for (int j = 0; j < m - size + 1; j++) {
                if (down[i][j] >= size && right[i][j] >= size
                        && down[i][j + size - 1] >= size && right[i + size - 1][j] >= size) {
                    return true;
                }
            }
        }
        return false;
    }

    // 统计每个点右边和下方连续1的个数
    public void setBorderMap(int[][] grid, int[][] down, int[][] right) {
        int n = grid.length;
        int m = grid[0].length;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                // 统计每个点下方连续1的个数
                if (i == n - 1) {
                    down[i][j] = grid[i][j];
                } else if (grid[i][j] != 0) {
                    down[i][j] = down[i + 1][j] + 1;
                }
                // 统计每个点右边连续1的个数
                if (j == m - 1) {
                    right[i][j] = grid[i][j];
                } else if (grid[i][j] != 0) {
                    right[i][j] = right[i][j + 1] + 1;
                }
            }
        }
    }

    @Override
    public int[][] genRandomTwoArr(int n) {
        int N = ThreadLocalRandom.current().nextInt(1, n);
        int M = ThreadLocalRandom.current().nextInt(1, n);
        int[][] ints = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ints[i][j] = ThreadLocalRandom.current().nextInt(0, 1);
            }
        }
        return ints;
    }

    @Override
    public void test(int n) {
        int[][] grid = genRandomTwoArr(n);
        // grid = new int[][]{{0, 0, 0, 1}};
        if (largest1BorderedSquare(grid) != largest1BorderedSquare1(grid)) {
            System.out.println("计算错误");
            Utils.printTwoArrays(grid);
        }
    }
}
