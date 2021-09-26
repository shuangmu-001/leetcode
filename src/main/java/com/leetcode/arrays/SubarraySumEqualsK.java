package com.leetcode.arrays;

import java.util.*;

/**
 * @author zms
 * @date 3:32 PM 2020/4/22
 * <a href="https://leetcode.com/problems/subarray-sum-equals-k/">
 *     Subarray Sum Equals K</a>
 */
public class SubarraySumEqualsK {
    /**
     * Given an array of integers and an integer k,
     * you need to find the total number of continuous subarrays whose sum equals to k.
     *
     * Example 1:
     * Input:nums = [1,1,1], k = 2
     * Output: 2
     * Note:
     * The length of the array is in range [1, 20,000].
     * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
     */
    public int subarraySum1(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int length = nums.length;
        if( length == 1) {
            return nums[0] == k ? 1 : 0;
        }

        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            for (int j = i; j < nums.length; j++) {

                sum = i == j ? sum : sum + nums[j];
                if(sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int subarraySum2(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
    // TODO
    public static int subarraySum(int[] a, int k) {
        int s = 0, ret = 0, tmp, mn=1<<30, mx=-1<<30;
        for(int i : a) {
            s += i;
            if(s > mx) mx = s;
            if(s < mn) mn = s;
        }

        int []hash = new int[mx-mn+1];
        s = 0;
        for(int i : a) {
            s += i;
            if(s == k) ++ret;
            tmp = s-k;
            if(tmp >= mn && tmp <= mx)
                ret += hash[tmp - mn];
            ++hash[s-mn];
        }
        return ret;
    }

    public static void main(String[] args) {
//        System.out.println(subarraySum(new int[]{1,1,2}, 2));
        System.out.println(subarraySum(new int[]{-1,-1,1}, 0));
    }
}
