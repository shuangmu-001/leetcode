package com.leetcode.math;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wcl
 * @date 4:54 PM 2020/4/2
 * <a href="https://leetcode.com/problems/happy-number/">
 *     Happy Number</a>
 */
public class HappyNumber {
    /**
     * Write an algorithm to determine if a number is "happy".
     * A happy number is a number defined by the following process:
     * Starting with any positive integer, replace the number by the sum of the squares of its digits,
     * and repeat the process until the number equals 1 (where it will stay),
     * or it loops endlessly in a cycle which does not include 1.
     * Those numbers for which this process ends in 1 are happy numbers.
     *
     * Example:
     *      Input: 19
     *      Output: true
     *      Explanation:
     *      1^2 + 9^2 = 82
     *      8^2 + 2^2 = 68
     *      6^2 + 8^2 = 100
     *      1^2 + 0^2 + 0^2 = 1
     */
    static Set<Integer> map = new HashSet<>();
    public static boolean isHappy(int n) {
        if(n == 1) {
            return true;
        }

        int sum = 0;
        while(n >0) {
            int h = n % 10;
            n /= 10;
            sum += h * h;
        }
        if(map.contains(sum)) {
            return false;
        }
        map.add(sum);
        return isHappy(sum);
    }

    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }
}
