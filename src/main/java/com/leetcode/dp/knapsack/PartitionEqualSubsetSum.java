package com.leetcode.dp.knapsack;


import java.util.*;

/**
 * @author zms
 * @date 2:23 PM 2020/5/18
 * <a href="https://leetcode.com/problems/partition-equal-subset-sum/">
 * Partition Equal Subset Sum</a>
 */
public class PartitionEqualSubsetSum {
    /**
     * Given a non-empty array containing only positive integers,
     * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
     *
     * Note:
     * Each of the array element will not exceed 100.
     * The array size will not exceed 200.
     *
     * Example 1:
     * Input: [1, 5, 11, 5]
     * Output: true
     * Explanation: The array can be partitioned as [1, 5, 5] and [11].
     *
     * Example 2:
     * Input: [1, 2, 3, 5]
     * Output: false
     * Explanation: The array cannot be partitioned into equal sum subsets.
     */
    public static boolean canPartition1(int[] nums) {
        if(nums == null || nums.length < 2) {
            return false;
        }
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }
        if((sum & 1) != 0) {
            return false;
        }
        // MLE 用set解决重复问题
        Set<Integer> dp = new HashSet<>();
        for (int num : nums) {
            int target = num * 2;
            if(sum == target) {
                return true;
            }
            if(sum < target) {
                return false;
            }
            Set<Integer> newDp = new HashSet<>();
            for (int n : dp) {
                target = (n + num) * 2;
                if(target == sum) {
                    return true;
                } else if(target < sum) {
                    newDp.add(n + num);
                }
            }

            newDp.add(num);
            dp = newDp;
        }
        return false;
//        res = false;
//        dfs(nums, 0, sum, 0);
//        return res;

    }
    static boolean res = false;
    // TLE
    public static void dfs(int[] nums, int index, int target, int sum) {

        if(nums.length <= index) {
            return;
        }
        sum += nums[index];
        if((nums[index] * 2) == target || sum * 2 == target || res) {
            res = true;
            return;
        }

        if(sum * 2 < target) {
            for (int i = index + 1; i < nums.length; i++){
                dfs(nums, i, target, sum);
            }
        }
    }

    public static boolean canPartition(int[] nums) {
        if(nums == null || nums.length < 2) {
            return false;
        }
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }
        if((sum & 1) != 0) {
            return false;
        }
//        int m = nums.length;
        int n = sum >> 1;
//        boolean[][] dp = new boolean[m][n + 1];
//        for (int i = 0; i < m; i++) {
//            dp[i][0] = true;
//        }
//        int max = 0;
//        for (int i = 0; i < m; i++) {
//            dp[i][nums[i]] = true;
//            max += nums[i];
//            max = Math.min(max, n);
//            for (int j = 0; j <= max; j++) {
//                if(i == 0) {
//                    dp[i][nums[i]] = true;
//                } else {
//                    if(dp[i - 1][j]) {
//                        dp[i][j] = true;
//                        if(j + nums[i] < n+1) {
//                            dp[i][j + nums[i]] = true;
//                        }
//                    }
//                }
//            }
//        }
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int num: nums){
            for(int j=n; j>=0; j--){
                if(j >= num){
                    dp[j] = dp[j] || dp[j-num];
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
//        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
//        System.out.println(canPartition(new int[]{1, 2, 3, 10}));
        System.out.println(canPartition(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }

}
