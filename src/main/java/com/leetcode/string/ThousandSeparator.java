package com.leetcode.string;

import java.util.Locale;

/**
 * @author zms
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
    // 最长字串 中间有空格
    public static void main(String[] args) {
        System.out.println(4 == longStr("a b   123#"));
        System.out.println(1 == longStr("a"));
        System.out.println(0 == longStr("     "));
        System.out.println(1 == longStr("   a  "));
        System.out.println(5 == longStr("    abaev"));
        System.out.println(6 == longStr("    abaev  fefe er ew eeeeee"));
        System.out.println("jsfklejwoij".length() == longStr("a b   123#   jsfklejwoij"));
        System.out.println("bwjefvwjoijvoew".length() == longStr("a bwjefvwjoijvoew   123# jfei jfeifee  fe          "));
    }

    public static int longStr(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int n = s.length();
        char[] c = s.toCharArray();
        int max = 0;
        int i = 0, j = 0;
        while (j < n) {
            while (j < n && c[j] == ' ') {
                j++;
            }
            if (j >= n) {
                return max;
            }
            i = j;
            while (j < n && c[j] != ' ') {
                j++;
            }
            max = Math.max(max, j - i);
        }
//        while(j < n) {
//            if(c[j] == ' ') {
//                if(i != j) {
//                    max = Math.max(max, j - i);
//                }
//                j++;
//                i = j;
//            } else {
//                j++;
//            }
//        }
//        max = Math.max(max, j - i);
        return max;
    }


}
