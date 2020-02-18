package com.leetcode.sum;

import java.util.*;

/**
 * {@link "https://leetcode.com/problems/4sum/"}
 *
 * @author wcl
 * @date 11:10 AM 2020/2/17
 * @see ThreeSum
 * @see ThreeSumClosest
 */
public class FourSum {
    /**
     * Given an array nums of n integers and an integer target,
     * are there elements a, b, c, and d in nums such that a + b + c + d = target?
     * Find all unique quadruplets in the array which gives the sum of target.
     *
     * Note:
     *
     * The solution set must not contain duplicate quadruplets.
     *
     * Example:
     *      Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
     *      A solution set is:
     *          [
     *              [-1,  0, 0, 1],
     *              [-2, -1, 1, 2],
     *              [-2,  0, 0, 2]
     *          ]
     * Runtime: 5 ms, faster than 91.83% of Java online submissions for 4Sum.
     * Memory Usage: 40 MB, less than 62.32% of Java online submissions for 4Sum.
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;

        Set<List<Integer>> sets = new HashSet<>();
        for (int i = 0; i < len - 3; i++) {
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            // 最大比目标值还小
            if(nums[i] + nums[len - 1] * 3 < target) {
                continue;
            }
            // 最小比目标值还大
            if(nums[i] * 4 > target) {
                continue;
            }
            for (int j = i + 1; j < len - 2; j++) {

                int left = j + 1;
                int right = len - 1;

                int firstSum = nums[i] + nums[j];
                // 最大比目标值还小
                if(firstSum + nums[right] * 2 < target) {
                    continue;
                }
                // 最小比目标值还大
                if(firstSum + nums[left] * 2 > target) {
                    continue;
                }

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum == target) {
                        sets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                    } else if(sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            
        }
        return new ArrayList<>(sets);
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        System.out.println(fourSum(nums1, 0));
    }
}
