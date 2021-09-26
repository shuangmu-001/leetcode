package com.leetcode.dp;

/**
 * @author zms
 * @date 4:25 PM 2020/3/30
 * <a href="https://leetcode.com/problems/maximum-length-of-repeated-subarray/">
 *     Maximum Length of Repeated Subarray</a>
 */
public class MaximumLengthOfRepeatedSubarray {
    /**
     * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
     *
     * Example 1:
     *      Input: A: [1,2,3,2,1] B: [3,2,1,4,7]
     *      Output: 3
     *      Explanation: The repeated subarray with maximum length is [3, 2, 1].
     *
     * Note:
     *      1 <= len(A), len(B) <= 1000
     *      0 <= A[i], B[i] < 100
     */
    public int findLength(int[] A, int[] B) {
        int[][] dp = new int[A.length][B.length];
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if(A[i] == B[j]) {
                    if(i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}
