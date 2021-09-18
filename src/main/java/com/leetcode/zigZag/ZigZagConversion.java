package com.leetcode.zigZag;

import com.Utils;

/**
 * @author wcl
 * @date Create in 3:16 下午 2019/11/27
 * {@link "https://leetcode.com/problems/zigzag-conversion/"}
 */
public class ZigZagConversion {
    /**
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
     * (you may want to display this pattern in a fixed font for better legibility)
     *
     *              P   A   H   N
     *              A P L S I I G
     *              Y   I   R
     *
     * And then read line by line: "PAHNAPLSIIGYIR"
     * Write the code that will take a string and make this conversion given a number of rows:
     * string convert(string s, int numRows);
     *
     * Example 1:
     *
     *  Input: s = "PAYPALISHIRING", numRows = 3
     *  Output: "PAHNAPLSIIGYIR"
     *
     * Example 2:
     *
     *  Input: s = "PAYPALISHIRING", numRows = 4
     *  Output: "PINALSIGYAHRPI"
     *  Explanation:
     *
     *          P     I    N
     *          A   L S  I G
     *          Y A   H R
     *          P     I
     * Runtime: 2 ms, faster than 100.00% of Java online submissions for ZigZag Conversion.
     * Memory Usage: 41.3 MB, less than 22.34% of Java online submissions for ZigZag Conversion.
     */
    private static String convert(String s, int numRows) {
        int length = s.length();
        if(length == 0 || s.length() <= numRows || numRows == 1) {
            return s;
        }
        // 以numRows - 1为单元
        int c = numRows - 1;
        char[] chars = new char[length];
        // 每行开始的索引
        int[] num = new int[numRows + 1];
        int target;
        // j 表示行数
        for(int i = 0,j = 1; i < length; i++) {
            // 第几行的第几个数
            int h = i - num[j - 1];
            if( j != 1 && j!= numRows) {
                if((h & 1) !=0) {
                    target = h * c + c - j + 1;
                } else {
                    target = h * c + j - 1;
                }
            } else {
                target = h * 2 * c + j - 1;
            }
            if(target >= length) {
                num[j] = i;
                i--;
                j++;
            } else {
                chars[i] = s.charAt(target);
            }

        }
        Utils.printArrays(num);
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
        System.out.println(convert("PAYPALISHIRING", 4));
        System.out.println("PAYPALISHIRING".length());
        // 14 / 3 = 2; 14 / 2
    }

}
