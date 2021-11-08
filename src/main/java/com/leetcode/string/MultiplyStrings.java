package com.leetcode.string;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.com/problems/multiply-strings/">Multiply Strings</a>
 *
 * @author zms
 * @date 3:37 下午 2021/11/8
 */
public class MultiplyStrings {
    /**
     * Given two non-negative integers num1 and num2 represented as strings,
     * return the product of num1 and num2, also represented as a string.
     * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
     * <p>
     * Example 1:
     * Input: num1 = "2", num2 = "3"
     * Output: "6"
     * <p>
     * Example 2:
     * Input: num1 = "123", num2 = "456"
     * Output: "56088"
     * <p>
     * Constraints:
     * 1 <= num1.length, num2.length <= 200
     * num1 and num2 consist of digits only.
     * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
     */
    public static String multiply01(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        LinkedList<Integer> pre = new LinkedList<>();
        LinkedList<Integer> cur = new LinkedList<>();
        char[] str1 = num1.toCharArray();
        char[] str2 = num2.toCharArray();
        int m = str1.length;
        int n = str2.length;
        StringBuilder sb = new StringBuilder();

        for (int i = m - 1; i >= 0; i--) {
            int carry = 0;
            int l = str1[i] - '0';
            for (int j = n - 1; j >= 0; j--) {
                int r = str2[j] - '0';
                Integer res = pre.pollFirst();
                if (res == null) {
                    res = 0;
                }
                res += (carry + (l * r));
                carry = res / 10;
                res = res % 10;
                cur.addLast(res);
            }
            while (!pre.isEmpty()) {
                Integer res = pre.pollFirst();
                res += carry;
                if (res >= 10) {
                    carry = res / 10;
                    res = res % 10;
                }
            }
            if (carry != 0) {
                cur.addLast(carry);
            }
            sb.append(cur.pollFirst());
            LinkedList<Integer> temp = pre;
            pre = cur;
            cur = temp;
        }
        pre.forEach(sb::append);
        System.out.println(sb);
        return sb.reverse().toString();
    }

    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        char[] str1 = num1.toCharArray();
        char[] str2 = num2.toCharArray();
        int m = str1.length;
        int n = str2.length;
        int[] ans = new int[m + n + 1];
        int start = 0;
        int index = 0;
        for (int i = m - 1; i >= 0; i--) {
            int l = str1[i] - '0';
            index = start;
            for (int j = n - 1; j >= 0; j--) {
                int r = str2[j] - '0';
                int res = ans[index] + (r * l);
                ans[index++] = res % 10;
                ans[index] += res / 10;
            }
            start++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ans[index] == 0 ? "" : ans[index]);
        for (int i = index - 1; i >= 0; i--) {
            sb.append(ans[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("2", "3").equals(2 * 3 + ""));
        System.out.println(multiply("123", "456").equals(456 * 123 + ""));
        System.out.println(multiply("999", "999").equals(999 * 999 + ""));

    }
}
