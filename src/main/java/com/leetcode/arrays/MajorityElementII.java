package com.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zms
 * @date 3:13 下午 2020/9/22
 * <a href="https://leetcode.com/problems/majority-element-ii/">Majority Element II</a>
 */
public class MajorityElementII {
    /**
     * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
     *
     * Note: The algorithm should run in linear time and in O(1) space.
     *
     * Example 1:
     *
     * Input: [3,2,3]
     * Output: [3]
     * Example 2:
     *
     * Input: [1,1,1,3,3,2,2,2]
     * Output: [1,2]
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        int target = nums.length / 3;
        Arrays.sort(nums);
        for (int index = 0, i = 1; i < nums.length; i++) {
            if(nums[index] != nums[i]) {
                if(i - index > target) {
                    res.add(nums[index]);
                }
                index = i;
            }
            if(i == nums.length - 1) {
                if(i - index >= target) {
                    res.add(nums[i]);
                }
            }
        }
        return res;
    }
}
