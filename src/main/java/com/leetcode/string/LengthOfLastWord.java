package com.leetcode.string;

/**
 * @author wcl
 * @date 3:23 下午 2020/9/15
 * <a href="https://leetcode.com/problems/length-of-last-word/">
 *     Length of Last Word</a>
 */
public class LengthOfLastWord {
    /**
     * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word (last word means the last appearing word if we loop from left to right) in the string.
     *
     * If the last word does not exist, return 0.
     *
     * Note: A word is defined as a maximal substring consisting of non-space characters only.
     *
     * Example:
     *
     * Input: "Hello World"
     * Output: 5
     */
    public int lengthOfLastWord(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }
        int end = s.length() - 1;
        while(end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        if(end < 0) {
            return 0;
        }
        int len = 0;
        while(end >= 0 && s.charAt(end) != ' ') {
            end--;
            len++;
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println("     ".isEmpty());
        System.out.println(new LengthOfLastWord().lengthOfLastWord("     "));
        System.out.println(new LengthOfLastWord().lengthOfLastWord("   world  "));
        System.out.println(new LengthOfLastWord().lengthOfLastWord("hello world"));
    }
}
