package com.leetcode.KDiffPairsInAnArray;

import com.leetcode.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wcl
 * @date 2:40 PM 2020/1/3
 * {@link <https://leetcode.com/problems/k-diff-pairs-in-an-array/>}
 */
public class Solution {
    /**
     * Given an array of integers and an integer k,
     * you need to find the number of unique k-diff pairs in the array.
     * Here a k-diff pair is defined as an integer pair (i, j),
     * where i and j are both numbers in the array and their absolute difference is k.
     *
     * Example 1:
     *      Input: [3, 1, 4, 1, 5], k = 2
     *      Output: 2
     *      Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
     *                   Although we have two 1s in the input,
     *                   we should only return the number of unique pairs.
     *
     * Example 2:
     *      Input:[1, 2, 3, 4, 5], k = 1
     *      Output: 4
     *      Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
     *
     * Example 3:
     *      Input: [1, 3, 1, 5, 4], k = 0
     *      Output: 1
     *      Explanation: There is one 0-diff pair in the array, (1, 1).
     *
     * Note:
     *      The pairs (i, j) and (j, i) count as the same pair.
     *      The length of the array won't exceed 10,000.
     *      All the integers in the given input belong to the range: [-1e7, 1e7].
     *
     *
     * Runtime: 508 ms, faster than 5.33% of Java online submissions for K-diff Pairs in an Array.
     * Memory Usage: 40.9 MB, less than 21.05% of Java online submissions for K-diff Pairs in an Array.
     * @param nums an array of integers
     * @param k integer
     * @return int
     */
    public static int findPairs1(int[] nums, int k) {

        if(nums == null || nums.length < 2 || k < 0) {
            return 0;
        }

        int length = nums.length;
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length - 1; i++) {
            if (map.containsKey(nums[i]) && map.containsKey(nums[i] - k)) {
                continue;
            }
            for (int j = i + 1; j < length; j++) {
                int diff = nums[j] - nums[i];
                if(diff == k && !map.containsKey(nums[i])) {
                    result++;
                    map.put(nums[i], nums[j]);
                }

                if(diff == -k && !map.containsKey(nums[j])) {
                    result++;
                    map.put(nums[j], nums[i]);
                }

                if (map.containsKey(nums[i]) && map.containsKey(nums[i] - k)) {
                    break;
                }
            }
        }
        System.out.println(result);
        return result;
    }

    /**
     * Runtime: 10 ms, faster than 68.34% of Java online submissions for K-diff Pairs in an Array.
     * Memory Usage: 40.3 MB, less than 84.21% of Java online submissions for K-diff Pairs in an Array.
     */
    public static int findPairs2(int[] nums, int k) {

        if(nums == null || nums.length < 2 || k < 0) {
            return 0;
        }
        Arrays.sort(nums);
        int length = nums.length;
        int result = 0;
        int log = Integer.MIN_VALUE;
        for (int i = 0, j = 1; i < length - 1 && j < length; j++) {
            int diff = nums[j] - nums[i];
            System.out.println(diff + " i = " + i + " j = " + j);
            if(diff == k) {
                if(nums[i] != log) {
                    log = nums[i];
                    result++;
                }
                i++;
                j = i;
            }
            if(diff > k) {
                i++;
                j = i;
            }
        }
        Utils.printArrays(nums);
        return result;
    }

    /**
     * Runtime: 181 ms, faster than 8.03% of Java online submissions for K-diff Pairs in an Array.
     * Memory Usage: 40.5 MB, less than 52.63% of Java online submissions for K-diff Pairs in an Array.
     */
    public static int findPairs(int[] nums, int k) {

        if(nums == null || nums.length < 2 || k < 0) {
            return 0;
        }
        int result = 0;
        int length = nums.length;
        int[] pairs = new int[length];
        int diff;
        for (int i = 0; i < length - 1; i++) {
            if(pairs[i] == 2) {
                continue;
            }
            int before = pairs[i] == -1 ? 1 : 0;
            int after = pairs[i] == 1 ? 1 : 0;
            for (int j = i + 1; j < length; j++) {
                diff = nums[j] - nums[i];
                if(diff == k) {
                    if(after == 0) {
                        result++;
                        after = 1;
                    }
                    pairs[j] = pairs[j] == 0 ? -1 : pairs[j];
                } else if(diff == -k) {
                    if(before == 0) {
                        result++;
                        before = 1;
                    }
                    pairs[j] = pairs[j] == 0 ? 1 : pairs[j];
                }
                if(diff == 0) {
                    pairs[j] = 2;
                }
            }
        }
        Utils.printArrays(pairs);
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 1, 4, 1, 5};
        System.out.println(findPairs(nums1, 2) == 2);
        int[] nums2 = {1, 2, 3, 4, 5};
        System.out.println(findPairs(nums2, 2) == 3);
        int[] nums3 = {1, 3, 1, 3, 4};
        System.out.println(findPairs(nums3, 0) == 2);
        int[] nums4 = {-1,0,0,1,0,0,-1};
        System.out.println(findPairs(nums4, 1) == 2);

        int[] nums5 = {1, 1, 1, 1, 1};
        System.out.println(findPairs(nums5, 0) == 1);
        int[] nums6 = {2,8,6,9,7,4,9,0,5,4};
        System.out.println(findPairs(nums6, 1) == 1);
    }

}
