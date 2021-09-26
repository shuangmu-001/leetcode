package com.leetcode.arrays;

import com.Utils;

/**
 * @author zms
 * @date 2:48 PM 2020/4/30
 * <a href="https://leetcode.com/problems/diagonal-traverse/">
 *     Diagonal Traverse</a>
 */
public class DiagonalTraverse {
    /**
     * Given a matrix of M x N elements (M rows, N columns),
     * return all elements of the matrix in diagonal order as shown in the below image.
     *
     * Example:
     * Input:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     *
     * Output:  [1,2,4,7,5,3,6,8,9]
     *
     *
     * Note:
     * The total number of elements of the given matrix will not exceed 10,000.
     */
    public static int[] findDiagonalOrder1(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int len = m * n;
        int[] res = new int[len];
        if(n == 1) {
            for (int i = 0; i < m; i++) {
                res[i] = matrix[i][0];
            }
            return res;
        } else if(m == 1) {
            return matrix[0];
        }

        int index = 0;
        int i = 0, j = 0;
        boolean flag = true;
        while(i != m - 1 || j != n - 1) {

            res[index++] = matrix[i][j];
            if(i == 0 && j == 0) {
                j++;
                flag = false;
            } else if((j == 0 && !flag) || (j == n - 1 && flag) ) {
                if(i == m - 1) {
                    j++;
                } else {
                    i++;
                }

                flag = !flag;
            } else if((i == 0 && flag) || (i == m - 1 && !flag)) {
                if(j == n - 1) {
                    i++;
                } else {
                    j++;
                }

                flag = !flag;
            } else {
                if(flag) {
                    i--;
                    j++;
                } else {
                    i++;
                    j--;
                }
            }


        }
        res[len - 1] = matrix[m - 1][n - 1];
        return res;
    }

    public static int[] findDiagonalOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int len = m * n;
        int[] res = new int[len];

        int index = 0;
        int column = 0, row = 0;
        boolean flag = true;
        while(row < m && column < n) {
            res[index++] = matrix[row][column];
            int new_row = row + (flag ? -1 : 1);
            int new_column = column + (flag ? 1 : -1);
            if(new_row < 0 || new_row >= m || new_column < 0 || new_column >= n) {
                if(flag) {
                    row += (column == n - 1 ? 1 : 0);
                    column += (column >= n - 1 ? 0 : 1);
                } else {
                    column += (row == m - 1 ? 1 : 0);
                    row += (row >= m - 1 ? 0 : 1);
                }
                flag = !flag;
            } else {
                row = new_row;
                column = new_column;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Utils.printArrays(findDiagonalOrder(new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9},
                {10,11,12},
        }));
        Utils.printArrays(findDiagonalOrder(new int[][]{
                {1,2,3,10},
                {4,5,6,11},
                {7,8,9,12},
        }));
    }
}
