package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 6:57 PM 2020/4/24
 * TODO <a href="https://leetcode.com/problems/letter-case-permutation/">
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
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        char[] chars = S.toCharArray();
        return res;
    }
}
