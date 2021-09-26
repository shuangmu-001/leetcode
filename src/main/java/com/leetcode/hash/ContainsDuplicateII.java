package com.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zms
 * @date 2:34 PM 2020/5/12
 * <a href="https://leetcode.com/problems/contains-duplicate-ii/">
 * Contains Duplicate II</a>
 */
public class ContainsDuplicateII {
    /**
     * Given an array of integers and an integer k,
     * find out whether there are two distinct indices
     * i and j in the array such that nums[i] = nums[j] and the absolute
     * difference between i and j is at most k.
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,1], k = 3
     * Output: true
     * Example 2:
     *
     * Input: nums = [1,0,1,1], k = 1
     * Output: true
     * Example 3:
     *
     * Input: nums = [1,2,3,1,2,3], k = 2
     * Output: false
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if(map.containsKey(nums[i])) {
                int minIndex = map.get(nums[i]);
                if (i - minIndex <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
