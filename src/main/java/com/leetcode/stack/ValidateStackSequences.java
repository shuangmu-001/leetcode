package com.leetcode.stack;

import java.util.Stack;

/**
 * @author zms
 * @date 10:26 上午 2021/2/27
 * <a href="https://leetcode.com/problems/validate-stack-sequences/">
 * Validate Stack Sequences</a>
 */
public class ValidateStackSequences {

    /**
     * Given two sequences pushed and popped with distinct values,
     * return true if and only if this could have been the result of
     * a sequence of push and pop operations on an initially empty stack.
     * <p>
     * Example 1:
     * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * Output: true
     * Explanation: We might do the following sequence:
     * push(1), push(2), push(3), push(4), pop() -> 4,
     * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     * <p>
     * Example 2:
     * <p>
     * Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
     * Output: false
     * Explanation: 1 cannot be popped before 2.
     * <p>
     * Constraints:
     * 0 <= pushed.length == popped.length <= 1000
     * 0 <= pushed[i], popped[i] < 1000
     * pushed is a permutation of popped.
     * pushed and popped have distinct values.
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int i = 0, j = 0;
        while (i < pushed.length) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && j < popped.length && stack.peek() == popped[j]) {
                j++;
                stack.pop();
            }
            i++;
        }

        return j == popped.length;
    }
}
