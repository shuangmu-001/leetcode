package com.question.day04;

import com.Test;
import com.Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/maximum-subarray/">maximum subarray</a>
 *
 * @author wcl
 * @date 7:46 下午 2021/9/19
 */
public class Code02SubArrayMaxSum implements Test {

    // 返回一个数组中，子数组最大累加和
    public int maxSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : arr) {
            sum = Math.max(num, sum + num);
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
