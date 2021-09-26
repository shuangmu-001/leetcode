package com.leetcode.bit;

/**
 * @author zms
 * @date 4:09 PM 2020/4/23
 * <a href="https://leetcode.com/problems/hamming-distance/">
 *     Hamming Distance</a>
 *
 * 异或
 */
public class HammingDistance {
    /**
     * The Hamming distance between two integers is the number
     * of positions at which the corresponding bits are different.
     * Given two integers x and y, calculate the Hamming distance.
     *
     * Note: 0 ≤ x, y < 2^31.
     *
     * Example:
     *
     * Input: x = 1, y = 4
     *
     * Output: 2
     *
     * Explanation:
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     *        ↑   ↑
     *
     * The above arrows point to positions where the corresponding bits are different.
     */
    public int hammingDistance1(int x, int y) {
        int res = 0;
        while(x != 0 && y != 0) {
            if(((x & 1) != 0 && (y & 1) == 0) || ((x & 1) == 0 && (y & 1) != 0)) {
                res++;
            }
            x = x >> 1;
            y = y >> 1;
        }
        while(x != 0) {
            if((x & 1) != 0) {
                res++;
            }
            x = x >> 1;
        }
        while(y != 0) {
            if((y & 1) != 0) {
                res++;
            }
            y = y >> 1;
        }
        return res;
    }

    public int hammingDistance(int x, int y) {

        return Integer.bitCount(x ^ y);
    }
}
