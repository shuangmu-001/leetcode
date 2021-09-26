package com.leetcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zms
 * @date 6:49 下午 2020/9/10
 * <a href="https://leetcode.com/problems/bulls-and-cows/">Bulls and Cows</a>
 */
public class BullsAndCows {
    /**
     * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.
     * <p>
     * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 
     * <p>
     * Please note that both secret number and friend's guess may contain duplicate digits.
     * <p>
     * Example 1:
     * <p>
     * Input: secret = "1807", guess = "7810"
     * <p>
     * Output: "1A3B"
     * <p>
     * Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
     * Example 2:
     * <p>
     * Input: secret = "1123", guess = "0111"
     * <p>
     * Output: "1A1B"
     * <p>
     * Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
     * Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
     */
    public String getHint(String secret, String guess) {
        int[] secrets = new int[10];
        int a = 0;
        int b = 0;
        int length = secret.length();
        for (int i = 0; i < length; i++) {
            char s = secret.charAt(i);
            secrets[s - '0']++;
        }
        //"1122" "1222"
        for (int i = 0; i < length; i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s == g) {
                a++;
            }
            if(secrets[g - '0'] > 0) {
                b++;
                secrets[g - '0']--;
            }
        }

        return a + "A" + (b - a) + "B";
    }
}
