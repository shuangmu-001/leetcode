package com.leetcode.string;



import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 3:32 PM 2020/2/17
 * {@link "https://leetcode.com/problems/letter-combinations-of-a-phone-number/"}
 * @see GenerateParentheses
 */
public class LetterCombinationsOfAPhoneNumber {
    /**
     * Given a string containing digits from 2-9 inclusive,
     * return all possible letter combinations that the number could represent.
     * <p>
     * A mapping of digit to letters (just like on the telephone buttons) is given below.
     * Note that 1 does not map to any letters.
     * <p>
     * Example:
     * Input: "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * <p>
     * Note:
     * Although the above answer is in lexicographical order, your answer could be in any order you want.
     */
    public static List<String> letterCombinations1(String digits) {
        if(digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        char[][] chars = {
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };
        int length = digits.length();
        int resultSize = 1;
        for (int i = 0; i < length; i++) {
            char[] aChar = chars[digits.charAt(i) - '2'];
            resultSize *= aChar.length;
        }
        List<String> result = new ArrayList<>(resultSize);
        int[] indexs = new int[length];
        for (int i = 0; i < resultSize; i++) {
            char[] target = new char[length];
            for (int j = 0; j < length; j++) {
                target[j] = chars[digits.charAt(j) - '2'][indexs[j]];
            }
            String string = new String(target);
            result.add(string);
            for (int j = length - 1; j >= 0; j--) {
                if (indexs[j] < chars[digits.charAt(j) - '2'].length - 1) {
                    indexs[j]++;
                    break;
                } else {
                    indexs[j] =0;
                }
            }

        }

        return result;
    }

    /**
     * 暴力 递归实现
     */
    public static List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        char[][] chars = {
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };
        int length = digits.length();
        List<String> results = new ArrayList<>();
        char[] target = new char[length];
        combinations(digits, 0, target, results);
        return results;
    }

    private static final char[][] chars;
    static {
        chars = new char[][]{
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };
    }

    public static void combinations(String digits, int n, char[] result, List<String> results) {
        char source = digits.charAt(n);
        char[] aChar = chars[source - '2'];
        for (char target : aChar) {
            result[n] = target;
            if(n == digits.length() - 1) {
                results.add(new String(result));
            } else {
                combinations(digits, n + 1, result, results);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }
}
