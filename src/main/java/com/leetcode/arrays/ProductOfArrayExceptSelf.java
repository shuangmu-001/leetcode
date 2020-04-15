package com.leetcode.arrays;

import java.util.Arrays;

/**
 * @author wcl
 * @date 3:16 PM 2020/4/15
 * <a href="https://leetcode.com/problems/product-of-array-except-self/">
 *     Product of Array Except Self</a>
 */
public class ProductOfArrayExceptSelf {
    /**
     * Given an array nums of n integers where n > 1,
     * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
     *
     * Example:
     *
     * Input:  [1,2,3,4]
     * Output: [24,12,8,6]
     * Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
     *
     * Note: Please solve it without division and in O(n).
     *
     * Follow up:
     * Could you solve it with constant space complexity?
     * (The output array does not count as extra space for the purpose of space complexity analysis.)
     */
    int[] res ;
    public int[] productExceptSelf1(int[] nums) {
        res = new int[nums.length];
        productExceptSelfHelper(nums, 1, -1, 0);
        return res;
    }

    public void productExceptSelfHelper(int[] nums, int product, int index, int cur) {
        if(cur >= nums.length) {
            if(index != -1) {
                res[index] = product;
            }
            return;
        }
        if(index != -1) {
            productExceptSelfHelper(nums, product * nums[cur], index, cur + 1);
        } else {
            productExceptSelfHelper(nums, product, cur, cur + 1);
            productExceptSelfHelper(nums, product * nums[cur], -1, cur + 1);
        }

    }
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = 1;
        right[nums.length - 1] = 1;
        int lIndex = 1;
        int rIndex = nums.length - 2;
        while(lIndex < nums.length && rIndex >= 0) {
            left[lIndex] = nums[lIndex - 1] * left[lIndex - 1];
            lIndex++;
            right[rIndex] = right[rIndex + 1] * nums[rIndex + 1];
            rIndex--;
        }
        for (int i = 0; i < nums.length; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ProductOfArrayExceptSelf().productExceptSelf(new int[]{1, 2, 3, 4})));
    }
}
