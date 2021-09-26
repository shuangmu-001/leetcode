package com.leetcode.search;

import java.util.*;

/**
 * @author zms
 * @date 4:11 PM 2020/4/3
 * <a href="https://leetcode.com/problems/intersection-of-two-arrays/">
 *     Intersection of Two Arrays</a>
 */
public class IntersectionOfTwoArrays {
    /**
     * Given two arrays, write a function to compute their intersection.
     *
     * Example 1:
     *
     * Input: nums1 = [1,2,2,1], nums2 = [2,2]
     * Output: [2]
     * Example 2:
     *
     * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * Output: [9,4]
     * Note:
     *
     * Each element in the result must be unique.
     * The result can be in any order.
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums = new HashSet<>();
        for (int value : nums1) {
            nums.add(value);
        }
        Set<Integer> result = new HashSet<>();
        for (int value : nums2) {
            if (nums.contains(value)) {
                result.add(value);
            }
        }
        int[] res = new int[result.size()];
        int index = 0;
        for (Integer integer : result) {
            res[index++] = integer;
        }
        return res;
    }
}
