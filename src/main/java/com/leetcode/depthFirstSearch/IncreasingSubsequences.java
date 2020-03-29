package com.leetcode.depthFirstSearch;

import com.leetcode.dp.NumberOfLongestIncreasingSubsequence;

import java.util.*;

/**
 * @author wcl
 * @date 4:57 PM 2020/1/15
 * TODO {@link "https://leetcode.com/problems/increasing-subsequences/"}
 * @see NumberOfLongestIncreasingSubsequence
 */
public class IncreasingSubsequences {
    /**
     * Given an integer array,
     * your task is to find all the different possible increasing subsequences of the given array,
     * and the length of an increasing subsequence should be at least 2.
     * <p>
     * Example:
     * Input: [4, 6, 7, 7]
     * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7, 7], [4, 7, 7]]
     * <p>
     * <p>
     * Note:
     * The length of the given array will not exceed 15.
     * The range of integer in the given array is [-100,100].
     * The given array may contain duplicates,
     * and two equal integers should also be considered as a special case of increasing sequence.
     *
     * Runtime: 18 ms, faster than 46.33% of Java online submissions for Increasing Subsequences.
     * Memory Usage: 57.4 MB, less than 14.29% of Java online submissions for Increasing Subsequences.
     */
    public static List<List<Integer>> findSubsequences(int[] nums) {

        int length = nums.length;
        List<List<Integer>> results = new ArrayList<>();
        // 结尾
        Map<Integer, Map<Integer, List<List<Integer>>>> con = new HashMap<>();
        // 重复标志
        Map<List<Integer>, Integer> map = new HashMap<>();
        boolean[] flag = new boolean[length];
        for (int i = 0; i < length - 1; i++) {
            if (flag[i]) {
                continue;
            }
            int minFlag = 0;
            for (int j = i + 1; j < length; j++) {

                if (nums[i] > nums[j]) {
                    List<Integer> integers = Arrays.asList(nums[j], nums[i]);
                    if (!map.containsKey(integers)) {
                        minFlag++;
                    }
                } else {
                    if (nums[j] == nums[i] && minFlag == 0) {
                        flag[j] = true;
                    }
                    List<Integer> integers = Arrays.asList(nums[i], nums[j]);
                    if (con.containsKey(nums[i])) {
                        Map<Integer, List<List<Integer>>> map1 = con.get(nums[i]);
                        Map<Integer, List<List<Integer>>> map2 = con.get(nums[j]);
                        List<List<Integer>> lists1;
                        if (con.containsKey(nums[j])) {
                            if (map2.containsKey(j)) {
                                lists1 = map2.get(j);
                            } else {
                                lists1 = new ArrayList<>();
                                map2.put(j, lists1);
                            }
                        } else {
                            map2 = new HashMap<>();
                            lists1 = new ArrayList<>();
                            map2.put(j, lists1);
                            con.put(nums[j], map2);
                        }

                        Set<Integer> integers1 = map1.keySet();
                        for (int l : integers1) {
                            if (l < j) {
                                List<List<Integer>> lists = map1.get(l);
                                int size = lists.size();
                                for (int k = 0; k < size; k++) {
                                    List<Integer> oldList = lists.get(k);
                                    List<Integer> objects = new ArrayList<>(oldList);
                                    objects.add(nums[j]);
                                    if (!map.containsKey(objects)) {
                                        results.add(objects);
                                        map.put(objects, 1);
                                        lists1.add(objects);
                                    }

                                }
                            }
                        }
                    }

                    if (!map.containsKey(integers)) {
                        results.add(integers);
                        map.put(Arrays.asList(nums[i], nums[j]), 1);
                        // 用于下次是否会续
                        if (con.containsKey(nums[j])) {
                            Map<Integer, List<List<Integer>>> map1 = con.get(nums[j]);
                            if (map1.containsKey(j)) {
                                List<List<Integer>> lists = map1.get(j);
                                lists.add(Arrays.asList(nums[i], nums[j]));
                            } else {
                                List<List<Integer>> lists = new ArrayList<>();
                                lists.add(Arrays.asList(nums[i], nums[j]));
                                map1.put(j, lists);
                            }
                        } else {
                            List<List<Integer>> lists = new ArrayList<>();
                            lists.add(Arrays.asList(nums[i], nums[j]));
                            Map<Integer, List<List<Integer>>> map1 = new HashMap<>();
                            map1.put(j, lists);
                            con.put(nums[j], map1);
                        }
                    }
                }
            }
        }
        System.out.println(con);
        return results;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 6, 7, 7};
        System.out.println(findSubsequences(nums1));

        int[] nums2 = {1, 2, 3, 4};
        System.out.println(findSubsequences(nums2));

