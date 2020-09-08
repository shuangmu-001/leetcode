package com.leetcode.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wcl
 * @date 9:19 PM 2020/2/14
 */
public class TwoSum {
    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     *
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     *
     * Example:
     *
     * Given nums = [2, 7, 11, 15], target = 9,
     *
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     */
    public static int[] twoSum(int[] nums, int target) {
//        int[] results = new int[2];
//        int length = nums.length;
//        for(int i = 0; i < length; i ++) {
//            for(int j = i + 1; j < length; j++) {
//                if(nums[i] + nums[j] == target) {
//                    results[0] = i;
//                    results[1] = j;
//                }
//            }
//        }
//        return results;
//        int[] result = new int[2];
//        Map<Integer, Integer> helper = new HashMap<>();
//
//        for (int i = 0; i < nums.length; i++) {
//            if (helper.containsKey(nums[i])) {
//                result[0] = helper.get(nums[i]);
//                result[1] = i;
//
//                break;
//            } else {
//                helper.put(target - nums[i], i);
//            }
//        }
//
//        return result;

        int[] arr=new int[8192];
        int max=arr.length-1;
        int first=nums[0];
        for(int i=1;i<nums.length;i++){
            int diff=target-nums[i];
            if(first==diff){
                return new int[]{0,i};
            }
            int index=arr[diff&max];
            if(index!=0){
                return new int[]{index,i};
            }
            arr[nums[i]&max]=i;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(twoSum(nums, target)[0]);
        System.out.println(twoSum(nums, target)[1]);
    }
}
