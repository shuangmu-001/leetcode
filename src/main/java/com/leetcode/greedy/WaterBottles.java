package com.leetcode.greedy;

/**
 * @author wcl
 * @date 4:27 下午 2020/7/24
 * <a href="https://leetcode.com/problems/water-bottles/">
 *     Water Bottles</a>
 * TODO 贪心
 */
public class WaterBottles {
    /**
     * Given numBottles full water bottles, you can exchange numExchange empty water bottles for one full water bottle.
     * The operation of drinking a full water bottle turns it into an empty bottle.
     * Return the maximum number of water bottles you can drink.
     *
     * Example 1:
     * Input: numBottles = 9, numExchange = 3
     * Output: 13
     * Explanation: You can exchange 3 empty bottles to get 1 full water bottle.
     * Number of water bottles you can drink: 9 + 3 + 1 = 13.
     *
     * Example 2:
     * Input: numBottles = 15, numExchange = 4
     * Output: 19
     * Explanation: You can exchange 4 empty bottles to get 1 full water bottle.
     * Number of water bottles you can drink: 15 + 3 + 1 = 19.
     *
     * Example 3:
     * Input: numBottles = 5, numExchange = 5
     * Output: 6
     *
     * Example 4:
     * Input: numBottles = 2, numExchange = 3
     * Output: 2
     *
     * Constraints:
     * 1 <= numBottles <= 100
     * 2 <= numExchange <= 100
     */
    public static int numWaterBottles(int numBottles, int numExchange) {
        if(numExchange > numBottles) {
            return numBottles;
        }

        if(numExchange == numBottles) {
            return numBottles + 1;
        }
        int result = 0;
        int newBottles = numBottles;
        while(newBottles >= numExchange) {
            int a = newBottles / numExchange;
            int b= newBottles % numExchange;
            result += newBottles - b;
            newBottles = a + b;
        }
        return result + newBottles;
    }

    public static void main(String[] args) {
        System.out.println(numWaterBottles(15, 4));
    }
}
