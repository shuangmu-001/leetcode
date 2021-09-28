package com.question.day09;

/**
 * <a href="https://leetcode.com/problems/longest-increasing-subsequence">
 * longest increasing subsequence</a>
 *
 * @author zms
 * @date 8:37 下午 2021/9/27
 */
public class Code03LIS {
    /**
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * <p>
     * 示例 1：
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * <p>
     * 示例 2：
     * 输入：nums = [0,1,0,3,2,3]
     * 输出：4
     * <p>
     * 示例 3：
     * 输入：nums = [7,7,7,7,7,7,7]
     * 输出：1
     * <p>
     * 提示：
     * 1 <= nums.length <= 2500
     * -10^4 <= nums[i] <= 10^4
     * <p>
     * 进阶：
     * 你可以设计时间复杂度为 O(n^2) 的解决方案吗？
     * 你能将算法的时间复杂度降低到O(nlog(n)) 吗?
     */
    public int lengthOfLIS01(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // 以i位置结尾的最长子序列长度
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // 构建最长的递增子序列
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        // 最长的递增子序列
        int[] arr = new int[length];
        // 递增子序列的长度
        int ans = 0;
        arr[0] = nums[0];
        for (int i = 1; i < length; i++) {
            ans = search(arr, 0, ans, nums[i]);
        }
        return ans + 1;
    }

    // 二分找大于目标数据的最小位置
    public int search(int[] arr, int start, int end, int target) {
        int size = end;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (arr[mid] > target) {
                end = mid - 1;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                return size;
            }
        }
        arr[start] = target;
        return Math.max(start, size);
    }
}
