package com.leetcode.dp.fibonacci;

import com.Utils;

/**
 * <a href="https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/">Non-negative Integers without Consecutive Ones</a>
 *
 * @author zms
 * @date 6:29 下午 2021/12/1
 * @see HouseRobber
 * @see HouseRobberII
 */
public class NonnegativeIntegersWithoutConsecutiveOnes {

    /**
     * Given a positive integer n, return the number of the integers in the range [0, n]
     * whose binary representations do not contain consecutive ones.
     * <p>
     * Example 1:
     * Input: n = 5
     * Output: 5
     * Explanation:
     * Here are the non-negative integers <= 5 with their corresponding binary representations:
     * 0 : 0
     * 1 : 1
     * 2 : 10
     * 3 : 11
     * 4 : 100
     * 5 : 101
     * Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
     * <p>
     * Example 2:
     * Input: n = 1
     * Output: 2
     * <p>
     * Example 3:
     * Input: n = 2
     * Output: 3
     * <p>
     * Constraints:
     * 1 <= n <= 10^9
     */
    public static int findIntegers(int n) {
        if (n == 0) {
            return 1;
        }
        //目的是： 最高位选择1的时候的可能性，用到
        int[] positions = onePosition(n);
        // 转化成二进制的时候最高位的位置
        int position = positions[0];
        // 当前位置不选1的可能性
        int[] no = new int[position];
        // 当前位置选择1的可能性
        int[] yes = new int[position];
        no[0] = 1;
        yes[0] = 1;
        for (int i = 1; i < position; i++) {
            no[i] = yes[i - 1] + no[i - 1];
            yes[i] = no[i - 1];
        }
        // 最高位选择1要单独求解，目的是让选择之后的数值小于 n
        yes[position - 1] = 1;
        // 最高位选择1的时候需要 第二个1到第一个1中间只能是0，第二个1的位置选1或不选1两种情况的和，
        // 第二个1的位置选择1 需要第三个1的位置来决定选择1还是不选1；
        // ...
        // 最一个1选择了就1种情况 n这个数本身，
        // 当中间有两个1位置相邻，递归就结束了，
        for (int i = 1; i < 32 && positions[i] != 0; i++) {
            // n这个数本身也是一种可能
            // 每一个1的位置都可以不选择
            yes[position - 1] += no[positions[i] - 1];
            if (positions[i - 1] - positions[i] == 1) {
                yes[position - 1]--;
                break;
            }
        }
        return no[position - 1] + yes[position - 1];
    }

    /**
     * @param n 正整数
     * @return 将正整数转化成二进制所有1的位置
     */
    public static int[] onePosition(int n) {
        int[] positions = new int[32];
        int index = 0;
        while (n != 0) {
            // Integer.numberOfLeadingZeros n 前面有多少个0
            // 最高位1的位置
            int position = 32 - Integer.numberOfLeadingZeros(n);
            positions[index++] = position;
            // 让下一个1的位置变成最高位
            n -= Integer.highestOneBit(n);
        }
        return positions;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(101183));
        Utils.printArrays(onePosition(101183));
        System.out.println(findIntegers(0) == 1);
        System.out.println(findIntegers(1) == 2);
        System.out.println(findIntegers(2) == 3);
        System.out.println(findIntegers(3) == 3);
        System.out.println(findIntegers(4) == 4);
        System.out.println(findIntegers(5) == 5);
        System.out.println(findIntegers(64) == 22);
        System.out.println(findIntegers(83) == 32);
        System.out.println(findIntegers(101183));
    }
}
