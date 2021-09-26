package com.leetcode.arrays;

import java.util.Arrays;

/**
 * @author zms
 * @date 10:15 下午 2020/9/30
 * TODO <a href="https://leetcode.com/problems/first-missing-positive/">First Missing Positive</a>
 */
public class FirstMissingPositive {
    /**
     * Given an unsorted integer array, find the smallest missing positive integer.
     *
     * Example 1:
     *
     * Input: [1,2,0]
     * Output: 3
     * Example 2:
     *
     * Input: [3,4,-1,1]
     * Output: 2
     * Example 3:
     *
     * Input: [7,8,9,11,12]
     * Output: 1
     * Follow up:
     *
     * Your algorithm should run in O(n) time and uses constant extra space.
     */
    public static int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 1;
        }
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if(nums[i] > 0 && nums[i] <= length) {
                
                
                
                nums[nums[i] - 1] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < length; i++) {
            if(nums[i] != i+1) {
                return i + 1;
            }
        }
        return length + 1;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{3,4,-1,1}));
    }
}
