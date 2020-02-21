package com.leetcode.integers;

import com.leetcode.integers.RomanToInteger;

/**
 * @author wcl
 * @date 6:18 PM 2020/2/13
 * {@link "https://leetcode.com/problems/integer-to-roman/"}
 * @see RomanToInteger
 */
public class IntegerToRoman {
    /**
     * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
     *
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
     *
     * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
     *
     * I can be placed before V (5) and X (10) to make 4 and 9.
     * X can be placed before L (50) and C (100) to make 40 and 90.
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
     *
     * Example 1:
     *      Input: 3
     *      Output: "III"
     *
     * Example 2:
     *      Input: 4
     *      Output: "IV"
     *
     * Example 3:
     *      Input: 9
     *      Output: "IX"
     *
     * Example 4:
     *      Input: 58
     *      Output: "LVIII"
     *      Explanation: L = 50, V = 5, III = 3.
     *
     * Example 5:
     *      Input: 1994
     *      Output: "MCMXCIV"
     *      Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
     *
     * Runtime: 4 ms, faster than 86.83% of Java online submissions for Integer to Roman.
     * Memory Usage: 41.2 MB, less than 11.25% of Java online submissions for Integer to Roman.
     */
    public static String intToRoman1(int num) {

        int[] integers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < integers.length; i++) {
            int numeral = num / integers[i];
            for (int j = 0; j < numeral; j++) {
                result.append(romans[i]);
            }
            num = num % integers[i];
        }

        return result.toString();
    }

    public static String intToRoman(int num) {

        String[] romans = {"I", "X", "C", "M"};
        StringBuilder result = new StringBuilder();

        for (int i = 3; i >= 0; i--) {
            int pow = (int)Math.pow(10, i);
            if(num > pow) {
                int numeral = num / pow;
                if(numeral == 9) {
                    result.append(romans[i]).append(romans[i+1]);
                } else if(numeral == 4) {
                    result.append(romans[i]).append(getRoman(pow * 5));
                } else {
                    if(numeral >= 5) {
                        result.append(getRoman(pow * 5));
                        numeral = numeral - 5;
                    }
                    for (int j = 0; j < numeral; j++) {
                        result.append(romans[i]);
                    }
                }

                num = num % pow;
            }

        }

        return result.toString();
    }

    public static String getRoman(int num) {
        switch (num) {
            case 500:
                return "D";
            case 50:
                return "L";
            case 5:
                return "V";
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("III".equals(intToRoman(3)));
        System.out.println("IV".equals(intToRoman(4)));
        System.out.println("IX".equals(intToRoman(9)));
        System.out.println("LVIII".equals(intToRoman(58)));
        System.out.println(intToRoman(58));
        System.out.println("MCMXCIV".equals(intToRoman(1994)));
    }
}
