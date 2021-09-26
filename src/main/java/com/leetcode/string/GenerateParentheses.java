package com.leetcode.string;

import com.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zms
 * @date 10:12 AM 2020/2/19
 * {@link "https://leetcode.com/problems/generate-parentheses/"}
 * @see com.leetcode.stack.ValidParentheses
 * @see LetterCombinationsOfAPhoneNumber
 */
public class GenerateParentheses {
    /**
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * <p>
     * For example, given n = 3, a solution set is:
     * [
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     * <p>
     * 1 2 5 14
     */

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n < 0) {
            return result;
        }
        if (n == 1) {
            result.add("()");
            return result;
        }

        int len = n << 1;
        char[] chars = new char[len];
        chars[0] = '(';
        for (int i = 1; i < len; i++) {
            chars[i] = ')';
        }

        addChar(n - 1, 1,1,chars, result);
        Utils.printArrays(chars);
        return result;
    }

    public static void addChar(int max ,int n, int begin, char[] chars, List<String> lists) {

        for (int i = begin; i < (n << 1) + 1; i++) {
            System.out.println(i);
            chars[i] = '(';
            if(max == n) {
                lists.add(new String(chars));
            } else {
                addChar(max,n + 1, i + 1, chars, lists);
            }
            chars[i] = ')';

        }

    }

    public static void main(String[] args) {
//        System.out.println(generateParenthesis(1));
//        System.out.println(generateParenthesis(2));
        System.out.println(generateParenthesis(3));
        System.out.println(generateParenthesis(4));

        int len = 4 << 1;
        char[] chars = new char[len];
        chars[0] = '(';
        for (int i = 1; i < len; i++) {
            chars[i] = ')';
        }
        for (int i = 1; i < 3; i++) {
            for (int j = i + 1; j < 5; j++) {
                System.out.println(j);
                for (int k = j + 1; k < 7; k++) {
                    chars[i] = '(';
                    chars[j] = '(';
                    chars[k] = '(';
                    System.out.println(new String(chars));
                    chars[k] = ')';
                }
                chars[j] = ')';
            }
            chars[i] = ')';
        }

    }
}
