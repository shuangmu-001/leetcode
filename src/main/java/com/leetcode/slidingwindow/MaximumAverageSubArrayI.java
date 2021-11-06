package com.leetcode.slidingwindow;

/**
 * <a href="https://leetcode.com/problems/maximum-average-subarray-i/">Maximum Average Subarray I</a>
 *
 * @author zms
 * @date 6:33 下午 2021/11/4
 */
public class MaximumAverageSubArrayI {
    /**
     * You are given an integer array nums consisting of n elements, and an integer k.
     * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
     * Any answer with a calculation error less than 10-5 will be accepted.
     * <p>
     * Example 1:
     * Input: nums = [1,12,-5,-6,50,3], k = 4
     * Output: 12.75000
     * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
     * <p>
     * Example 2:
     * Input: nums = [5], k = 1
     * Output: 5.00000
     * <p>
     * Constraints:
     * n == nums.length
     * 1 <= k <= n <= 10^5
     * -10^4 <= nums[i] <= 10^4
     */
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        int r = 0;
        while (r < k) {
            sum += nums[r++];
        }
        int res = sum;
        while (r < n) {
            sum += -nums[r - k] + nums[r++];
            res = Math.max(res, sum);
        }
        return res / 1.0 / k;
    }
}
