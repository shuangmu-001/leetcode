package com.leetcode.twopoints;

/**
 * <a href="https://leetcode.com/problems/reverse-words-in-a-string-iii/">Reverse Words in a String III</a>
 *
 * @author zms
 * @date 10:45 上午 2021/10/11
 */
public class ReverseWordsInAStringIII {
    /**
     * Given a string s, reverse the order of characters in each word within a sentence
     * while still preserving whitespace and initial word order.
     * <p>
     * Example 1:
     * Input: s = "Let's take LeetCode contest"
     * Output: "s'teL ekat edoCteeL tsetnoc"
     * <p>
     * Example 2:
     * Input: s = "God Ding"
     * Output: "doG gniD"
     * <p>
     * Constraints:
     * 1 <= s.length <= 5 * 104
     * s contains printable ASCII characters.
     * s does not contain any leading or trailing spaces.
     * There is at least one word in s.
     * All the words in s are separated by a single space.
     */
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int length = s.length();
        int l = 0;
        for (int i = 0; i < length; i++) {
            if (chars[i] == ' ' || i == length - 1) {
                int r = i == (length - 1) ? length - 1 : i - 1;
                while (l < r) {
                    char temp = chars[l];
                    chars[l++] = chars[r];
                    chars[r--] = temp;
                }
                l = i + 1;
            }
        }
        return new String(chars);
    }
}
