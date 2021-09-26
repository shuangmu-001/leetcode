package com.leetcode.search;

import java.util.Arrays;

/**
 * @author zms
 * @date 4:07 PM 2020/4/7
 * <a href="https://leetcode.com/problems/search-a-2d-matrix-ii/">
 *     Search a 2D Matrix II</a>
 */
public class SearchA2DMatrixII {
    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     *
     * Integers in each row are sorted in ascending from left to right.
     * Integers in each column are sorted in ascending from top to bottom.
     * Example:
     *
     * Consider the following matrix:
     *
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * Given target = 5, return true.
     *
     * Given target = 20, return false.
     */
    /**
     * Runtime: 18 ms, faster than 5.85% of Java online submissions for Search a 2D Matrix II.
     * Memory Usage: 51.5 MB, less than 5.66% of Java online submissions for Search a 2D Matrix II.
     */
    public boolean searchMatrix1(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) {
            return false;
        }
        for (int[] ints : matrix) {
            int index = Arrays.binarySearch(ints, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Runtime: 10 ms, faster than 15.71% of Java online submissions for Search a 2D Matrix II.
     * Memory Usage: 51.6 MB, less than 5.66% of Java online submissions for Search a 2D Matrix II.
     */
    public static boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) {
            return false;
        }
        int start = 0;
        int end = matrix[0].length;
        for (int i = matrix.length - 1; i >= 0 ; i--) {
            int index = Arrays.binarySearch(matrix[i], start, end, target);
            if(index >= 0) {
                return true;
            }
            if(end <= ~index) {
                return false;
            }
            start = ~index;
        }
        return false;
    }
    // other
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1, col = 0;
        while(row >= 0 && col < matrix[0].length){
            if(matrix[row][col] == target) {
                return true;
            } else if(matrix[row][col] > target) {
                row --;
            } else {
                col++;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{
                {1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 13));
    }
}
