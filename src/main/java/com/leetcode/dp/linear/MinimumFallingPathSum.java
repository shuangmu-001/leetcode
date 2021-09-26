package com.leetcode.dp.linear;


/**
 * @author zms
 * @date 12:00 PM 2020/2/28
 * {@link "https://leetcode.com/problems/minimum-falling-path-sum/"}
 * @see Triangle
 */
public class MinimumFallingPathSum {

    /**
     * Given a square array of integers A, we want the minimum sum of a falling path through A.
     * A falling path starts at any element in the first row, and chooses one element from each row.
     * The next row's choice must be in a column that is different from the previous row's column by at most one.
     *
     * Example 1:
     *      Input: [[1,2,3],[4,5,6],[7,8,9]]
     *      Output: 12
     *      Explanation:
     *          The possible falling paths are:
     *          [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
     *          [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
     *          [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
     *          The falling path with the smallest sum is [1,4,7], so the answer is 12.
     *
     *
     *
     * Note:
     *
     * 1 <= A.length == A[0].length <= 100
     * -100 <= A[i][j] <= 100
     */
    // 用确定的位置来找不确定的位置 dp[j]代表不同位置的最小值 A[i][j] 可以找 j - 1， j， j + 1
    public static int minFallingPathSum(int[][] A) {
        if(A.length == 1) {
            return A[0][0];
        }
        int min = Integer.MAX_VALUE;
        int[] dp = new int[A[0].length];
        int cache = 0;
        System.arraycopy(A[0], 0, dp, 0, A[0].length);
        for (int i = 1; i < A.length ; i++) {
            for (int j = 0; j < A[i].length; j++) {

                int cur = dp[j];
                if(j + 1 < A.length) {
                    cur = Math.min(dp[j + 1], cur);
                }

                if(j - 1 >= 0) {
                    cur = Math.min(dp[j - 1], cur);
                    dp[j - 1] = cache;
                }

                cache = A[i][j] + cur;
                if(j == A[i].length - 1) {
                    dp[j] = cache;
                }

                if(i == A.length - 1) {
                    min = Math.min(cache, min);
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
        }) == -45);
        System.out.println(minFallingPathSum(new int[][]{
                {8,93,21},
                {18,19,-11},
                {-23,15,-42}
        }) == -32);
        System.out.println(minFallingPathSum(new int[][]{
                {-51,-35,74},
                {-62,14,-53},
                {94,61,-10}
        }) == -98);
    }
}
