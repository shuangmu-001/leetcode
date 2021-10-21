package com.leetcode.bit;

/**
 * <a href="https://leetcode.com/problems/number-of-1-bits/">Number of 1 Bits</a>
 *
 * @author zms
 * @date 4:20 PM 2020/4/23
 */
public class NumberOfBits {
    /**
     * Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).
     * <p>
     * Example 1:
     * Input: 00000000000000000000000000001011
     * Output: 3
     * Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
     * <p>
     * Example 2:
     * Input: 00000000000000000000000010000000
     * Output: 1
     * Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
     * <p>
     * Example 3:
     * <p>
     * Input: 11111111111111111111111111111101
     * Output: 31
     * Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
     * <p>
     * <p>
     * Note:
     * <p>
     * Note that in some languages such as Java, there is no unsigned integer type. In this case, the input will be given as signed integer type and should not affect your implementation, as the internal binary representation of the integer is the same whether it is signed or unsigned.
     * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3 above the input represents the signed integer -3.
     * <p>
     * <p>
     * Follow up:
     * <p>
     * If this function is called many times, how would you optimize it?
     */
    public int hammingWeight(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            // 将最近的一个1 变成0 n & n - 1;
            n &= (n - 1);
        }
        return sum;
    }

    public int hammingWeight01(int n) {
        int i = n;
        i = i - ((i & 0xaaaaaaaa) >>> 1);
        i = (i & 0x33333333) + ((i & 0xcccccccc) >>> 2);
        i = (i & 0x0f0f0f0f) + ((i & 0xf0f0f0f0) >>> 4);
        i = (i & 0x00ff00ff) + ((i & 0xff00ff00) >>> 8);
        i = (i & 0x0000ffff) + ((i & 0xffff0000) >>> 16);
        return i;
    }
}
