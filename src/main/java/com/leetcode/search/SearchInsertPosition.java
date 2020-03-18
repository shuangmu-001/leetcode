package com.leetcode.search;

/**
 * @author wcl
 * @date 2:24 PM 2020/1/15
 * {@link "https://leetcode.com/problems/search-insert-position/"}
 * 二分查找法
 * @see com.leetcode.graph.tree.bt.bst.ConvertSortedArrayToBST
 * @see com.leetcode.graph.tree.bt.CountCompleteTreeNodes
 */
public class SearchInsertPosition {
    /**
     * Given a sorted array and a target value,
     * return the index if the target is found. If not,
     * return the index where it would be if it were inserted in order.
     *
     * You may assume no duplicates in the array.
     *
     * Example 1:
     *      Input: [1,3,5,6], 5
     *      Output: 2
     *
     * Example 2:
     *      Input: [1,3,5,6], 2
     *      Output: 1
     *
     * Example 3:
     *      Input: [1,3,5,6], 7
     *      Output: 4
     *
     * Example 4:
     *      Input: [1,3,5,6], 0
     *      Output: 0
     *
     * @param nums a sorted array
     * @param target a target value
     */
    public static int searchInsert(int[] nums, int target) {
        int mid = 0;
        for (int start = 0, end = nums.length - 1; start <= end; ) {
            if(nums[end] < target) {
                return end + 1;
            }
            if(target < nums[start]) {
                return start;
            }
            mid = (end + start) / 2 ;
            if(nums[mid] == target) {
                return mid;
                // mid已经判断过了 所以 end = mid - 1， start = mid + 1； 这样循环的条件 就可以用start <= end
            } else if(nums[mid] > target) {
                end = mid - 1;
            } else if(nums[mid] < target) {
                start = mid + 1;
            }
        }

        return nums[mid] < target ? mid + 1 : mid;
    }
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 6, 10};
        System.out.println(searchInsert(nums1, 5));
        int[] nums2 = {1, 3, 5, 6, 10};
        System.out.println(searchInsert(nums2, 4));
        int[] nums3 = {1, 3, 5, 6, 10};
        System.out.println(searchInsert(nums3, 7));
        int[] nums4 = {1, 3, 5, 6, 10};
        System.out.println(searchInsert(nums4, 0));

        int[] nums5 = {1,  10};
        System.out.println(searchInsert(nums5, 3));
    }
}
