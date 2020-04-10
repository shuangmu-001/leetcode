package com.leetcode.stack;

import java.util.Stack;

/**
 * @author wcl
 * @date 3:35 PM 2020/4/9
 * <a href="https://leetcode.com/problems/backspace-string-compare/">
 *     Backspace String Compare</a>
 */
public class BackspaceStringCompare {
    /**
     * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
     *
     * Example 1:
     *
     * Input: S = "ab#c", T = "ad#c"
     * Output: true
     * Explanation: Both S and T become "ac".
     * Example 2:
     *
     * Input: S = "ab##", T = "c#d#"
     * Output: true
     * Explanation: Both S and T become "".
     * Example 3:
     *
     * Input: S = "a##c", T = "#a#c"
     * Output: true
     * Explanation: Both S and T become "c".
     * Example 4:
     *
     * Input: S = "a#c", T = "b"
     * Output: false
     * Explanation: S becomes "c" while T becomes "b".
     * Note:
     *
     * 1 <= S.length <= 200
     * 1 <= T.length <= 200
     * S and T only contain lowercase letters and '#' characters.
     * Follow up:
     *
     * Can you solve it in O(N) time and O(1) space?
     */
    public static boolean backspaceCompare1(String S, String T) {
        Stack<Character> s = new Stack<>();
        Stack<Character> t = new Stack<>();
        for (char c : S.toCharArray()) {
            if(c == '#' && !s.isEmpty()) {
                s.pop();
            } else if(c != '#'){
                s.push(c);
            }
        }
        for (char c : T.toCharArray()) {
            if(c == '#' && !t.isEmpty()) {
                t.pop();
            } else if(c != '#'){
                t.push(c);
            }
        }
        while(!s.isEmpty()) {
            if(t.isEmpty()) {
                return false;
            }
            Character pop1 = s.pop();
            Character pop2 = t.pop();
            if(!pop1.equals(pop2)) {
                return false;
            }
        }
        return t.isEmpty();
    }

    public static boolean backspaceCompare(String S, String T) {
        int sIndex = S.length() - 1;
        int tIndex = T.length() - 1;
        while(sIndex >= 0 && tIndex >= 0) {
            char s = S.charAt(sIndex);
            char t = T.charAt(tIndex);
            if(s != '#' && t != '#' ) {
                if(s != t) {
                    return false;
                } else {
                    sIndex--;
                    tIndex--;
                }
            }

            if(s == '#') {
                sIndex = backspace(S, sIndex);
            }

            if(t == '#') {
                tIndex = backspace(T, tIndex);
            }
        }
        if(tIndex >= 0) {
            if(T.charAt(tIndex) != '#') {
                return false;
            }
            return backspace(T, tIndex) <= 0;
        } else if(sIndex >= 0) {
            if(S.charAt(sIndex) != '#') {
                return false;
            }
            return backspace(S, sIndex) <= 0;
        }
        return true;
    }

    private static int backspace(String S, int index) {
        int count = 1;
        while(index > 0) {
            index--;
            char s = S.charAt(index);
            if(s != '#' && count == 0) {
                return index;
            }
            if(s == '#' ) {
                count++;
            } else {
                count--;
            }
        }
        return index - 1;
    }

    public static void main(String[] args) {
        System.out.println(backspaceCompare("ab#c", "ad#c"));
        System.out.println(backspaceCompare("y#fo##f", "y#f#o##f"));
        System.out.println(backspaceCompare("y#fo##f", "h#y#f#o##f"));
        System.out.println(backspaceCompare1("y#fo##f", "h#hy#f#o##f"));
        System.out.println(backspaceCompare("bxj##tw", "bxo#j##tw"));
        System.out.println(!backspaceCompare("bxj##tw", "bxj###tw"));
        System.out.println(backspaceCompare("du###vu##v#fbtu", "du###vu##v##fbtu"));

//        int len = "y#f#o##".length();
//        System.out.println(backspace("y#f#o##", len - 1));
    }
}
