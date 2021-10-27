package com.leetcode.dp;

import com.leetcode.dp.linear.MaximumNumberOfPointsWithCost;

/**
 * <a href="https://leetcode.com/problems/best-sightseeing-pair/">Best Sightseeing Pair</a>
 *
 * @author zms
 * @date 10:34 上午 2021/10/27
 */
public class BestSightseeingPair {
    /**
     * You are given an integer array values where values[i] represents the value of the ith sightseeing spot.
     * Two sightseeing spots i and j have a distance j - i between them.
     * <p>
     * The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j:
     * the sum of the values of the sightseeing spots, minus the distance between them.
     * Return the maximum score of a pair of sightseeing spots.
     * <p>
     * Example 1:
     * Input: values = [8,1,5,2,6]
     * Output: 11
     * Explanation: i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
     * <p>
     * Example 2:
     * Input: values = [1,2]
     * Output: 2
     * <p>
     * Constraints:
     * 2 <= values.length <= 5 * 10^4
     * 1 <= values[i] <= 1000
     */
    public int maxScoreSightseeingPair01(int[] values) {
        int n = values.length;
        int ans = Integer.MIN_VALUE;
        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                ans = Math.max(values[i] + values[j] - len, ans);
            }
        }
        return ans;
    }


    /**
     * 斜率优化
     *
     * @see MaximumNumberOfPointsWithCost
     */
    public int maxScoreSightseeingPair02(int[] nums) {
        int n = nums.length;
        int ans = Integer.MIN_VALUE;
        int max = nums[0] - 1;
        for (int i = 1; i < n; i++) {
            ans = Math.max(nums[i] + max, ans);
            max = Math.max(nums[i], max) - 1;
        }
        return ans;
    }
}
