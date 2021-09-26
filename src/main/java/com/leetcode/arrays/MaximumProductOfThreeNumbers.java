package com.leetcode.arrays;

import java.util.Arrays;

/**
 * @author zms
 * @date 2:32 PM 2020/3/30
 * <a href="https://leetcode.com/problems/maximum-product-of-three-numbers/">
 *      Maximum Product of Three Numbers</a>
 */
public class MaximumProductOfThreeNumbers {
    /**
     * Given an integer array, find three numbers whose product is maximum and output the maximum product.
     *
     * Example 1:
     *      Input: [1,2,3]
     *      Output: 6
     *
     * Example 2:
     *      Input: [1,2,3,4]
     *      Output: 24
     *
     * Note:
     *      The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
     *      Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
     */
    public int maximumProduct1(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[len - 1], nums[len - 1] * nums[len - 2] * nums[len - 3]);
    }

    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            } else if(nums[i] > max2) {
                max3 = max2;
                max2 = nums[i];
            } else if(nums[i] > max3) {
                max3 = nums[i];
            }
            if(nums[i] < min1) {
                min2 = min1;
                min1 = nums[i];
            } else if(nums[i] < min2) {
                min2 = nums[i];
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }
}
