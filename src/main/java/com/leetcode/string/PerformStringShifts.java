package com.leetcode.string;

/**
 * @author wcl
 * @date 11:01 PM 2020/4/14
 * <a href="https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/529/week-2/3299/">
 * Perform String Shifts</a>
 */
public class PerformStringShifts {
    /**
     * You are given a string s containing lowercase English letters, and a matrix shift, where shift[i] = [direction, amount]:
     *
     * direction can be 0 (for left shift) or 1 (for right shift).
     * amount is the amount by which string s is to be shifted.
     * A left shift by 1 means remove the first character of s and append it to the end.
     * Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
     * Return the final string after all operations.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "abc", shift = [[0,1],[1,2]]
     * Output: "cab"
     * Explanation:
     * [0,1] means shift to left by 1. "abc" -> "bca"
     * [1,2] means shift to right by 2. "bca" -> "cab"
     * Example 2:
     *
     * Input: s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
     * Output: "efgabcd"
     * Explanation:
     * [1,1] means shift to right by 1. "abcdefg" -> "gabcdef"
     * [1,1] means shift to right by 1. "gabcdef" -> "fgabcde"
     * [0,2] means shift to left by 2. "fgabcde" -> "abcdefg"
     * [1,3] means shift to right by 3. "abcdefg" -> "efgabcd"
     *
     *
     * Constraints:
     * 1 <= s.length <= 100
     * s only contains lower case English letters.
     * 1 <= shift.length <= 100
     * shift[i].length == 2
     * 0 <= shift[i][0] <= 1
     * 0 <= shift[i][1] <= 100
     */
    public static String stringShift(String s, int[][] shift) {
        int shiftLen = 0;
        for (int[] ints : shift) {
            shiftLen += (ints[0] == 1 ? ints[1] : -ints[1]);
        }
        int sLen = s.length();
        shiftLen = shiftLen % sLen;
        StringBuilder builder = new StringBuilder();
        if(shiftLen < 0) {
            builder.append(s, -shiftLen, sLen)
                    .append(s, 0, -shiftLen);
        } else {
            builder.append(s, sLen - shiftLen, sLen)
                    .append(s, 0, sLen - shiftLen);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(stringShift("abcdefg", new int[][]{{1,1},{1,1},{0,2},{1,3}}));
        System.out.println(stringShift("abc", new int[][]{{0,1},{1,2}}));
    }

}
