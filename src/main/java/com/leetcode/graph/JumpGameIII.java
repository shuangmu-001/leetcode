package com.leetcode.graph;

import java.util.*;

/**
 * @author wcl
 * @date 2:55 PM 2020/4/26
 * <a href="https://leetcode.com/problems/jump-game-iii/">
 *     Jump Game III</a>
 */
public class JumpGameIII {
    /**
     * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
     *
     * Notice that you can not jump outside of the array at any time.
     *
     *
     *
     * Example 1:
     *
     * Input: arr = [4,2,3,0,3,1,2], start = 5
     * Output: true
     * Explanation:
     * All possible ways to reach at index 3 with value 0 are:
     * index 5 -> index 4 -> index 1 -> index 3
     * index 5 -> index 6 -> index 4 -> index 1 -> index 3
     * Example 2:
     *
     * Input: arr = [4,2,3,0,3,1,2], start = 0
     * Output: true
     * Explanation:
     * One possible way to reach at index 3 with value 0 is:
     * index 0 -> index 4 -> index 1 -> index 3
     * Example 3:
     *
     * Input: arr = [3,0,2,1,2], start = 2
     * Output: false
     * Explanation: There is no way to reach at index 1 with value 0.
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 5 * 10^4
     * 0 <= arr[i] < arr.length
     * 0 <= start < arr.length
     */
    public static boolean canReach(int[] arr, int start) {
        int len = arr.length;
        if(start >= len) {
            return false;
        }
        Queue<Integer> indexs = new LinkedList<>();
        indexs.add(start);
        Set<Integer> set = new HashSet<>();
        while(!indexs.isEmpty()) {
            Integer pop = indexs.poll();

            if(arr[pop] == 0) {
                return true;
            }
            set.add(pop);
            if(pop + arr[pop] < len && !set.contains(pop + arr[pop])) {
                indexs.add(pop + arr[pop]);
            }
            if(pop - arr[pop] >= 0 && !set.contains(pop - arr[pop])) {
                indexs.add(pop - arr[pop]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canReach(new int[]{3,0,2,1,2}, 2));
    }
}
