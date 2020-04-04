package com.leetcode.search;

/**
 * @author wcl
 * @date 5:21 PM 2020/4/3
 * <a href="https://leetcode.com/problems/valid-perfect-square/">
 *     Valid Perfect Square</a>
 */
public class ValidPerfectSquare {
    /**
     * Given a positive integer num, write a function which returns True if num is a perfect square else False.
     * Note: Do not use any built-in library function such as sqrt.
     *
     * Example 1:
     *
     * Input: 16
     * Output: true
     * Example 2:
     *
     * Input: 14
     * Output: false
     */
    public boolean isPerfectSquare(int num) {
        if(num <= 1) {
            return true;
        }
        long left = 1;
        long right = num;
        while(left <= right) {
            long mid = (right + left) / 2;
            long sq = mid * mid;
            if(sq == num) {
                return true;
            } else if(sq > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
