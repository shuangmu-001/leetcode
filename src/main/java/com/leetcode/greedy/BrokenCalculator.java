package com.leetcode.greedy;

/**
 * @author zms
 * @date 9:34 上午 2021/2/22
 * <a href="https://leetcode.com/problems/broken-calculator/">Broken Calculator</a>
 */
public class BrokenCalculator {
    /**
     * On a broken calculator that has a number showing on its display, we can perform two operations:
     * <p>
     * Double: Multiply the number on the display by 2, or;
     * Decrement: Subtract 1 from the number on the display.
     * Initially, the calculator is displaying the number X.
     * <p>
     * Return the minimum number of operations needed to display the number Y.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: X = 2, Y = 3
     * Output: 2
     * Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.
     * Example 2:
     * <p>
     * Input: X = 5, Y = 8
     * Output: 2
     * Explanation: Use decrement and then double {5 -> 4 -> 8}.
     * Example 3:
     * <p>
     * Input: X = 3, Y = 10
     * Output: 3
     * Explanation:  Use double, decrement and double {3 -> 6 -> 5 -> 10}.
     * Example 4:
     * <p>
     * Input: X = 1024, Y = 1
     * Output: 1023
     * Explanation: Use decrement operations 1023 times.
     * <p>
     * <p>
     * Note:
     * <p>
     * 1 <= X <= 10^9
     * 1 <= Y <= 10^9
     */
    // TODO 溢出
    public static int brokenCalc1(int X, int Y) {
        if (X >= Y) {
            return X - Y;
        }
        long x = X;
        int res = 0;
        while (x < (long) Y) {
            x *= 2;
            res++;
        }
        System.out.println(res);
        int count = (int) (x - (long) Y);
        System.out.println(count);
        return res + count / res + count % res;
    }

    public static int brokenCalc(int X, int Y) {

        int ans = 0;
        while (Y > X) {
            ans++;
            if (Y % 2 == 1) {
                Y++;
            } else {
                Y /= 2;
            }
        }

        return ans + X - Y;
    }

    public static void main(String[] args) {
        System.out.println(brokenCalc(1024, 1) == 1023);
        System.out.println(brokenCalc(3, 10) == 3);
        System.out.println(brokenCalc(5, 8) == 2);

        System.out.println(brokenCalc1(1, 10_0000_0000));
    }

}
