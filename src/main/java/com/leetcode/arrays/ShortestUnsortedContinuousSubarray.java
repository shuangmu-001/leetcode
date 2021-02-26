package com.leetcode.arrays;

/**
 * @author zms
 * @date 10:32 上午 2021/2/26
 * <a href="https://leetcode.com/problems/shortest-unsorted-continuous-subarray/">
 * Shortest Unsorted Continuous Subarray</a>
 */
public class ShortestUnsortedContinuousSubarray {
    /**
     * Given an integer array nums, you need to find one continuous subarray
     * that if you only sort this subarray in ascending order,
     * then the whole array will be sorted in ascending order.
     * Return the shortest such subarray and output its length.
     * <p>
     * Example 1:
     * Input: nums = [2,6,4,8,10,9,15]
     * Output: 5
     * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
     * <p>
     * Example 2:
     * Input: nums = [1,2,3,4]
     * Output: 0
     * <p>
     * Example 3:
     * Input: nums = [1]
     * Output: 0
     * <p>
     * Constraints:
     * 1 <= nums.length <= 104
     * -105 <= nums[i] <= 105
     * <p>
     * <p>
     * Follow up: Can you solve it in O(n) time complexity?
     */
    // 折线图
    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        // 找到不在位置的最小值
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                flag = true;
            }
            if (flag) {
                min = Math.min(min, nums[i]);
            }
        }
        int l;
        for (l = 0; l < nums.length; l++) {
            if (nums[l] > min) {
                break;
            }
        }
        // 找到不在位置的最大值
        int max = Integer.MIN_VALUE;
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                flag = true;
            }
            if (flag) {
                max = Math.max(max, nums[i]);
            }
        }
        int r;
        for (r = nums.length - 1; r >= 0; r--) {
            if (nums[r] < max) {
                break;
            }
        }
        return r < l ? 0 : r - l + 1;
    }

    public static void main(String[] args) {
        System.out.println(findUnsortedSubarray(new int[]{2,6,4,8,10,9,15}));
    }
}
