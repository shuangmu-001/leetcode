package com.leetcode.bit;

/**
 * @author wcl
 * @date 5:03 PM 2020/4/23
 * <a href="https://leetcode.com/problems/missing-number/">
 *     Missing Number</a>
 */
public class MissingNumber {
    /**
     * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
     * find the one that is missing from the array.
     *
     * Example 1:
     *
     * Input: [3,0,1]
     * Output: 2
     * Example 2:
     *
     * Input: [9,6,4,2,3,5,7,0,1]
     * Output: 8
     * Note:
     * Your algorithm should run in linear runtime complexity.
     * Could you implement it using only constant extra space complexity?
     */
    public int missingNumber1(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int total = nums.length * (nums.length + 1) / 2;
        return total - sum;
    }

    /**
     * [0,1,3,4]
     * missing
     * =4∧(0∧0)∧(1∧1)∧(2∧3)∧(3∧4)
     * =(4∧4)∧(0∧0)∧(1∧1)∧(3∧3)∧2
     * =0∧0∧0∧0∧2
     * =2
     * ​
     */
    public int missingNumber(int[] nums) {
        int num = nums.length;
        for (int i = 0; i < nums.length; i++) {
            num ^= i ^ nums[i];
        }
        return num;
    }

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        System.out.println(sum);

    }
}
