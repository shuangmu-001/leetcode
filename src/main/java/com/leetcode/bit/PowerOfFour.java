package com.leetcode.bit;

/**
 * @author zms
 * @date 6:36 PM 2020/4/23
 * <a href="https://leetcode.com/problems/power-of-four/">
 *     Power of Four</a>
 */
public class PowerOfFour {
    /**
     * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
     *
     * Example 1:
     *
     * Input: 16
     * Output: true
     * Example 2:
     *
     * Input: 5
     * Output: false
     * Follow up: Could you solve it without loops/recursion?
     */
    public boolean isPowerOfFour(int num) {
//        return  num == 0x40000000 ||
//                num == 0x10000000 ||
//                num == 0x04000000 ||
//                num == 0x01000000 ||
//                num == 0x00400000 ||
//                num == 0x00100000 ||
//                num == 0x00040000 ||
//                num == 0x00010000 ||
//                num == 0x00004000 ||
//                num == 0x00001000 ||
//                num == 0x00000400 ||
//                num == 0x00000100 ||
//                num == 0x00000040 ||
//                num == 0x00000010 ||
//                num == 0x00000004 ||
//                num == 0x00000001 ;

        return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 19; i++) {
            System.out.println(((Double)Math.pow(4, i)).intValue());
        }
    }
}
