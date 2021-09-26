package com.leetcode.string;

/**
 * @author zms
 * @date 4:53 下午 2020/9/8
 * <a href="https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/">
 *     Replace All ?'s to Avoid Consecutive Repeating Characters</a>
 */
public class ReplaceAllToAvoidConsecutiveRepeatingCharacters {
    /**
     * Given a string s containing only lower case English letters and the '?' character,
     * convert all the '?' characters into lower case letters such that the final string does not contain any consecutive repeating characters.
     * You cannot modify the non '?' characters.
     * It is guaranteed that there are no consecutive repeating characters in the given string except for '?'.
     * Return the final string after all the conversions (possibly zero) have been made.
     * If there is more than one solution, return any of them.
     * It can be shown that an answer is always possible with the given constraints.
     *
     * Example 1:
     * Input: s = "?zs"
     * Output: "azs"
     * Explanation: There are 25 solutions for this problem. From "azs" to "yzs", all are valid.
     * Only "z" is an invalid modification as the string will consist of consecutive repeating characters in "zzs".
     *
     * Example 2:
     * Input: s = "ubv?w"
     * Output: "ubvaw"
     * Explanation: There are 24 solutions for this problem.
     * Only "v" and "w" are invalid modifications as the strings will consist of consecutive repeating characters in "ubvvw" and "ubvww".
     *
     * Example 3:
     * Input: s = "j?qg??b"
     * Output: "jaqgacb"
     *
     * Example 4:
     * Input: s = "??yw?ipkj?"
     * Output: "acywaipkja"
     *
     * Constraints:
     * 1 <= s.length <= 100
     * s contains only lower case English letters and '?'.
     */
    public String modifyString(String s) {
        if(s == null || s.isEmpty()) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int c = chars[i];
            if(c != '?') {
                continue;
            }
            char before = i == 0 ? (char)('a' - 1) : chars[i - 1];
            char after = i == chars.length - 1 ? (char)('z' + 1) : chars[i + 1];
            for(int j = 0; j < 26; j++) {
                char target = (char)('a' + j);
                if(target != before && target != after) {
                    chars[i] = target;
                    break;
                }
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println((char)('z' + 1));
    }
}
