package com.leetcode.dp.game;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 7:48 PM 2020/2/25
 * {@link "https://leetcode.com/problems/divisor-game/"}
 */
public class DivisorGame {
    /**
     * Alice and Bob take turns playing a game, with Alice starting first.
     * Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:
     *      Choosing any x with 0 < x < N and N % x == 0.
     *      Replacing the number N on the chalkboard with N - x.
     * Also, if a player cannot make a move, they lose the game.
     * Return True if and only if Alice wins the game, assuming both players play optimally.
     *
     * Example 1:
     *      Input: 2
     *      Output: true
     *      Explanation: Alice chooses 1, and Bob has no more moves.
     *
     * Example 2:
     *      Input: 3
     *      Output: false
     *      Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
     *
     * Note:1 <= N <= 1000
     *
     */
    public static boolean divisorGame(int N) {
        if(N == 1) {
            return false;
        }

        if(N == 2) {
            return true;
        }
        // 如果 A
        int[] dp = new int[N + 1];
        dp[1] = -1;
        dp[2] = 1;
        for (int i = 3; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if(i % j == 0 && dp[i - j] == 0) {
                    dp[i] = 1;
                    break;
                }
            }
            if(dp[i] == 0) {
                // 奇数和偶数的关系 --> (N & 1) == 0
                dp[i] = -dp[i - 1];
            }
        }
        return dp[N] == 1;
    }

    public static void main(String[] args) {
        System.out.println(divisorGame(100));
        System.out.println(!divisorGame(101));
    }
}
