package com.leetcode.twopoints;

/**
 * <a href="https://leetcode.com/problems/reverse-string-ii/">reverse-string-ii</a>
 *
 * @author zms
 * @date 10:54 上午 2021/10/11
 */
public class ReverseStringII {
    /**
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
     * <p>
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     * <p>
     * <p>
     * 示例 1：
     * 输入：s = "abcdefg", k = 2
     * 输出："bacdfeg"
     * <p>
     * 示例 2：
     * 输入：s = "abcd", k = 2
     * 输出："bacd"
     * <p>
     * 提示：
     * 1 <= s.length <= 10^4
     * s 仅由小写英文组成
     * 1 <= k <= 10^4
     */
    public String reverseStr(String s, int k) {
        if (k == 1 || s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int l = 0; l < s.length(); l += (k << 1)) {
            int r = Math.min(l + k - 1, s.length() - 1);
            reverse(chars, l, r);
        }
        return new String(chars);
    }

    public void reverse(char[] chars, int l, int r) {
        if (l < 0 || r >= chars.length) {
            return;
        }
        while (l < r) {
            char temp = chars[l];
            chars[l++] = chars[r];
            chars[r--] = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ReverseStringII().reverseStr("abcdefg", 2));
    }
}
