package com.leetcode.arrays;

/**
 * <a href="https://leetcode.com/problems/plus-one/">Plus One</a>
 *
 * @author zms
 * @date 5:10 PM 2020/5/7
 */
public class PlusOne {
    /**
     * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
     * <p>
     * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
     * <p>
     * You may assume the integer does not contain any leading zero, except the number 0 itself.
     * <p>
     * Example 1:
     * Input: [1,2,3]
     * Output: [1,2,4]
     * Explanation: The array represents the integer 123.
     * <p>
     * Example 2:
     * Input: [4,3,2,1]
     * Output: [4,3,2,2]
     * Explanation: The array represents the integer 4321.
     */
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        if (length == 0) {
            return new int[]{1};
        }
        int carry = 1;
        for (int i = length - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            if (sum >= 10) {
                digits[i] = 0;
            } else {
                digits[i] = sum;
                return digits;
            }
        }
        int[] res = new int[length + 1];
        res[0] = 1;
        return res;
    }
}
