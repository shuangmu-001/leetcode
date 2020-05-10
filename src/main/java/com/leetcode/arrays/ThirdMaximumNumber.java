package com.leetcode.arrays;


/**
 * @author wcl
 * @date 4:14 PM 2020/5/7
 * <a href="https://leetcode.com/problems/third-maximum-number/">
 *     Third Maximum Number</a>
 * long 或者 null 解决 数组中存在Integer.MIN_VALUE
 */
public class ThirdMaximumNumber {
    /**
     * Given a non-empty array of integers, return the third maximum number in this array.
     * If it does not exist, return the maximum number. The time complexity must be in O(n).
     *
     * Example 1:
     * Input: [3, 2, 1]
     *
     * Output: 1
     *
     * Explanation: The third maximum is 1.
     * Example 2:
     * Input: [1, 2]
     *
     * Output: 2
     *
     * Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
     * Example 3:
     * Input: [2, 2, 3, 1]
     *
     * Output: 1
     * Explanation: Note that the third maximum here means the third maximum distinct number.
     * Both numbers with value 2 are both considered as second maximum.
     */

    public static int thirdMax(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        Integer first = null;
        Integer second = null;
        Integer third = null;
        for (int n : nums) {


            if(first == null) {
                first = n;
            } else if(first < n) {
                third = second;
                second = first;
                first = n;
            } else if(first > n) {
                if(second == null) {
                    second = n;
                } else if(second < n) {
                    third = second;
                    second = n;
                } else if(n < second) {
                    if(third == null) {
                        third = n;
                    } else if(third < n ) {
                        third = n;
                    }
                }
            }
        }
        return third == null ? first : third;
    }

    public static void main(String[] args) {
        System.out.println(thirdMax(new int[]{2,2,3,1}));
        System.out.println(thirdMax(new int[]{2,2,3,1}));
        System.out.println(thirdMax(new int[]{1,Integer.MIN_VALUE,1}));
    }
}
