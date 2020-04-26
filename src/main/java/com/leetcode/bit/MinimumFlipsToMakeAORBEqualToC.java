package com.leetcode.bit;

/**
 * @author wcl
 * @date 10:55 AM 2020/4/24
 * <a href="https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/">
 *     Minimum Flips to Make a OR b Equal to c</a>
 */
public class MinimumFlipsToMakeAORBEqualToC {
    /**
     * Given 3 positives numbers a, b and c.
     * Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
     * Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.
     *
     * Example 1:
     *
     * Input: a = 2, b = 6, c = 5
     * Output: 3
     * Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
     *
     * Example 2:
     *
     * Input: a = 4, b = 2, c = 7
     * Output: 1
     * Example 3:
     *
     * Input: a = 1, b = 2, c = 3
     * Output: 0
     *
     *
     * Constraints:
     *
     * 1 <= a <= 10^9
     * 1 <= b <= 10^9
     * 1 <= c <= 10^9
     */
    public static int minFlips(int a, int b, int c) {
        int count = 0;
        while(c != 0) {
            int e = a & 1;
            int f = b & 1;
            int g = c & 1;
            if(g == 0) {
                count += e + f;
            } else {
                count += ((e | f) == g) ? 0 : 1;
            }
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }
        return count + Integer.bitCount(a) + Integer.bitCount(b);
    }

    public static void main(String[] args) {
//        System.out.println(minFlips(2, 6, 1023));
        System.out.println(minFlips(4, 2, 7));
    }
}
