package com.leetcode.search;

/**
 * @author zms
 * @date 5:07 PM 2020/4/3
 * <a href="https://leetcode.com/problems/guess-number-higher-or-lower/">
 *     Guess Number Higher or Lower</a>
 */
public class GuessNumberHigherOrLower {
    /**
     * We are playing the Guess Game. The game is as follows:
     *
     * I pick a number from 1 to n. You have to guess which number I picked.
     *
     * Every time you guess wrong, I'll tell you whether the number is higher or lower.
     *
     * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
     *
     * -1 : My number is lower
     *  1 : My number is higher
     *  0 : Congrats! You got it!
     * Example :
     *
     * Input: n = 10, pick = 6
     * Output: 6
     */
    public static int guessNumber(int n) {
        long left = 0;
        long right = n;
        while(left <= right) {
            int mid = (int)((left + right) / 2);
            if(guess(mid) == 0) {
                return mid;
            } else if(guess(mid) == -1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return n;
    }

    /**
     * Forward declaration of guess API.
     * @param  num   your guess
     * @return 	     -1 if num is lower than the guess number
     *			      1 if num is higher than the guess number
     *               otherwise return 0
     * int guess(int num);
     */
    static int target = 1702766719;
    static int guess(int num) {
        return Integer.compare(num, target);
    }

    public static void main(String[] args) {
        System.out.println(guessNumber(2126753390));
    }
}
