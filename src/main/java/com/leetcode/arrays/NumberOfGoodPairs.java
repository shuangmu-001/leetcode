package com.leetcode.arrays;

/**
 * @author zms
 * @date 10:58 上午 2020/7/14
 * <a href="https://leetcode.com/problems/number-of-good-pairs/">
 * Number of Good Pairs</a>
 */
public class NumberOfGoodPairs {
    /**
     * Given an array of integers nums.
     * <p>
     * A pair (i,j) is called good if nums[i] == nums[j] and i < j.
     * <p>
     * Return the number of good pairs.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,2,3,1,1,3]
     * Output: 4
     * Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
     * Example 2:
     * <p>
     * Input: nums = [1,1,1,1]
     * Output: 6
     * Explanation: Each pair in the array are good.
     * Example 3:
     * <p>
     * Input: nums = [1,2,3]
     * Output: 0
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     */
    public int numIdenticalPairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int result = 0;
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[j] == nums[i]) {
                    result++;
                }
            }
        }
        return result;
    }

}
