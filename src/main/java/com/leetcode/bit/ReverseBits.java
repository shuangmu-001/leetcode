package com.leetcode.bit;

/**
 * @author wcl
 * @date 5:28 PM 2020/4/23
 * <a href="https://leetcode.com/problems/reverse-bits/">
 *     Reverse Bits</a>
 */
public class ReverseBits {
    /**
     * Reverse bits of a given 32 bits unsigned integer.
     *
     * Example 1:
     *
     * Input: 00000010100101000001111010011100
     * Output: 00111001011110000010100101000000
     * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
     * Example 2:
     *
     * Input: 11111111111111111111111111111101
     * Output: 10111111111111111111111111111111
     * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10111111111111111111111111111111.
     *
     *
     * Note:
     *
     * Note that in some languages such as Java, there is no unsigned integer type. In this case, both input and output will be given as signed integer type and should not affect your implementation, as the internal binary representation of the integer is the same whether it is signed or unsigned.
     * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above the input represents the signed integer -3 and the output represents the signed integer -1073741825.
     *
     *
     * Follow up:
     *
     * If this function is called many times, how would you optimize it?
     */
    public int reverseBits(int n) {
        if(n == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            if((n & 1) != 0) {
                res++;
            }
            n >>= 1;
        }

        return res;
    }
    // TODO
    public int reverseBits1(int n) {
        int res = n;
        res = (res >>> 16) | (res << 16);
        res = (res & 0xff00ff00) >>> 8 | (res & 0x00ff00ff) << 8;
        res = (res & 0xf0f0f0f0) >>> 4 | (res & 0x0f0f0f0f) << 4;
        res = (res & 0xcccccccc) >>> 2 | (res & 0x33333333) << 2;
        res = (res & 0xaaaaaaaa) >>> 1 | (res & 0x55555555) << 1;
        return res;
    }
}
