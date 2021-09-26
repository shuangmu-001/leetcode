package com.leetcode.arrays;

/**
 * @author zms
 * @date 4:46 PM 2020/2/23
 * TODO {@link "https://leetcode.com/problems/24-game/"}
 */
public class Game24 {
    /**
     * You have 4 cards each containing a number from 1 to 9.
     * You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
     * Example 1:
     *      Input: [4, 1, 8, 7]
     *      Output: True
     *      Explanation: (8-4) * (7-1) = 24
     *
     * Example 2:
     *      Input: [1, 2, 1, 2]
     *      Output: False
     * Note:
     *      The division operator / represents real division, not integer division.
     *          For example, 4 / (1 - 2/3) = 12.
     *      Every operation done is between two numbers. In particular, we cannot use - as a unary operator.
     *          For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
     *      You cannot concatenate numbers together.
     *          For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
     */
    public static boolean judgePoint24(int[] nums) {
        return true;
    }

    public static void main(String[] args) {

    }
}
