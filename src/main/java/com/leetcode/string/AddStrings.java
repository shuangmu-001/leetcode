package com.leetcode.string;

/**
 * <a href="https://leetcode.com/problems/add-strings/">Add Strings</a>
 *
 * @author zms
 * @date 5:23 下午 2021/11/8
 */
public class AddStrings {
    /**
     * Given two non-negative integers, num1 and num2 represented as string,
     * return the sum of num1 and num2 as a string.
     * <p>
     * You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
     * You must also not convert the inputs to integers directly.
     * <p>
     * Example 1:
     * Input: num1 = "11", num2 = "123"
     * Output: "134"
     * <p>
     * Example 2:
     * Input: num1 = "456", num2 = "77"
     * Output: "533"
     * <p>
     * Example 3:
     * Input: num1 = "0", num2 = "0"
     * Output: "0"
     * <p>
     * Constraints:
     * 1 <= num1.length, num2.length <= 10^4
     * num1 and num2 consist of only digits.
     * num1 and num2 don't have any leading zeros except for the zero itself.
     */
    public static String addStrings(String a, String b) {
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
            res[i] = (char) (((ac + bc + crass) % 10) + '0');
            crass = (ac + bc + crass) / 10;
        }
        return res[0] == '0' ? new String(res, 1, length - 1) : new String(res);
    }

    public static void main(String[] args) {
        System.out.println(addStrings("0", "0").equals("0"));
        System.out.println(addStrings("456", "77").equals(456 + 77 + ""));
        System.out.println(addStrings("999", "999").equals(999 + 999 + ""));
    }
}
