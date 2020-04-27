package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 6:57 PM 2020/4/24
 * <a href="https://leetcode.com/problems/letter-case-permutation/">
 *     Letter Case Permutation</a>
 */
public class LetterCasePermutation {
    /**
     * Given a string S,
     * we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.
     *
     * Examples:
     * Input: S = "a1b2"
     * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
     *
     * Input: S = "3z4"
     * Output: ["3z4", "3Z4"]
     *
     * Input: S = "12345"
     * Output: ["12345"]
     * Note:
     *
     * S will be a string with length between 1 and 12.
     * S will consist only of letters or digits.
     */
    static List<String> res;
    public static List<String> letterCasePermutation(String S) {
        res = new ArrayList<>();
        char[] chars = S.toCharArray();
        letterCasePermutation(chars, -1, new char[S.length()]);
        return res;
    }

    public static void letterCasePermutation(char[] chars, int start, char[] newChars) {
        if(start > chars.length - 1) {
            return;
        }
        if(start == chars.length - 1) {
            res.add(new String(newChars));
            return;
        }
        char c = chars[start + 1];
        newChars[start + 1] = c;
        letterCasePermutation(chars, start + 1, newChars);
        if(c >= 'a' && c <= 'z') {
            newChars[start + 1] = (char)(c - 32);
            letterCasePermutation(chars, start + 1, newChars);
        } else if(c >= 'A' && c <= 'Z') {
            newChars[start + 1] = (char)(c + 32);
            letterCasePermutation(chars, start + 1, newChars);
        }

    }

    public static void main(String[] args) {
        System.out.println(letterCasePermutation("a1b2"));
        System.out.println(letterCasePermutation("3z4"));
        System.out.println(letterCasePermutation("12345"));
        System.out.println(letterCasePermutation("a1b2cser"));

    }

}
