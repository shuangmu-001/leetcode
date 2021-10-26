package com.leetcode.dp.linear;


/**
 * <a href="https://leetcode.com/problems/maximum-product-subarray/">Maximum Product SubArray</a>
 *
 * @author zms
 * @date 10:01 PM 2020/3/25
 */
public class MaximumProductSubArray {
    /**
     * Given an integer array nums, find the contiguous subarray within an array
     * (containing at least one number) which has the largest product.
     * <p>
     * Example 1:
     * Input: [2,3,-2,4]
     * Output: 6
     * Explanation: [2,3] has the largest product 6.
     * <p>
     * Example 2:
     * Input: [-2,0,-1]
     * Output: 0
     * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
     */
    public static int maxProduct1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = nums[i];
            max = Math.max(dp[i], max);
            for (int j = i + 1; j < nums.length; j++) {
                dp[i] = dp[i] * nums[j];
                max = Math.max(dp[i], max);
            }
        }
        return max;
    }

    // dp[i][0] 表示包含nums[i]的最大值 dp[i][1] 表示包含nums[i]负数的最小值
    public static int maxProduct2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        int[][] dp = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                if (i == 0) {
                    dp[i][1] = nums[0];
                } else {
                    dp[i][0] = dp[i - 1][1] * nums[i];
                    dp[i][1] = Math.min(dp[i - 1][0] * nums[i], nums[i]);
                    max = Math.max(max, dp[i][0]);
                }

            } else {
                if (i == 0) {
                    dp[i][0] = nums[0];
                } else {
                    dp[i][0] = Math.max(dp[i - 1][0] * nums[i], nums[i]);
                    dp[i][1] = dp[i - 1][1] * nums[i];
                    max = Math.max(max, dp[i][0]);
                }
            }
        }
        return max;
    }

    // n只与n-1有关 则 可以用 O(1) space
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        int even = Math.max(nums[0], 0);
        int odd = Math.min(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                even = Math.max(nums[i] * even, nums[i]);
                odd *= nums[i];
                max = Math.max(max, even);
            } else {
                int temp = odd;
                odd = Math.min(nums[i] * even, nums[i]);
                even = nums[i] * temp;
                max = Math.max(max, even);
            }
        }
        return max;
    }

    public static int dp(int[] nums) {
        // {-2}
        if(nums.length == 1) {
            return nums[0];
        }
        int ans = Integer.MIN_VALUE;
        int pos = 0;
        int neg = 0;
        for (int num : nums) {
            if (num == 0) {
                pos = 0;
                neg = 0;
            } else if(num > 0) {
                pos = pos == 0 ? num : pos * num;
                neg = neg == 0 ? 0 : neg * num;
            } else {
                int temp = pos == 0 ? num : pos * num;
                pos = neg == 0 ? 0 : neg * num;
                neg = temp;
            }
            ans = Math.max(ans, pos);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(dp(new int[]{-2, 0, -1}) == 0);
        System.out.println(dp(new int[]{-2, 0, -1, -7}) == 7);
        System.out.println(dp(new int[]{2, -5, -2, -4, 3}) == 24);
        System.out.println(dp(new int[]{2, 0, 7}) == 7);
        System.out.println(dp(new int[]{1, 0, -1, 2, 3, -5, -2}) == 60);
    }
}
