package com.leetcode.bit;

import com.Utils;

/**
 * @author wcl
 * @date 5:22 PM 2020/4/23
 * <a href="https://leetcode.com/problems/majority-element/">
 *     Majority Element</a>
 * TODO 多数投票算法（Boyer-Moore Voting Algorithm）
 */
public class MajorityElement {
    /**
     * Given an array of size n, find the majority element.
     * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
     * You may assume that the array is non-empty and the majority element always exist in the array.
     *
     * Example 1:
     *
     * Input: [3,2,3]
     * Output: 3
     * Example 2:
     *
     * Input: [2,2,1,1,1,2,2]
     * Output: 2
     */
    public static int majorityElement(int[] nums) {
        int len = nums.length;
        if(len == 1) {
            return nums[0];
        }
        int[] element = new int[len + 1];
        int mid = len >> 1;
        element[0] = nums[0];
        element[mid + 1] = 1;
        int res = nums[0];
        int count = 1;
        int index = 1;

        boolean flag = true;
        for (int i = 1; i < len; i++) {

            for (int j = 0; j < index; j++) {
                if(nums[i] == element[j]) {
                    element[j + mid + 1] += 1;
                    flag = false;
                    if(element[j + mid + 1] > count) {
                        count = element[j + mid + 1];
                        res = element[j];
                    }
                    break;
                }
            }
            if(flag) {
                element[index] = nums[i];
                element[index + mid + 1] = 1;
                index++;
            }
            flag = true;
        }
        Utils.printArrays(element);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{2,2,1,1,1,2,2}) == 2);
        System.out.println(majorityElement(new int[]{3,2,3}) == 3);
    }
}
