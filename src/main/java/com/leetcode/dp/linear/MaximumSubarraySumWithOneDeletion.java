package com.leetcode.dp.linear;

import com.leetcode.Utils;

/**
 * @author zms
 * @date 4:55 下午 2021/4/25
 * <a href="https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/">
 * Maximum Subarray Sum with One Deletion</a>
 */
public class MaximumSubarraySumWithOneDeletion {

    /**
     * Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion.
     * In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.
     * <p>
     * Note that the subarray needs to be non-empty after deleting one element.
     * <p>
     * <p>
     * Example 1:
     * Input: arr = [1,-2,0,3]
     * Output: 4
     * Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
     * <p>
     * Example 2:
     * Input: arr = [1,-2,-2,3]
     * Output: 3
     * Explanation: We just choose [3] and it's the maximum sum.
     * <p>
     * Example 3:
     * Input: arr = [-1,-1,-1,-1]
     * Output: -1
     * Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it,
     * then get an empty subarray to make the sum equals to 0.
     * <p>
     * <p>
     * Constraints:
     * 1 <= arr.length <= 10^5
     * -10^4 <= arr[i] <= 10^4
     */
    public static int maximumSum(int[] arr) {
        int res = Integer.MIN_VALUE;
        int n = arr.length;
        if (n == 1) {
            return arr[0];
        }
        int[] before = new int[n + 1];
        int[] after = new int[n + 1];
        for (int i = 0; i < n; i++) {
            before[i + 1] = Math.max(before[i] + arr[i], arr[i]);
            after[n - i - 1] = Math.max(after[n - i] + arr[n - i - 1], arr[n - i - 1]);
            res = Math.max(before[i + 1], res);
            res = Math.max(after[n - i - 1], res);
        }

        for (int i = 1; i < n - 1; i++) {
            res = Math.max(before[i] + after[i + 1], res);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(maximumSum(new int[]{1, -2, 0, 3}) == 4);
        System.out.println(maximumSum(new int[]{1, -2, -2, 3}) == 3);
        System.out.println(maximumSum(new int[]{-1, -1, -1, -1}) == -1);
        System.out.println(maximumSum(new int[]{-10, 10}) == 10);
    }

}
