package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        // current index of character
        int[] index = new int[128];
        // try to extend the range [i, j]
        // i -> The position where the substring begins
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
//        if(s == null || s.isEmpty()) {
//            return 0;
//        }
//
//        char[] chars = s.toCharArray();
//        int length = chars.length;
//        String result = "";
//        String str = chars[0] + "";
//        for(int i = 1; i < length; i++) {
//            if(!str.contains(chars[i] + "")) {
//                str += chars[i];
//            } else {
//
//                if(result.length() < str.length()){
//                    result = str;
//                }
//                int indexOf = str.indexOf(chars[i]);
//                str = str.substring(indexOf + 1);
//                str += chars[i];
//            }
//
//            if(result.length() > str.length() + length - i) {
//                break;
//            }
//
//        }
//
//        if(result.length() < str.length()){
//            result = str;
//        }
//
//        return result.length();
    }

    public static void main(String[] args) {
//        String input01 = "abcabcbb";
//        System.out.println(lengthOfLongestSubstring(input01) == 3);
//        String input02 = "bbbbb";
//        System.out.println(lengthOfLongestSubstring(input02) == 1);
        String input03 = "abbccb";
        System.out.println(lengthOfLongestSubstring(input03) == 2);
        String input04 = "dvdf";
        int indexOf = input04.indexOf("v");
        System.out.println(input04.substring(indexOf));
        System.out.println(lengthOfLongestSubstring(input04) == 3);
    }
}
