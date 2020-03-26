package com.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 10:01 PM 2020/3/25
 * <a href="https://leetcode.com/problems/maximum-product-subarray/">
 *      Maximum Product Subarray</a>
 */
public class MaximumProductSubarray {
    /**
     * Given an integer array nums, find the contiguous subarray within an array
     * (containing at least one number) which has the largest product.
     *
     * Example 1:
     *      Input: [2,3,-2,4]
     *      Output: 6
     *      Explanation: [2,3] has the largest product 6.
     *
     * Example 2:
     *      Input: [-2,0,-1]
     *      Output: 0
     *      Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
     *
     */
    public static int maxProduct1(int[] nums) {
        if(nums == null || nums.length == 0) {
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
    // TODO
    public static int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        int all = 1;
        List<Integer> odds = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(nums[i], max);
            all *= nums[i];
            max = Math.max(all, max);
            if(nums[i] < 0) {
                odds.add(nums[i]);
            } else if(nums[i] == 0) {
                odds = new ArrayList<>();
                all = 1;
            }

            for (int j = 0; j < odds.size() - 1; j++) {
                int h = odds.get(j) * nums[i];
                odds.set(j, h);
                max = Math.max(max, h);
            }
            System.out.println(odds);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{-2,0,-1}));
        System.out.println(maxProduct(new int[]{-2,0,-1,-7}));
        System.out.println(maxProduct(new int[]{2,-5,-2,-4,3}));
        System.out.println(maxProduct(new int[]{2, 0, 7}));
        System.out.println(maxProduct(new int[]{1,0,-1,2,3,-5,-2}));
    }
}
