package com.leetcode.bit;

/**
 * @author wcl
 * @date 4:54 PM 2020/4/23
 * <a href="https://leetcode.com/problems/number-complement/">
 *     Number Complement</a>
 */
public class NumberComplement {
    /**
     * Given a positive integer, output its complement number.
     * The complement strategy is to flip the bits of its binary representation.
     *
     * Example 1:
     *
     * Input: 5
     * Output: 2
     * Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
     *
     *
     * Example 2:
     *
     * Input: 1
     * Output: 0
     * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
     *
     *
     * Note:
     *
     * The given integer is guaranteed to fit within the range of a 32-bit signed integer.
     * You could assume no leading zero bit in the integer’s binary representation.
     * This question.txt is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
     */
    public int findComplement(int num) {
        int dummy = num;
        int n = 1;
        while(num != 0) {
            n <<= 1;
            num >>= 1;
        }
        return dummy ^ (n - 1);
    }

}
