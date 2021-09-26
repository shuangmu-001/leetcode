package com.leetcode.bit;

/**
 * @author zms
 * @date 6:13 PM 2020/4/23
 * <a href ="https://leetcode.com/problems/binary-number-with-alternating-bits/">
 *     Binary Number with Alternating Bits</a>
 */
public class BinaryNumberWithAlternatingBits {
    /**
     * Given a positive integer, check whether it has alternating bits: namely,
     * if two adjacent bits will always have different values.
     *
     * Example 1:
     * Input: 5
     * Output: True
     * Explanation:
     * The binary representation of 5 is: 101
     * Example 2:
     * Input: 7
     * Output: False
     * Explanation:
     * The binary representation of 7 is: 111.
     * Example 3:
     * Input: 11
     * Output: False
     * Explanation:
     * The binary representation of 11 is: 1011.
     * Example 4:
     * Input: 10
     * Output: True
     * Explanation:
     * The binary representation of 10 is: 1010.
     */
    public boolean hasAlternatingBits(int n) {
        if(n < 2) {
            return true;
        }
        int before = n & 1;
        n >>= 1;
        while(n != 0) {
            int bit = n & 1;
            if ((before ^ bit ) == 0) {
                return false;
            }
            before = bit;
            n >>= 1;
        }
        return true;
    }
}
