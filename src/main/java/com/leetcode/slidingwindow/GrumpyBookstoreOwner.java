package com.leetcode.slidingwindow;

/**
 * @author wcl
 * @date 11:40 上午 2020/5/25
 * <a href="https://leetcode.com/problems/grumpy-bookstore-owner/">
 * Grumpy Bookstore Owner</a>
 */
public class GrumpyBookstoreOwner {
    /**
     * Today, the bookstore owner has a store open for customers.length minutes. 
     * Every minute, some number of customers (customers[i]) enter the store,
     * and all those customers leave after the end of that minute.
     *
     * On some minutes, the bookstore owner is grumpy. 
     * If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0. 
     * When the bookstore owner is grumpy, the customers of that minute are not satisfied,
     * otherwise they are satisfied.
     *
     * The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight,
     * but can only use it once.
     *
     * Return the maximum number of customers that can be satisfied throughout the day.
     *
     * Example 1:
     *
     * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
     * Output: 16
     * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
     * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
     *  
     * Note:
     * 1 <= X <= customers.length == grumpy.length <= 20000
     * 0 <= customers[i] <= 1000
     * 0 <= grumpy[i] <= 1
     */
    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int sum1 = 0;
        int length = customers.length;
        int sum2 = 0;
        int before = 0;
        for (int left = 0, right = 0; left < length && right < length; right++) {
            if(grumpy[right] == 0) {
                sum1 += customers[right];
            } else {
                if(right - left < X) {
                    sum2 += customers[right];
                    before = sum2;
                } else {
                    while(right - left >= X) {
                        if(grumpy[left] == 1) {
                            before -= customers[left];
                        }
                        left++;
                    }
                    before += customers[right];
                    sum2 = Math.max(sum2, before);
                }
            }
        }

        return sum1 + sum2;
    }

    public static void main(String[] args) {
        System.out.println(maxSatisfied(new int[]{1,10,1,2,1,1,7,5}, new int[]{0,1,0,1,0,1,0,1}, 3));
    }
}
