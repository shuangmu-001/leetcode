package com.leetcode.string;

/**
 * @author zms
 * @date 2:32 PM 2020/5/6
 * <a href="https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/">
 *     Max Difference You Can Get From Changing an Integer</a>
 */
public class MaxDifferenceYouCanGetFromChangingAnInteger {
    /**
     * You are given an integer num. You will apply the following steps exactly two times:
     *
     * Pick a digit x (0 <= x <= 9).
     * Pick another digit y (0 <= y <= 9). The digit y can be equal to x.
     * Replace all the occurrences of x in the decimal representation of num by y.
     * The new integer cannot have any leading zeros, also the new integer cannot be 0.
     * Let a and b be the results of applying the operations to num the first and second times, respectively.
     *
     * Return the max difference between a and b.
     *
     *
     *
     * Example 1:
     *
     * Input: num = 555
     * Output: 888
     * Explanation: The first time pick x = 5 and y = 9 and store the new integer in a.
     * The second time pick x = 5 and y = 1 and store the new integer in b.
     * We have now a = 999 and b = 111 and max difference = 888
     * Example 2:
     *
     * Input: num = 9
     * Output: 8
     * Explanation: The first time pick x = 9 and y = 9 and store the new integer in a.
     * The second time pick x = 9 and y = 1 and store the new integer in b.
     * We have now a = 9 and b = 1 and max difference = 8
     * Example 3:
     *
     * Input: num = 123456
     * Output: 820000
     * Example 4:
     *
     * Input: num = 10000
     * Output: 80000
     * Example 5:
     *
     * Input: num = 9288
     * Output: 8700
     *
     *
     * Constraints:
     *
     * 1 <= num <= 10^8
     */
    public static int maxDiff(int num) {
        String s = num + "";
        char[] chars = s.toCharArray();
        int index = 0;
        int length =chars.length;
        while(index < length && chars[index] == '9') {
            index++;
        }
        int a = index < length ? Integer.parseInt(s.replace(chars[index], '9')) : num;
        if(chars[0] != '1') {
            return a - Integer.parseInt(s.replace(chars[0], '1'));
        }
        index = 0;
        while(index < length && (chars[index] == '0' || chars[index] == '1')) {
            index++;
        }
        int b = index < length ? Integer.parseInt(s.replace(chars[index], '0')) : num;
        return a - b;
    }

    public static void main(String[] args) {
        System.out.println(maxDiff(555));
        System.out.println(maxDiff(10000));
        System.out.println(maxDiff(9288));
        System.out.println(maxDiff(123456));
        System.out.println(maxDiff(9));
        System.out.println(maxDiff(113));
    }
}
