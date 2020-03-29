package com.leetcode.arrays;

/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: [4,1,2,1,2]
 * Output: 4
 */
public class SingleNumber {
    /**
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int s = 0;
        for (int n : nums) {
            s ^= n;
        }
        s ^= 0;
        return s;
    }

    public static void main(String[] args) {
        SingleNumber singleNumber = new SingleNumber();
        int[] nums = {4,1,2,1,2};
        System.out.print(singleNumber.singleNumber(nums));
        System.out.println(4 ^ 5 ^ 4);
    }
}
