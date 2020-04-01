package com.leetcode.dp;

/**
 * @author wcl
 * @date 6:29 PM 2020/3/30
 * <a href="https://leetcode.com/problems/minimum-falling-path-sum-ii/">
 *     Minimum Falling Path Sum II</a>
 */
public class MinimumFallingPathSumII {
    /**
     * Given a square grid of integers arr,
     * a falling path with non-zero shifts is a choice of exactly one element from each row of arr,
     * such that no two elements chosen in adjacent rows are in the same column.
     * Return the minimum sum of a falling path with non-zero shifts.
     *
     * Example 1:
     *      Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
     *      Output: 13
     *      Explanation:
     *          The possible falling paths are:
     *          [1,5,9], [1,5,7], [1,6,7], [1,6,8],
     *          [2,4,8], [2,4,9], [2,6,7], [2,6,8],
     *          [3,4,8], [3,4,9], [3,5,7], [3,5,9]
     *          The falling path with the smallest sum is [1,5,7], so the answer is 13.
     *
     * Constraints:
     *      1 <= arr.length == arr[i].length <= 200
     *      -99 <= arr[i][j] <= 99
     */
    public static int minFallingPathSum(int[][] arr) {
        if(arr.length == 1) {
            return arr[0][0];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int cur = Integer.MAX_VALUE;
                for (int k = 0; k < arr[i].length; k++) {
                    if(j != k) {
                        cur = Math.min(arr[i - 1][k], cur);
                    }
                }
                arr[i][j] = cur + arr[i][j];
                if(i == arr.length - 1) {
                    min = Math.min(min, arr[i][j]);
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(minFallingPathSum(new int[][]{
                {8,93,21},
                {18,-11,19},
                {-23,15,-42}
        }));
        System.out.println(minFallingPathSum(new int[][]{
                {8,93,21},
                {18,19,-11},
                {-23,15,-42}
        }));
        System.out.println(minFallingPathSum(new int[][]{
                {-51,-35,74},
                {-62,14,-53},
                {94,61,-10}
        }));
    }
}
