package com.leetcode.slidingwindow;

/**
 * @author wcl
 * @date 6:26 PM 2020/5/7
 * <a href="https://leetcode.com/problems/max-consecutive-ones-iii/">
 *     Max Consecutive Ones III</a>
 * TODO 滑动窗口最大值和最小值
 */
public class MaxConsecutiveOnesIII {
    /**
     * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
     * Return the length of the longest (contiguous) subarray that contains only 1s.
     *
     * Example 1:
     * Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
     * Output: 6
     * Explanation:
     * [1,1,1,0,0,1,1,1,1,1,1]
     * Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
     *
     * Example 2:
     * Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
     * Output: 10
     * Explanation:
     * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
     * Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
     *
     * Note:
     * 1 <= A.length <= 20000
     * 0 <= K <= A.length
     * A[i] is 0 or 1
     */
    public int longestOnes(int[] A, int K) {
        int count = 0;
        int res = 0;
        int length = A.length;
        if(K == length) {
            return length;
        }
        int left = 0;
        int right = 0;
        int len = 0;
        while(left < length && right < length) {
            if(left == right) {
                count = 0;
            }
            if(A[right] == 0) {
                count++;
            }
            if(count < K) {
                right++;
                len++;
                res = Math.max(len, res);
            } else {
                if(A[left] == 0) {
                    count--;
                }
                left++;
            }
        }
        return 0;
    }
}
