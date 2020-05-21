package com.leetcode.string;

/**
 * @author wcl
 * @date 5:19 下午 2020/5/19
 * <a href="https://leetcode.com/problems/consecutive-characters/">
 * Consecutive Characters</a>
 */
public class ConsecutiveCharacters {
    /**
     * Given a string s, the power of the string is the maximum length of a non-empty substring that contains only one unique character.
     *
     * Return the power of the string.
     *
     * Example 1:
     * Input: s = "leetcode"
     * Output: 2
     * Explanation: The substring "ee" is of length 2 with the character 'e' only.
     *
     * Example 2:
     * Input: s = "abbcccddddeeeeedcba"
     * Output: 5
     * Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
     * Example 3:
     *
     * Input: s = "triplepillooooow"
     * Output: 5
     * Example 4:
     *
     * Input: s = "hooraaaaaaaaaaay"
     * Output: 11
     * Example 5:
     *
     * Input: s = "tourist"
     * Output: 1
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 500
     * s contains only lowercase English letters.
     */
    public static int maxPower(String s) {
        int length = s.length();
        if(length == 1) {
            return 1;
        }
        int max = 1;
        char before = s.charAt(0);
        int beforeIndex = 0;
        for (int i = 1; i < length; i++) {
            char curr = s.charAt(i);
            if(curr != before) {
                max = Math.max(max, i - beforeIndex);
                before = curr;
                beforeIndex = i;
            } else if(i == length - 1) {
                max = Math.max(max, i - beforeIndex + 1);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxPower("hooraaaaaaaaaaay") == 11);
        System.out.println(maxPower("tourist") == 1);
        System.out.println(maxPower("leetcode") == 2);
        System.out.println(maxPower("abbcccddddeeeeedcba") == 5);
        System.out.println(maxPower("triplepillooooow") == 5);
    }
}
