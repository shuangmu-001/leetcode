package com.leetcode.dp.fibonacci;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author zms
 * @date 2:08 PM 2020/4/1
 * <a href="https://leetcode.com/problems/delete-and-earn/">
 *     Delete and Earn</a>
 * @see HouseRobber
 * @see HouseRobberII
 */
public class DeleteAndEarn {
    /**
     * Given an array nums of integers, you can perform operations on the array.
     * In each operation, you pick any nums[i] and delete it to earn nums[i] points.
     * After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
     * You start with 0 points. Return the maximum number of points you can earn by applying such operations.
     *
     * Example 1:
     *      Input: nums = [3, 4, 2]
     *      Output: 6
     *      Explanation:
     *          Delete 4 to earn 4 points, consequently 3 is also deleted.
     *          Then, delete 2 to earn 2 points. 6 total points are earned.
     *
     * Example 2:
     *      Input: nums = [2, 2, 3, 3, 3, 4]
     *      Output: 9
     *      Explanation:
     *          Delete 3 to earn 3 points, deleting both 2's and the 4.
     *          Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
     *          9 total points are earned.
     *
     * Note:
     *      The length of nums is at most 20000.
     *      Each element nums[i] is an integer in the range [1, 10000].
     */
    public static int deleteAndEarn02(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int[] newNum = new int[10000];
        for (int num : nums) {
            newNum[num] += num;
        }
        int first = newNum[0];
        int second = Math.max(newNum[1], newNum[0]);
        for (int i = 2; i < 10000; i++) {
            int temp = second;
            second = Math.max(second, first + newNum[i]);
            first = temp;
        }
        return second;
    }

    public static int deleteAndEarn(int[] nums) {
        // 构造一个不重复的排好序的数组
        Map<Integer, Integer> map = new TreeMap<>();
        for(int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        int n = map.size();
        nums = new int[n];
        int[] counts = new int[n];
        int index = 0;
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            nums[index] = entry.getKey();
            counts[index++] = entry.getValue();
        }
        int[][] dp = new int[n + 1][2];
        dp[1][0] = nums[0] * counts[0];
        for(int i = 1; i < n; i++) {
            dp[i + 1][0] = dp[i][1] + nums[i] * counts[i];
            if(nums[i] - nums[i - 1] != 1) {
                dp[i + 1][0] = Math.max(dp[i][0] + nums[i] * counts[i], dp[i + 1][0]);
            }
            dp[i + 1][1] = Math.max(dp[i][0],dp[i][1]);
        }

        return Math.max(dp[n][0], dp[n][1]);
    }

    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{2,4}));
        System.out.println(deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));
    }

}
