package com.leetcode.slidingwindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wcl
 * @date 2:16 下午 2020/5/25
 * <a href="https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/">
 * Maximum Number Of Vowels In A Substring Of Given Length</a>
 */
public class MaximumNumberOfVowelsInASubstringOfGivenLength {
    /**
     * Given a string s and an integer k.
     * Return the maximum number of vowel letters in any substring of s with length k.
     * Vowel letters in English are (a, e, i, o, u).
     *
     * Example 1:
     * Input: s = "abciiidef", k = 3
     * Output: 3
     * Explanation: The substring "iii" contains 3 vowel letters.
     *
     * Example 2:
     * Input: s = "aeiou", k = 2
     * Output: 2
     * Explanation: Any substring of length 2 contains 2 vowels.
     *
     * Example 3:
     * Input: s = "leetcode", k = 3
     * Output: 2
     * Explanation: "lee", "eet" and "ode" contain 2 vowels.
     *
     * Example 4:
     * Input: s = "rhythms", k = 4
     * Output: 0
     * Explanation: We can see that s doesn't have any vowel letters.
     *
     * Example 5:
     * Input: s = "tryhard", k = 4
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of lowercase English letters.
     * 1 <= k <= s.length
     */
    static Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    public static int maxVowels(String s, int k) {
        char[] chars = s.toCharArray();
        int res = 0;

        int before = 0;
        int length = s.length();
//        int left = 0;
//        int right = 0;
//        while(left < length && right < length) {
//            while(right - left >= k) {
//                if(vowels.contains(chars[left])) {
//                    before--;
//                }
//                left++;
//            }
//            if(vowels.contains(chars[right++])) {
//                before++;
//            }
//            res = Math.max(res, before);
//        }
        for (int i = 0; i < length; i++) {
            if(i >= k) {
                if(vowels.contains(chars[i - k])) {
                    before--;
                }
            }
            if (vowels.contains(chars[i])) {
                before++;
            }
            res = Math.max(res, before);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(maxVowels("aeiou",5));
    }
}
