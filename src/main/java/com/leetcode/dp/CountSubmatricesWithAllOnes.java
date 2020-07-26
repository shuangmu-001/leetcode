package com.leetcode.dp;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 11:12 上午 2020/7/15
 * <a href="https://leetcode.com/problems/count-submatrices-with-all-ones/">
 *     Count Submatrices With All Ones</a>
 * @see MaximalRectangle
 */
public class CountSubmatricesWithAllOnes {

    /**
     * Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.
     *
     *
     *
     * Example 1:
     *
     * Input: mat = [[1,0,1],
     *               [1,1,0],
     *               [1,1,0]]
     * Output: 13
     * Explanation:
     * There are 6 rectangles of side 1x1.
     * There are 2 rectangles of side 1x2.
     * There are 3 rectangles of side 2x1.
     * There is 1 rectangle of side 2x2.
     * There is 1 rectangle of side 3x1.
     * Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
     * Example 2:
     *
     * Input: mat = [[0,1,1,0],
     *               [0,1,1,1],
     *               [1,1,1,0]]
     * Output: 24
     * Explanation:
     * There are 8 rectangles of side 1x1.
     * There are 5 rectangles of side 1x2.
     * There are 2 rectangles of side 1x3.
     * There are 4 rectangles of side 2x1.
     * There are 2 rectangles of side 2x2.
     * There are 2 rectangles of side 3x1.
     * There is 1 rectangle of side 3x2.
     * Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
     * Example 3:
     *
     * Input: mat = [[1,1,1,1,1,1]]
     * Output: 21
     * Example 4:
     *
     * Input: mat = [[1,0,1],[0,1,0],[1,0,1]]
     * Output: 5
     *
     *
     * Constraints:
     *
     * 1 <= rows <= 150
     * 1 <= columns <= 150
     * 0 <= mat[i][j] <= 1
     */
    public static int numSubmat(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] width = new int[n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 && j == 0) {
                    width[j] = mat[i][j];
                    result += width[j];
                } else {
                    width[j] = mat[i][j] == 0 ? mat[i][j] : width[j] + mat[i][j];
                    int min = width[j];
                    int index = j;
                    while(index >=0 && width[index] != 0) {
                        min = Math.min(min, width[index--]);
                        result += min;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(numSubmat(new int[][]{
                {1,0,1},
                {1,1,0},
                {1,1,0}
        }) == 13);

        System.out.println(numSubmat(new int[][]{
                {1,1,1,1,1,1}
        }) == 21);

        System.out.println(numSubmat(new int[][]{
                {0,1,1,0},
                {0,1,1,1},
                {1,1,1,0}
        }) == 24);

        System.out.println(numSubmat(new int[][]{
                {1,0,1},
                {0,1,0},
                {1,0,1}
        }) == 5);
    }

}