        int[] nums3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 1, 1, 1, 1};
        System.out.println(findSubsequences(nums3));

        int[] nums4 = {1, 1, 1, 2, 2, 2, 3, 3, 3};
        System.out.println(findSubsequences(nums4));
        //[[1,1],[1,1,1],[1,1,1,2],[1,1,1,2,2],[1,1,1,2,2,2],[1,1,1,2,2,2,3],[1,1,1,2,2,2,3,3],[1,1,1,2,2,2,3,3,3],[1,1,1,2,2,3],[1,1,1,2,2,3,3],[1,1,1,2,2,3,3,3],[1,1,1,2,3],[1,1,1,2,3,3],[1,1,1,2,3,3,3],[1,1,1,3],[1,1,1,3,3],[1,1,1,3,3,3],[1,1,2],[1,1,2,2],[1,1,2,2,2],[1,1,2,2,2,3],[1,1,2,2,2,3,3],[1,1,2,2,2,3,3,3],[1,1,2,2,3],[1,1,2,2,3,3],[1,1,2,2,3,3,3],[1,1,2,3],[1,1,2,3,3],[1,1,2,3,3,3],[1,1,3],[1,1,3,3],[1,1,3,3,3],[1,2],[1,2,2],[1,2,2,2],[1,2,2,2,3],[1,2,2,2,3,3],[1,2,2,2,3,3,3],[1,2,2,3],[1,2,2,3,3],[1,2,2,3,3,3],[1,2,3],[1,2,3,3],[1,2,3,3,3],[1,3],[1,3,3],[1,3,3,3],[2,2],[2,2,2],[2,2,2,3],[2,2,2,3,3],[2,2,2,3,3,3],[2,2,3],[2,2,3,3],[2,2,3,3,3],[2,3],[2,3,3],[2,3,3,3],[3,3],[3,3,3]]
        //[[1,1],[1,1,1],[1,1,2],[1,1,1,2],[1,2],[1,1,3],[1,1,1,3],[1,3],[1,1,2,2],[1,1,1,2,2],[1,2,2],[2,2],[1,1,2,2,2],[1,1,1,2,2,2],[1,2,2,2],[2,2,2],[1,1,2,3],[1,1,1,2,3],[1,2,3],[1,1,2,2,3],[1,1,1,2,2,3],[1,2,2,3],[2,2,3],[1,1,2,2,2,3],[1,1,1,2,2,2,3],[1,2,2,2,3],[2,2,2,3],[2,3],[1,1,3,3],[1,1,1,3,3],[1,3,3],[1,1,2,3,3],[1,1,1,2,3,3],[1,2,3,3],[1,1,2,2,3,3],[1,1,1,2,2,3,3],[1,2,2,3,3],[2,2,3,3],[1,1,2,2,2,3,3],[1,1,1,2,2,2,3,3],[1,2,2,2,3,3],[2,2,2,3,3],[2,3,3],[3,3],[1,1,3,3,3],[1,1,1,3,3,3],[1,3,3,3],[1,1,2,3,3,3],[1,1,1,2,3,3,3],[1,2,3,3,3],[1,1,2,2,3,3,3],[1,1,1,2,2,3,3,3],[1,2,2,3,3,3],[2,2,3,3,3],[1,1,2,2,2,3,3,3],[1,1,1,2,2,2,3,3,3],[1,2,2,2,3,3,3],[2,2,2,3,3,3],[2,3,3,3],[3,3,3]]

        int[] nums5 = {100, 90, 80, 70, 60, 50, 60, 70, 80, 90, 100};
        System.out.println(findSubsequences(nums5));

        int[] nums6 = {1, 2, 3, 1, 2, 3, 1, 2, 3};
        System.out.println(findSubsequences(nums6));
        //[[1,2],[1,2,3],[1,2,3,3],[1,2,3,3,3],[1,2,2],[1,2,2,3],[1,2,2,3,3],[1,2,2,2],[1,2,2,2,3],[1,3],[1,3,3],[1,3,3,3],[1,1],[1,1,2],[1,1,2,3],[1,1,2,3,3],[1,1,2,2],[1,1,2,2,3],[1,1,3],[1,1,3,3],[1,1,1],[1,1,1,2],[1,1,1,2,3],[1,1,1,3],[2,3],[2,3,3],[2,3,3,3],[2,2],[2,2,3],[2,2,3,3],[2,2,2],[2,2,2,3],[3,3],[3,3,3]]
        //[[1,2],[1,3],[1,1],[1,1,2],[1,1,3],[1,1,1],[1,1,1,2],[1,1,1,3],[1,2,3],[2,3],[1,2,2],[2,2],[1,1,2,3],[1,2,2,3],[2,2,3],[1,1,2,2],[1,2,2,2],[2,2,2],[1,1,1,2,3],[1,1,2,2,3],[1,2,2,2,3],[2,2,2,3],[1,3,3],[1,2,3,3],[2,3,3],[3,3],[1,1,3,3],[1,1,2,3,3],[1,2,2,3,3],[2,2,3,3],[1,3,3,3],[1,2,3,3,3],[2,3,3,3],[3,3,3]]

    }



}
