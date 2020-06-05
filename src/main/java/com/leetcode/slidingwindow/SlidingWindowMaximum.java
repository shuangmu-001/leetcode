package com.leetcode.slidingwindow;

import com.leetcode.Utils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author wcl
 * @date 2:39 下午 2020/5/25
 * <a href="https://leetcode.com/problems/sliding-window-maximum/">
 * Sliding Window Maximum</a>
 */
public class SlidingWindowMaximum {
    /**
     * Given an array nums, there is a sliding window of size k which is moving from
     * the very left of the array to the very right. You can only see the k numbers in the window.
     * Each time the sliding window moves right by one position. Return the max sliding window.
     *
     * Follow up:
     * Could you solve it in linear time?
     *
     * Example:
     *
     * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
     * Output: [3,3,5,5,6,7]
     * Explanation:
     *
     * Window position                Max
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     * 1 <= k <= nums.length
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int length = nums.length;
        int newLen = length - k + 1;
        int[] res = new int[newLen];
        int index = 0;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            while(!deque.isEmpty() && deque.getLast() < nums[i]) {
                deque.pollLast();
            }
            deque.add(nums[i]);
            if(i >= k - 1) {
                if(i >= k && nums[i - k] == deque.getFirst()) {
                    deque.removeFirstOccurrence(nums[i - k]);
                }
                res[index++] = deque.getFirst();
            }
            System.out.println(deque);
        }
        return res;
    }

    public static void main(String[] args) {
//        Utils.printArrays(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 1));
        Utils.printArrays(maxSlidingWindow(new int[]{9,10,9,-7,-4,-8,2,-6}, 5));
    }
}
