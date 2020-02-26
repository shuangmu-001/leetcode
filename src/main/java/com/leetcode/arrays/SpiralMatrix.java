package com.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wcl
 * @date 3:18 PM 2020/2/25
 * {@link "https://leetcode.com/problems/spiral-matrix/"}
 */
public class SpiralMatrix {

    /**
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
     * Example 1:
     * Input:
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * Output: [1,2,3,6,9,8,7,4,5]
     * <p>
     * Example 2:
     * Input:
     * [
     * [1, 2, 3, 4],
     * [5, 6, 7, 8],
     * [9,10,11,12]
     * ]
     * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int size = rows * columns;
        List<Integer> result = new ArrayList<>(size);
        boolean rowFlag = columns > 1;
        boolean rowPathFlag = true;
        boolean columnFlag = columns == 1;
        boolean columnPathFlag = true;

        int rowMax = rows - 1;
        int rowMin = 0;
        int columnMax = columns - 1;
        int columnMin = 0;
        int m = 0;
        int n = 0;
        for (int i = 0; i < size; i++) {
            if (rowFlag) {
                result.add(matrix[m][n]);
                if (rowPathFlag) {
                    n++;
                } else {
                    n--;
                }
            }
            if (columnFlag) {
                result.add(matrix[m][n]);
                if (columnPathFlag) {
                    m++;
                } else {
                    m--;
                }

            }
            if (n == columnMax && rowFlag && rowPathFlag) {
                rowFlag = false;
                columnFlag = true;
                rowPathFlag = false;
                rowMin++;
            }

            if (n == columnMin && rowFlag && !rowPathFlag) {
                rowFlag = false;
                columnFlag = true;
                rowPathFlag = true;
                rowMax--;
            }

            if (m == rowMin && columnFlag && !columnPathFlag) {
                rowFlag = true;
                columnFlag = false;
                columnPathFlag = true;
                columnMin++;
            }

            if (m == rowMax && columnFlag && columnPathFlag) {
                rowFlag = true;
                columnFlag = false;
                columnPathFlag = false;
                columnMax--;
            }


        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(spiralOrder(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }));
        System.out.println(spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9,10,11,12}
        }));

        System.out.println(spiralOrder(new int[][]{
                {3},
                {2}
        }));
        System.out.println(spiralOrder(new int[][]{
                {3}
        }));
    }
}
