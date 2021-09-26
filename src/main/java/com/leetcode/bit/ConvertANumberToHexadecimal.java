package com.leetcode.bit;

/**
 * @author zms
 * @date 5:50 PM 2020/4/23
 * <a href="https://leetcode.com/problems/convert-a-number-to-hexadecimal/">
 *     Convert a Number to Hexadecimal</a>
 */
public class ConvertANumberToHexadecimal {
    /**
     * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.
     *
     * Note:
     *
     * All letters in hexadecimal (a-f) must be in lowercase.
     * The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
     * The given number is guaranteed to fit within the range of a 32-bit signed integer.
     * You must not use any method provided by the library which converts/formats the number to hex directly.
     * Example 1:
     *
     * Input:
     * 26
     *
     * Output:
     * "1a"
     * Example 2:
     *
     * Input:
     * -1
     *
     * Output:
     * "ffffffff"
     */
    public static String toHex(int num) {
        StringBuilder sb = new StringBuilder();
        while(num != 0) {
            int n = num & 15;
            if(n >= 10) {
                sb.append((char)('a' + n - 10));
            } else {
                sb.append(n);
            }
            num >>>= 4;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            System.out.println(-1 >>> 4);
        }

    }
}
