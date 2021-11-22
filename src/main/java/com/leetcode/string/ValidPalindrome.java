package com.leetcode.string;

/**
 * @author zms
 * @date 11:07 上午 2021/11/22
 */
public class ValidPalindrome {
    /**
     * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters,
     * it reads the same forward and backward. Alphanumeric characters include letters and numbers.
     * Given a string s, return true if it is a palindrome, or false otherwise.
     * <p>
     * Example 1:
     * Input: s = "A man, a plan, a canal: Panama"
     * Output: true
     * Explanation: "amanaplanacanalpanama" is a palindrome.
     * <p>
     * Example 2:
     * Input: s = "race a car"
     * Output: false
     * Explanation: "raceacar" is not a palindrome.
     * <p>
     * Example 3:
     * Input: s = " "
     * Output: true
     * Explanation: s is an empty string "" after removing non-alphanumeric characters.
     * Since an empty string reads the same forward and backward, it is a palindrome.
     * <p>
     * <p>
     * Constraints:
     * 1 <= s.length <= 2 * 10^5
     * s consists only of printable ASCII characters.
     */
    public boolean isPalindrome(String s) {
        char[] str = s.toCharArray();
        int n = s.length();
        int l = 0, r = n - 1;
        while (l <= r) {
            while (l < n && isValid(str[l])) {
                l++;
            }
            while (r >= 0 && isValid(str[r])) {
                r--;
            }

            if (l <= r && parse(str[l++]) != parse(str[r--])) {
                return false;
            }
        }
        return true;
    }

    public char parse(char c) {
        if (isUp(c)) {
            return (char) (c - ('A' - 'a'));
        }
        return c;
    }

    public boolean isValid(char c) {
        return (c < '0' || c > '9') && (c < 'a' || c > 'z') && !isUp(c);
    }

    public boolean isUp(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static void main(String[] args) {

    }
}
