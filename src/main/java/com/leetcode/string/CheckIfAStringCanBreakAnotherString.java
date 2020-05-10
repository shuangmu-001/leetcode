package com.leetcode.string;

import com.leetcode.Utils;

import java.util.Arrays;

/**
 * @author wcl
 * @date 3:10 PM 2020/5/6
 * <a href="https://leetcode.com/problems/check-if-a-string-can-break-another-string/">
 *     Check If a String Can Break Another String</a>
 */
public class CheckIfAStringCanBreakAnotherString {
    /**
     * Given two strings: s1 and s2 with the same size,
     * check if some permutation of string s1 can break some permutation of string s2 or vice-versa (in other words s2 can break s1).
     *
     * A string x can break string y (both of size n) if x[i] >= y[i] (in alphabetical order) for all i between 0 and n-1.
     *
     *
     *
     * Example 1:
     *
     * Input: s1 = "abc", s2 = "xya"
     * Output: true
     * Explanation: "ayx" is a permutation of s2="xya" which can break to string "abc" which is a permutation of s1="abc".
     * Example 2:
     *
     * Input: s1 = "abe", s2 = "acd"
     * Output: false
     * Explanation: All permutations for s1="abe" are: "abe", "aeb", "bae", "bea", "eab" and "eba" and all permutation for s2="acd" are: "acd", "adc", "cad", "cda", "dac" and "dca". However, there is not any permutation from s1 which can break some permutation from s2 and vice-versa.
     * Example 3:
     *
     * Input: s1 = "leetcodee", s2 = "interview"
     * Output: true
     *
     *
     * Constraints:
     *
     * s1.length == n
     * s2.length == n
     * 1 <= n <= 10^5
     * All strings consist of lowercase English letters.
     */
    public static boolean checkIfCanBreak1(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        int n = s1.length();
        int index = 0;
        while(index < n && c1[index] == c2[index]) {
            index++;
        }
        if(index >= n) {
            return true;
        }
        Utils.printArrays(c1);
        Utils.printArrays(c2);
        if (c1[index] < c2[index]) {
            char[] temp = c1;
            c1 = c2;
            c2 = temp;
            index++;
        }
        Utils.printArrays(c1);
        Utils.printArrays(c2);
        for (int i = index; i < n; i++) {
            if(c1[i] < c2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIfCanBreak(String s1, String s2) {
        int[] c1 = new int[26];
        int[] c2 = new int[26];
        for (char c : s1.toCharArray()) {
            c1[c - 'a']++;
        }
        for (char c : s2.toCharArray()) {
            c2[c - 'a']++;
        }

        return check(c1, c2) || check(c2, c1);
    }
    public static boolean check(int[] c1, int[] c2) {
        int s = 0;
        for (int i = 0; i < c1.length; i++) {
            s += c1[i] - c2[i];
            if(s < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(checkIfCanBreak("abrfjalsdjfeiowjfc", "xyajalsdkfjeioanvn"));
    }
}
