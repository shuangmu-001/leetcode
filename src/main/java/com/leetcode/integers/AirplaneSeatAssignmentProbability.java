package com.leetcode.integers;

/**
 * @author zms
 * @date 12:12 PM 2020/2/24
 * {@link "https://leetcode.com/problems/airplane-seat-assignment-probability/"}
 */
public class AirplaneSeatAssignmentProbability {
    /**
     * n passengers board an airplane with exactly n seats. The first passenger has lost the ticket and picks a seat randomly.
     * But after that, the rest of passengers will:
     *      Take their own seat if it is still available,
     *      Pick other seats randomly when they find their seat occupied
     * What is the probability that the n-th person can get his own seat?
     *
     * Example 1:
     *      Input: n = 1
     *      Output: 1.00000
     *      Explanation: The first person can only get the first seat.
     *
     * Example 2:
     *      Input: n = 2
     *      Output: 0.50000
     *      Explanation: The second person has a probability of 0.5 to get the second seat (when first person gets the first seat).
     *
     * Constraints:1 <= n <= 10^5
     */
    public static double nthPersonGetsNthSeat(int n) {
        if(n == 1) {
            return 1;
        }
        return 0.5;
    }

    public static void main(String[] args) {
        System.out.println(nthPersonGetsNthSeat(1) == 1.00000);
        System.out.println(nthPersonGetsNthSeat(2) == 0.50000);
    }
}
