package com.leetcode.math;

import java.util.TreeMap;

/**
 * @author wcl
 * @date 11:07 下午 2020/9/1
 * <a href="https://leetcode.com/problems/largest-time-for-given-digits/">Largest Time for Given Digits</a>
 * TODO 排列组合
 */
public class LargestTimeForGivenDigits {
    /**
     * Given an array of 4 digits, return the largest 24 hour time that can be made.
     * <p>
     * The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.
     * <p>
     * Return the answer as a string of length 5.  If no valid time can be made, return an empty string.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: [1,2,3,4]
     * Output: "23:41"
     * Example 2:
     * <p>
     * Input: [5,5,5,5]
     * Output: ""
     * <p>
     * <p>
     * Note:
     * <p>
     * A.length == 4
     * 0 <= A[i] <= 9
     */
    public String largestTimeFromDigits(int[] A) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < 4; i++) {
            treeMap.merge(A[i], 1, Integer::sum);
        }
        StringBuilder sb = new StringBuilder();
        Integer first = treeMap.lowerKey(3);
        if (first == null) {
            return "";
        }
        update(treeMap, first);

        Integer second;
        if (first == 2) {
            second = treeMap.lowerKey(4);
            if (second == null) {
                return "";
            }
        } else {
            second = treeMap.lastKey();
        }
        update(treeMap, second);
        sb.append(first).append(second).append(":");
        Integer third = treeMap.lowerKey(7);
        if (third == null) {
            if (first == 2) {
                treeMap = new TreeMap<>();
                for (int i = 0; i < 4; i++) {
                    treeMap.merge(A[i], 1, Integer::sum);
                }
                sb = new StringBuilder();
                first = treeMap.lowerKey(2);
                if (first == null) {
                    return "";
                }
                update(treeMap, first);
                second = treeMap.lastKey();
                update(treeMap, second);
                sb.append(first).append(second).append(":");
                third = treeMap.lowerKey(6);
                if (third == null) {
                    return "";
                }
                update(treeMap, third);
                sb.append(third).append(treeMap.lastKey());
                return sb.toString();
            }
            return "";
        }
        update(treeMap, third);
        sb.append(third).append(treeMap.lastKey());
        return sb.toString();
    }

    public void update(TreeMap<Integer, Integer> treeMap, int key) {
        Integer integer = treeMap.get(key);
        if (integer == 1) {
            treeMap.remove(key);
        } else {
            treeMap.put(key, integer - 1);
        }
    }

    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 2, 2, 3, 4};
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < 4; i++) {
            treeMap.merge(A[i], 1, Integer::sum);
        }
        System.out.println(treeMap);
        System.out.println(treeMap.lowerKey(1));
        System.out.println(treeMap.lastKey());
        A = new int[]{1, 2, 3, 4};
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(A));
    }
}
