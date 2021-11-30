package com.leetcode.arrays;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/subarray-sum-equals-k/">Subarray Sum Equals K</a>
 *
 * @author zms
 * @date 3:32 PM 2020/4/22
 */
public class SubarraySumEqualsK {
    /**
     * Given an array of integers and an integer k,
     * you need to find the total number of continuous subarrays whose sum equals to k.
     * <p>
     * Example 1:
     * Input:nums = [1,1,1], k = 2
     * Output: 2
     * Note:
     * 1 <= nums.length <= 2 * 10^4
     * -1000 <= nums[i] <= 1000
     * -10^7 <= k <= 10^7
     */
    public int subarraySum1(int[] nums, int k) {
        int length = nums.length;
        if (length == 1) {
            return nums[0] == k ? 1 : 0;
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int subarraySum2(int[] nums, int k) {
        // sum 表示前缀和
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            // 有多少个前缀和是sum-k，则就有多少个子数组的和是k
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            // 将前缀和存储hash表中
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // 构建一个数组替换hash，数组的大小是前缀和最大值和最小值的距离
    public static int subarraySum(int[] a, int k) {
        int s = 0, ret = 0, tmp, mn = 1 << 30, mx = -1 << 30;
        for (int i : a) {
            s += i;
            if (s > mx) mx = s;
            if (s < mn) mn = s;
        }

        int[] hash = new int[mx - mn + 1];
        s = 0;
        for (int i : a) {
            s += i;
            if (s == k) ++ret;
            tmp = s - k;
            if (tmp >= mn && tmp <= mx)
                ret += hash[tmp - mn];
            ++hash[s - mn];
        }
        return ret;
    }

    public static void main(String[] args) {
//        System.out.println(subarraySum(new int[]{1,1,2}, 2));
        System.out.println(subarraySum(new int[]{-1, -1, 1}, 0));
    }
}
