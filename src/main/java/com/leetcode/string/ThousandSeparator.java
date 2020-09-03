package com.leetcode.string;

/**
 * @author wcl
 * @date 2:23 下午 2020/8/28
 * <a href="https://leetcode.com/problems/thousand-separator/">
 * Thousand Separator</a>
 */
public class ThousandSeparator {
    /**
     * Given an integer n, add a dot (".") as the thousands separator and return it in string format.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: n = 987
     * Output: "987"
     * Example 2:
     * <p>
     * Input: n = 1234
     * Output: "1.234"
     * Example 3:
     * <p>
     * Input: n = 123456789
     * Output: "123.456.789"
     * Example 4:
     * <p>
     * Input: n = 0
     * Output: "0"
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 0 <= n < 2^31
     */
    public String thousandSeparator1(int n) {
        String format = String.format("%,d", n);
        return format.replaceAll(",", ".");
    }

    public String thousandSeparator(int n) {
        StringBuilder source = new StringBuilder();
        source.append(n);
        if (n < 1000) {
            return source.toString();
        }
        int length = source.length();
        int first = length % 3;
        int index = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < first; i++) {
            result.append(source.charAt(i));
        }
        if (first != length) {
            result.append(".");
        }
        for (int i = first; i < length; i++) {
            index++;
            result.append(source.charAt(i));
            if (index == 3) {
                if (i != length - 1) {
                    result.append(".");
                }
                index = 0;
            }
        }
        return result.toString();
    }


}
