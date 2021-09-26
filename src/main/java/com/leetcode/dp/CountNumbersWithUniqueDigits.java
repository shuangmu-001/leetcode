package com.leetcode.dp;

/**
 * @author zms
 * @date 3:19 下午 2020/9/11
 * <a href="https://leetcode.com/problems/count-numbers-with-unique-digits/">
 *     Count Numbers with Unique Digits</a>
 */
public class CountNumbersWithUniqueDigits {
    /**
     * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10^n.
     *
     * Example:
     * Input: 2
     * Output: 91
     * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
     * excluding 11,22,33,44,55,66,77,88,99
     *
     * Constraints:0 <= n <= 8
     */
    public static int countNumbersWithUniqueDigits(int n) {
        int res = 1;
        // i位数 没有重复数字的个数
        int[] nums = new int[n + 1];
        nums[0] = 1;
        for (int i = 1; i <= n; i++) {
            if(i == 1) {
                nums[i] = 9;
            } else {
                nums[i] = nums[i - 1] * (10 - i + 1);
            }
            res += nums[i];
        }

        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            System.out.println(countNumbersWithUniqueDigits(i));
        }
    }
}
