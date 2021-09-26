package com.leetcode.bit;

/**
 * @author zms
 * @date 3:18 PM 2020/4/23
 * <a href=https://leetcode.com/problems/bitwise-and-of-numbers-range/">
 *     Bitwise AND of Numbers Range</a>
 */
public class BitwiseANDOfNumbersRange {
    /**
     * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
     *
     * Example 1:
     *
     * Input: [5,7]
     * Output: 4
     * Example 2:
     *
     * Input: [0,1]
     * Output: 0
     */
    public static int rangeBitwiseAnd(int m, int n) {
        if(m == 0) {
            return 0;
        }
        if(m == 1 ) {
            return n > 1 ? 0 : 1;
        }

        if(m < (Integer.MAX_VALUE >> 1) && (m << 1) < n) {
            return 0;
        }
        int res = m;
        for (int i = m; i < n; i++) {
            res &= i;
        }
        return res & n;
    }

    public static void main(String[] args) {
//        System.out.println(rangeBitwiseAnd(2, 2147483647));
        System.out.println(rangeBitwiseAnd(2147483642, 2147483647));
        System.out.println(2147483647 >> 1);
        System.out.println(2147483642 & 2147483643 & 2147483644 & 2147483645 & 2147483646 & 2147483647);
    }
}
