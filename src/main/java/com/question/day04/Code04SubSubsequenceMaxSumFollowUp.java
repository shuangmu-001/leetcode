package com.question.day04;


/**
 * @author zms
 * @date 7:45 下午 2021/9/19
 */
public class Code04SubSubsequenceMaxSumFollowUp {

    // 返回一个数组中，选择的数字不能相邻的情况下，最大子序列累加和
    public int subSqeMaxSumNoNext(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int length = arr.length;
        if (length == 1) {
            return arr[0];
        }

        int first = arr[0];
        int second = Math.max(first, arr[1]);
        for (int i = 3; i < length; i++) {
            int temp = first;
            first = second;
            // dp[n - 1], arr[n] , dp[n - 2] + arr[n]
            // dp[n - 2] <= dp[n - 1]
            second = Math.max(Math.max(second, arr[i]), temp + arr[i]);
        }
        return second;
    }
}
