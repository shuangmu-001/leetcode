package com.leetcode.dp.knapsack;

import com.leetcode.Utils;

import java.util.*;

/**
 * @author wcl
 * @date 5:12 PM 2020/5/18
 * <a href="https://leetcode.com/problems/target-sum/">
 * Target Sum</a>
 * @see PartitionEqualSubsetSum
 */
public class TargetSum {
    /**
     * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
     * Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
     *
     * Find out how many ways to assign symbols to make sum of integers equal to target S.
     *
     * Example 1:
     * Input: nums is [1, 1, 1, 1, 1], S is 3.
     * Output: 5
     * Explanation:
     *
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     *
     * There are 5 ways to assign symbols to make the sum of nums be target 3.
     * Note:
     * The length of the given array is positive and will not exceed 20.
     * The sum of elements in the given array will not exceed 1000.
     * Your output answer is guaranteed to be fitted in a 32-bit integer.
     */
    static int sum;

    public static int findTargetSumWays1(int[] nums, int S) {
        for (int i : nums) {
            sum += i;
        }
        if(S > sum) {
            return 0;
        }
        return dfs(nums, S, 0, 0, new int[nums.length][sum * 2 + S + 1]);
    }
    static int res = 0;
    static int count = 0;
    // Recursion
    public static void dfs1(int[] nums, int S, int sum, int index) {
        if(nums.length <= index) {
            count++;
            if(nums.length == index && sum == S) {
                res++;
            }
            return;
        }
        dfs1(nums, S, sum + nums[index], index + 1);
        dfs1(nums, S, sum - nums[index], index + 1);
    }

    public static int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if(S > sum) {
            return 0;
        }

        int m = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.merge(nums[0], 1, Integer::sum);
        map.merge(-nums[0], 1, Integer::sum);
        for (int i = 1; i < m; i++) {
            Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
            Map<Integer, Integer> newMap = new HashMap<>();
            for(Map.Entry<Integer, Integer> entry : entries) {
                newMap.merge(entry.getKey() + nums[i], entry.getValue(), Integer::sum);
                newMap.merge(entry.getKey() - nums[i], entry.getValue(), Integer::sum);
            }
            map = newMap;
        }
        System.out.println(map);
        return map.getOrDefault(S, 0);
    }

    public static int findTargetSumWays(int[] nums, int S) {
        int m = nums.length;
        int[] dp = new int[2001];
        dp[nums[0] + 1000]++;
        dp[-nums[0] + 1000]++;
        for (int i = 1; i < m; i++) {
            int[] newDp = new int[2001];
            for (int j = 0; j < 2001; j++) {
                if (dp[j] > 0) {
                    newDp[j + nums[i]] += dp[j];
                    newDp[j - nums[i]] += dp[j];
                }
            }
            dp = newDp;
        }
        return dp[1000 + S];
    }

    public static int dfs(int[] nums, int S, int index, int depth, int[][] dp) {
        if(nums.length <= index) {
            if(nums.length == index && 0 == S) {
                return 1;
            }
            return 0;
        }
        if(dp[depth][S + sum] != 0) {
            return dp[depth][S + sum];
        }
        int before = dfs(nums,  S + nums[index], index + 1, depth + 1, dp);
        dp[depth][S + sum] += before;
        int after = dfs(nums, S - nums[index], index + 1, depth + 1, dp);
        dp[depth][S + sum] += after;
        return dp[depth][S + sum];
    }
    // TODO other
    public static int findTargetSumWays10(int[] nums, int S) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        // 我们这里的问题同样可以理解为将nums拆分为P&N两个子集（P做加法，N做减法），
        // 那么我们的问题就变成了sum(P)-sum(N)=target也就是2*sum(P)=target+sum(nums)，
        // 也就是说target+sum(nums)必须是一个偶数
        if (sum < S || (S + sum) % 2 == 1) {
            return 0;
        }
        int s = (S + sum)/2;
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums) {
            for (int i = s; i >= n; i--) {
                dp[i] += dp[i - n];
            }
        }
        return dp[s];
    }


    public static void main(String[] args) {
        System.out.println(findTargetSumWays10(new int[]{0,0,0,0,0,0,0,0,1}, 1));
        System.out.println(findTargetSumWays(new int[]{1,0} ,1));
        List<int[]> read = Optional.ofNullable(Utils.read("./src/main/resources/non-negative.txt", 20, true)).orElse(new ArrayList<>());
        System.out.println(findTargetSumWays1(new int[]{1,2,3,4,5,6,7,8}, 3) == findTargetSumWays(new int[]{1,2,3,4,5,6,7,8}, 3));
        for (int[] nums : read) {
            int[] newNums = new int[20];
            int index = 0;
            for (int n : nums) {
                newNums[index++] = n % 10;
            }
            Utils.printArrays(newNums);
            System.out.println(findTargetSumWays1(newNums, 23) );
            System.out.println(findTargetSumWays(newNums, 23) );
        }
    }
}
