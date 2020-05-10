package com.leetcode.bit;

/**
 * @author wcl
 * @date 11:16 AM 2020/4/24
 * TODO <a href="https://leetcode.com/problems/single-number-iii/">
 *     Single Number III</a>
 * @see SingleNumber
 * @see SingleNumberII
 */
public class SingleNumberIII {
    /**
     * Given an array of numbers nums,
     * in which exactly two elements appear only once and all the other elements appear exactly twice.
     * Find the two elements that appear only once.
     *
     * Example:
     * Input:  [1,2,1,3,2,5]
     * Output: [3,5]
     *
     * Note:
     * The order of the result is not important. So in the above example, [5, 3] is also correct.
     * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
     */
    public int[] singleNumber(int[] nums) {
        if(nums.length == 2) {
            return nums;
        }
        int[] res = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {

        }
        return res;
    }
}
