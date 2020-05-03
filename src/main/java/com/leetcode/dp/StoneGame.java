package com.leetcode.dp;

import java.util.Collections;

/**
 * @author wcl
 * @date 10:18 AM 2020/2/28
 * TODO {@link "https://leetcode.com/problems/stone-game/"}
 */
public class StoneGame {
    /**
     * Alex and Lee play a game with piles of stones.
     * There are an even number of piles arranged in a row,
     * and each pile has a positive integer number of stones piles[i].
     * The objective of the game is to end with the most stones.
     * The total number of stones is odd, so there are no ties.
     * Alex and Lee take turns, with Alex starting first.
     * Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.
     * This continues until there are no more piles left, at which point the person with the most stones wins.
     * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
     *
     * Example 1:
     *      Input: [5,3,4,5]
     *      Output: true
     *      Explanation:
     *              Alex starts first, and can only take the first 5 or the last 5.
     *              Say he takes the first 5, so that the row becomes [3, 4, 5].
     *              If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
     *              If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
     *              This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
     * Note:
     *      2 <= piles.length <= 500
     *      piles.length is even.
     *      1 <= piles[i] <= 500
     *      sum(piles) is odd.
     *
     */
    public static boolean stoneGame(int[] piles) {
        int length = piles.length;
        if(length == 2) {
            return true;
        }
        int left = 0;
        int right = length - 1;
        int aSum = 0;
        int lSum = 0;
        for (int i = 0; i < length; i++) {
            int max;
            // 3,2,10,4
            if(piles[left] > piles[right]) {
                max = piles[left];
                left++;
            } else {
                max = piles[right];
                right--;
            }
            if((i & 1) == 0) {
                aSum += max;
            } else {
                lSum += max;
            }
        }

        return aSum > lSum;
    }

    public static void main(String[] args) {
        System.out.println(stoneGame(new int[]{5,3,4,5}));
        System.out.println(stoneGame(new int[]{3,2,10,4}));
        System.out.println(stoneGame(new int[]{3,2,111,10,10,4}));
    }
}
