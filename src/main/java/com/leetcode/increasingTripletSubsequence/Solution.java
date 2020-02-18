package com.leetcode.increasingTripletSubsequence;

/**
 * @author wcl
 * @date 3:13 PM 2020/1/2
 * {@link <https://leetcode.com/problems/increasing-triplet-subsequence/>}
 */
public class Solution {
    /**
     * Given an unsorted array return whether an increasing subsequence
     * of length 3 exists or not in the array.
     *
     * Formally the function should:
     *  Return true if there exists i, j, k
     *  such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
     *
     * Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
     *
     * Example 1:
     *  Input: [1,2,3,4,5]
     *  Output: true
     *
     * Example 2:
     *  Input: [5,4,3,2,1]
     *  Output: false
     *
     * @param nums an unsorted array
     * @return boolean
     */
    public static boolean increasingTriplet(int[] nums) {
        if(nums == null || nums.length < 3) {
            return false;
        }
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num > second) {
                return true;
            } else if (num < second) {
                second = num;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4,5};
        System.out.println(increasingTriplet(nums1));
        int[] nums2 = {5,4,3,2,1};
        System.out.println(increasingTriplet(nums2));
    }
}
