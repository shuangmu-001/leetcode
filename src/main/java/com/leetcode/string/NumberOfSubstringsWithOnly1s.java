package com.leetcode.string;

import com.Utils;

/**
 * @author wcl
 * @date 11:10 上午 2020/7/14
 * <a href="https://leetcode.com/problems/number-of-substrings-with-only-1s/">
 *     Number of Substrings With Only 1s</a>
 */
public class NumberOfSubstringsWithOnly1s {
    /**
     * Given a binary string s (a string consisting only of '0' and '1's).
     *
     * Return the number of substrings with all characters 1's.
     *
     * Since the answer may be too large, return it modulo 10^9 + 7.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "0110111"
     * Output: 9
     * Explanation: There are 9 substring in total with only 1's characters.
     * "1" -> 5 times.
     * "11" -> 3 times.
     * "111" -> 1 time.
     * Example 2:
     *
     * Input: s = "101"
     * Output: 2
     * Explanation: Substring "1" is shown 2 times in s.
     * Example 3:
     *
     * Input: s = "111111"
     * Output: 21
     * Explanation: Each substring contains only 1's characters.
     * Example 4:
     *
     * Input: s = "000"
     * Output: 0
     *
     *
     * Constraints:
     *
     * s[i] == '0' or s[i] == '1'
     * 1 <= s.length <= 10^5
     */

    static long[] times;
    public static int numSub(String s) {
        int result = 0;
        int length = s.length();
        times = new long[length];
        times[0] = 1;
        int start = 0;
        char[] chars = s.toCharArray();
        for (int i = start; i < length;) {
            if(chars[i] == '1') {
                i++;
                if(i == length) {
                    result += dfs(i - start - 1);
                }
            } else {
                result += dfs(i - start - 1);
                start = i + 1;
                while(start < length && chars[start] == '0') {
                    start++;
                }
                i = start;
            }
        }

        return result;
    }

    public static int dfs(int length) {
        if(length < 0) {
            return 0;
        }
        if(times[length] != 0) {
            return (int)(times[length] % 1000_000_007);
        }
        times[length] = dfs(length - 1) + length + 1;
        return (int)(times[length] % 1000_000_007);
    }

    public static void main(String[] args) {
        System.out.println(numSub("1"));
        System.out.println(numSub("0"));
        System.out.println(numSub("01"));
        System.out.println(numSub("11"));
        Utils.printArrays(times);
    }

}
