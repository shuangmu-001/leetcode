package com.leetcode.string;

/**
 * @author zms
 * @date 5:23 PM 2020/5/7
 * <a href="https://leetcode.com/problems/add-binary/">
 * Add Binary</a>
 */
public class AddBinary {
    /**
     * Given two binary strings, return their sum (also a binary string).
     * The input strings are both non-empty and contains only characters 1 or 0.
     * <p>
     * Example 1:
     * Input: a = "11", b = "1"
     * Output: "100"
     * <p>
     * Example 2:
     * Input: a = "1010", b = "1011"
     * Output: "10101"
     * <p>
     * <p>
     * Constraints:
     * <p>
     * Each string consists only of '0' or '1' characters.
     * 1 <= a.length, b.length <= 10^4
     * Each string is either "0" or doesn't contain any leading zero.
     */
    public static String addBinary(String a, String b) {
        int aL = a.length();
        int bL = b.length();
        int length = Math.max(aL, bL) + 1;
        char[] res = new char[length];
        int aIndex = aL - 1;
        int bIndex = bL - 1;
        int crass = 0;
        for (int i = length - 1; i >= 0; i--) {
            int ac = aIndex >= 0 ? a.charAt(aIndex--) - '0' : 0;
            int bc = bIndex >= 0 ? b.charAt(bIndex--) - '0' : 0;
            res[i] = (char) (((ac + bc + crass) % 2) + '0');
            crass = (ac + bc + crass) / 2;
        }
        return res[0] == '0' ? new String(res, 1, length - 1) : new String(res);
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
        System.out.println(addBinary("11", "0"));
    }
}
