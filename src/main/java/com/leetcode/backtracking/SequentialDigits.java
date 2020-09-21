package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 10:15 下午 2020/9/19
 * <a href="https://leetcode.com/problems/sequential-digits/">
 *     Sequential Digits</a>
 */
public class SequentialDigits {
    /**
     * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
     *
     * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
     *
     * Example 1:
     * Input: low = 100, high = 300
     * Output: [123,234]
     *
     * Example 2:
     * Input: low = 1000, high = 13000
     * Output: [1234,2345,3456,4567,5678,6789,12345]
     *
     * Constraints: 10 <= low <= high <= 10^9
     */
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        int bit = 0;
        int first = low;
        while(first >= 10) {
            bit++;
            first /= 10;
        }
        helper(low, high, bit, first, res);
        return res;
    }

    private void helper(int low, int high, int bit, int first, List<Integer> res) {
        if(bit >= 9) {
            return;
        }
        if(first + bit > 9) {
            helper(low, high, bit + 1, 1, res);
        } else {
            int num = first;
            int before = first;
            for (int i = 0; i < bit; i++) {
                num = num * 10 + (before + 1);
                before++;
            }
            if(num > high) {
                return;
            }
            if(num >= low && num <= high) {
                res.add(num);
            }
            helper(low, high, bit, first + 1, res);
        }
    }

    public static void main(String[] args) {
        System.out.println(new SequentialDigits().sequentialDigits(100, 300));
        System.out.println(new SequentialDigits().sequentialDigits(1000, 13000));
        System.out.println(new SequentialDigits().sequentialDigits(10,1_000_000_000));
    }
}
