package com.leetcode.search;

/**
 * @author wcl
 * @date 5:28 PM 2020/4/3
 * <a href="https://leetcode.com/problems/arranging-coins/">
 *     Arranging Coins</a>
 */
public class ArrangingCoins {
    /**
     * You have a total of n coins that you want to form in a staircase shape,
     * where every k-th row must have exactly k coins.
     *
     * Given n, find the total number of full staircase rows that can be formed.
     *
     * n is a non-negative integer and fits within the range of a 32-bit signed integer.
     *
     * Example 1:
     *
     * n = 5
     *
     * The coins can form the following rows:
     * ¤
     * ¤ ¤
     * ¤ ¤
     *
     * Because the 3rd row is incomplete, we return 2.
     * Example 2:
     *
     * n = 8
     *
     * The coins can form the following rows:
     * ¤
     * ¤ ¤
     * ¤ ¤ ¤
     * ¤ ¤
     *
     * Because the 4th row is incomplete, we return 3.
     */
    public static int arrangeCoins(int n) {
        long left = 1;
        long right = n;
        while(left <= right) {
            long mid = (left + right) / 2;
            long sum;
            if((mid & 1) == 0) {
                sum = (mid / 2) * (mid + 1);
            } else {
                sum = mid * (mid + 1) / 2;
            }
            if(sum == n) {
                return (int)mid;
            } else if(sum < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int)right;
    }

    public static void main(String[] args) {
//        System.out.println(arrangeCoins(1) == 1);
//        System.out.println(arrangeCoins(2) == 1);
//        System.out.println(arrangeCoins(3) == 2);
//        System.out.println(arrangeCoins(4) == 2);
        System.out.println(arrangeCoins(199998389) == 19999);
    }

}
