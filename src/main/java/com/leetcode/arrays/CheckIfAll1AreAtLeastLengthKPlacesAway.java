package com.leetcode.arrays;

/**
 * @author zms
 * @date 11:27 AM 2020/5/7
 * <a href="https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/">
 *     Check If All 1's Are at Least Length K Places Away</a>
 */
public class CheckIfAll1AreAtLeastLengthKPlacesAway {
    /**
     * Given an array nums of 0s and 1s and an integer k,
     * return True if all 1's are at least k places away from each other, otherwise return False.
     *
     * Example 1:
     * Input: nums = [1,0,0,0,1,0,0,1], k = 2
     * Output: true
     * Explanation: Each of the 1s are at least 2 places away from each other.
     *
     * Example 2:
     * Input: nums = [1,0,0,1,0,1], k = 2
     * Output: false
     * Explanation: The second 1 and third 1 are only one apart from each other.
     *
     * Example 3:
     * Input: nums = [1,1,1,1,1], k = 0
     * Output: true
     *
     * Example 4:
     * Input: nums = [0,1,0,1], k = 1
     * Output: true
     *
     * Constraints:
     * 1 <= nums.length <= 10^5
     * 0 <= k <= nums.length
     * nums[i] is 0 or 1
     */
    public static boolean kLengthApart(int[] nums, int k) {
        int index = 0;
        int length = nums.length;
        while(index < length && nums[index] == 0) {
            index++;
        }

        int distance = index == 0 ? length : index;
        int first = index;
        for (int i = first + 1; i < length; i++) {
            if(nums[i] == 1) {
                distance = Math.min(i - first - 1, distance);
                first = i;
            } else if(i == length - 1) {
                distance = Math.min(length - 1 - first, distance);
            }
            if(distance < k) {
                return false;
            }
        }
        return distance >= k;
    }

    public static void main(String[] args) {
        System.out.println(kLengthApart(new int[]{0,0,0,1,0}, 2));
        System.out.println(kLengthApart(new int[]{0,0,0,1,0,0}, 2));
        System.out.println(kLengthApart(new int[]{1,0,0,0,1,0,0,1}, 2));
    }
}
