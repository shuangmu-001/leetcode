package com.leetcode.search;

import java.util.*;

/**
 * @author wcl
 * @date 6:40 PM 2020/4/3
 * <a href="https://leetcode.com/problems/intersection-of-two-arrays-ii/">
 *     Intersection of Two Arrays II</a>
 */
public class IntersectionOfTwoArraysII {
    /**
     * Given two arrays, write a function to compute their intersection.
     *
     * Example 1:
     *
     * Input: nums1 = [1,2,2,1], nums2 = [2,2]
     * Output: [2,2]
     * Example 2:
     *
     * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * Output: [4,9]
     * Note:
     *
     * Each element in the result should appear as many times as it shows in both arrays.
     * The result can be in any order.
     * Follow up:
     *
     * What if the given array is already sorted? How would you optimize your algorithm?
     * What if nums1's size is small compared to nums2's size? Which algorithm is better?
     * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
     */
    public int[] intersect1(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.merge(num, 1, Integer::sum);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if(map.containsKey(num) && map.get(num) > 0) {
                map.merge(num, -1, Integer::sum);
                list.add(num);
            }
        }
        int[] res = new int[list.size()];
        int index = 0;
        for (int num : list) {
            res[index++] = num;
        }
        return res;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int first = 0;
        int second = 0;
        int resLen = 0;
        while(first < nums1.length && second < nums2.length) {
            if(nums1[first] < nums2[second]) {
                first++;
            } else if(nums1[first] > nums2[second]) {
                second++;
            } else {
                nums1[resLen++] = nums2[second];
                first++;
                second++;
            }
        }
        return Arrays.copyOfRange(nums1, 0, resLen);
    }
}
