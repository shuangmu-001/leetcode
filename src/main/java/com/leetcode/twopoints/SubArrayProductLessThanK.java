package com.leetcode.twopoints;

/**
 * @author zms
 * @date 5:41 下午 2020/9/28
 * <a href="https://leetcode-cn.com/problems/subarray-product-less-than-k/">
 *     SubArray Product Less Than K</a>
 * TODO 如何优化代码
 */
public class SubArrayProductLessThanK {
    /**
     * Your are given an array of positive integers nums.
     * Count and print the number of (contiguous) sub arrays
     * where the product of all the elements in the sub array is less than k.
     *
     * Example 1:
     * Input: nums = [10, 5, 2, 6], k = 100
     * Output: 8
     * Explanation: The 8 sub arrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
     * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
     * Note:
     *
     * 0 < nums.length <= 50000.
     * 0 < nums[i] < 1000.
     * 0 <= k < 10^6.
     */
    public static int numSubarrayProductLessThanK1(int[] nums, int k) {
        int res = 0;
        if(k == 0) {
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] >= k) {
                continue;
            }
            res++;
            int product = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                product *= nums[j];
                if(product < k) {
                    res++;
                } else {
                    break;
                }
            }
        }
        return res;
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int res = 0;
        if(k == 0) {
            return res;
        }
        int product = 1;
        for (int start = 0, end = 0; end < nums.length;) {
            if(nums[end] > k) {
                end++;
                start = end;
                product = 1;
                continue;
            }
            product *= nums[end];
            if(product < k) {
                res += (end - start + 1);
                end++;
            } else {
                while (product >= k && start < end) {
                    product /= nums[start++];
                }
                product /= nums[end];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }


}
