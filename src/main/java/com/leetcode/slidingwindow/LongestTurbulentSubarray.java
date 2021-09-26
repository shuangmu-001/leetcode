package com.leetcode.slidingwindow;

/**
 * @author zms
 * @date 6:15 下午 2020/5/25
 * <a href="https://leetcode.com/problems/longest-turbulent-subarray/">
 * Longest Turbulent Subarray</a>
 */
public class LongestTurbulentSubarray {
    /**
     * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:
     *
     * For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
     * OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
     * That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
     *
     * Return the length of a maximum size turbulent subarray of A.
     *
     *
     *
     * Example 1:
     *
     * Input: [9,4,2,10,7,8,8,1,9]
     * Output: 5
     * Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
     * Example 2:
     *
     * Input: [4,8,12,16]
     * Output: 2
     * Example 3:
     *
     * Input: [100]
     * Output: 1
     *
     *
     * Note:
     *
     * 1 <= A.length <= 40000
     * 0 <= A[i] <= 10^9
     */
    public static int maxTurbulenceSize(int[] A) {
        int length = A.length;
        if(length == 1) {
            return 1;
        }
        int index = 0;
        while(index < length - 1 && A[index] == A[index + 1]) {
            index++;
        }
        if(index >= length - 2) {
            return length - index;
        }

        int res = 0;
        int left = index;
        int right = index + 1;

        boolean before = ((index & 1) == 0) == (A[index] < A[index + 1]);

        while(left < length - 1 && right < length - 1) {
            if(A[right] == A[right + 1]) {
                res = Math.max(right - left + 1, res);
                while(right < length - 1 && A[right] == A[right + 1]) {
                    right++;
                }
                if(right < length - 1) {
                    before = ((right & 1) == 0) == A[right] < A[right + 1];
                    left = right;
                }

            } else {
                boolean cur = ((right & 1) == 0) == (A[right] < A[right + 1]);
                if(cur != before) {
                    res = Math.max(right - left + 1, res);
                    left = right;
                    before = cur;
                }
            }

            right++;
        }
        res = Math.max(right - left + 1, res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9}) == 5);
        System.out.println(maxTurbulenceSize(new int[]{4,8,12,16}) == 2);
        System.out.println(maxTurbulenceSize(new int[]{100}) == 1);
        System.out.println(maxTurbulenceSize(new int[]{9,9}) == 1);
        System.out.println(maxTurbulenceSize(new int[]{9,9,8}) == 2);
        System.out.println(maxTurbulenceSize(new int[]{0,1,1,0,1,0,1,1,0,0}) == 5);
        System.out.println(maxTurbulenceSize(new int[]{0,8,45,88,48,68,28,55,17,24}) == 8);
    }
}
