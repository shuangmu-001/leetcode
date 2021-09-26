package com.leetcode.arrays;

/**
 * @author zms
 * @date 6:32 PM 2020/5/7
 * <a href="https://leetcode.com/problems/max-consecutive-ones/">
 *     Max Consecutive Ones</a>
 */
public class MaxConsecutiveOnes {
    /**
     * Given a binary array, find the maximum number of consecutive 1s in this array.
     *
     * Example 1:
     * Input: [1,1,0,1,1,1]
     * Output: 3
     * Explanation: The first two digits or the last three digits are consecutive 1s.
     *     The maximum number of consecutive 1s is 3.
     * Note:
     *
     * The input array will only contain 0 and 1.
     * The length of input array is a positive integer and will not exceed 10,000
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int index = 0;
        int length = nums.length;
        while(index < length && nums[index] == 0) {
            index++;
        }
        if(index >= length) {
            return 0;
        }
        int res = 1;
        int ones = 0;
        for(int i = index; i < length; i++) {
            if(nums[i] == 1) {
                ones++;
                if(i == length - 1) {
                    res = Math.max(ones, res);
                }
            } else if(nums[i] == 0) {
                res = Math.max(ones, res);
                ones = 0;
            }
        }
        return res;
    }
}
