package com.leetcode.arrays;

/**
 * <a href="https://leetcode.com/problems/reshape-the-matrix/">Reshape the Matrix</a>
 *
 * @author zms
 * @date 3:56 下午 2021/11/15
 */
public class ReshapeTheMatrix {
    /**
     * In MATLAB, there is a handy function called reshape which can reshape an m x n matrix into a new one with a different size r x c keeping its original data.
     * You are given an m x n matrix mat and two integers r and c representing the number of rows and the number of columns of the wanted reshaped matrix.
     * The reshaped matrix should be filled with all the elements of the original matrix in the same row-traversing order as they were.
     * If the reshape operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.
     * <p>
     * Example 1:
     * Input: mat = [[1,2],[3,4]], r = 1, c = 4
     * Output: [[1,2,3,4]]
     * <p>
     * Example 2:
     * Input: mat = [[1,2],[3,4]], r = 2, c = 4
     * Output: [[1,2],[3,4]]
     * <p>
     * Constraints:
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 100
     * -1000 <= mat[i][j] <= 1000
     * 1 <= r, c <= 300
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }
        int[][] res = new int[r][c];
        int l = 0, k = 0;
        for (int[] ints : mat) {
            for (int j = 0; j < n; j++) {
                res[l][k++] = ints[j];
                if (k >= c) {
                    l++;
                    k = 0;
                }
            }
        }
        return res;
    }
}
