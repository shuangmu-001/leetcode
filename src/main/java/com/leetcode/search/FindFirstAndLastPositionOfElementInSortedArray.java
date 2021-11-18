package com.leetcode.search;


import java.util.Arrays;

/**
 * 二分法 有相同的数，最左边和最右边
 * <a href="https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/">Find First and Last Position of Element in Sorted Array</a>
 *
 * @author zms
 * @date 2:39 PM 2020/4/23
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    /**
     * Given an array of integers nums sorted in ascending order,
     * find the starting and ending position of a given target value.
     * <p>
     * Your algorithm's runtime complexity must be in the order of O(log n).
     * <p>
     * If the target is not found in the array, return [-1, -1].
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     * Example 2:
     * <p>
     * Input: nums = [5,7,7,8,8,10], target = 6
     * Output: [-1,-1]
     */
    public static int[] searchRange1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = 0;
        int right = nums.length - 1;
        int[] res = new int[2];
        int mid = left;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (left > right) {
            return new int[]{-1, -1};
        }
        int before = mid;
        while (left <= before) {
            int newMid = left + (before - left) / 2;
            if (nums[newMid] == target) {
                before = newMid - 1;
            } else {
                left = newMid + 1;
            }
        }
        res[0] = left;
        before = mid;
        while (before <= right) {
            int newMid = before + (right - before) / 2;
            if (nums[newMid] == target) {
                before = newMid + 1;
            } else {
                right = newMid - 1;
            }
        }
        res[1] = right;
        return res;
    }

    public static int[] searchRange01(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return new int[]{-1, -1};
        }
        int first = findFirstLast(arr, target, 0, true);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        int last = findFirstLast(arr, target, first, false);
        return new int[]{first, last};
    }

    public static int findFirstLast(int[] arr, int target, int start, boolean first) {
        int index = -1;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (arr[mid] > target || (first && arr[mid] == target)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            if (arr[mid] == target) {
                index = mid;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 7)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10, 11, 12, 14, 15, 15, 16, 18, 19, 20}, 18)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10, 11, 12, 14, 15, 15, 16, 18, 19, 20}, 20)));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(findTarget(new int[]{5, 7, 7, 8, 8, 10}, 4, 0));
    }
    // 找到首个大于目标的数
    public static int findTarget(int[] arr, int target, int start) {
        int l = start;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if(arr[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static int[] searchRange(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return new int[]{-1, -1};
        }
        int first = findTarget(arr, target - 1, 0);
        if (arr[first] != target) {
            return new int[]{-1, -1};
        }
        int last = findTarget(arr, target, first);
        return new int[]{first, last - 1};
    }
}
