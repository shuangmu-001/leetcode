package com.leetcode.search;


/**
 * @author wcl
 * @date 12:02 PM 2020/4/3
 * <a href="https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/">
 *     Count Negative Numbers in a Sorted Matrix</a>
 */
public class CountNegativeNumbersInASortedMatrix {
    /**
     * Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise.
     * Return the number of negative numbers in grid.
     *
     * Example 1:
     *      Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
     *      Output: 8
     *      Explanation: There are 8 negatives number in the matrix.
     *
     * Example 2:
     *      Input: grid = [[3,2],[1,0]]
     *      Output: 0
     *
     * Example 3:
     *      Input: grid = [[1,-1],[-1,-1]]
     *      Output: 3
     *
     * Example 4:
     *      Input: grid = [[-1]]
     *      Output: 1
     *
     * Constraints:
     *      m == grid.length
     *      n == grid[i].length
     *      1 <= m, n <= 100
     *      -100 <= grid[i][j] <= 100
     */
    public int countNegatives(int[][] grid) {
        int count = 0;
        for (int[] ints : grid) {
            int index = searchIndex(ints, 0);
            if (index < 0) {
                count += (ints.length - ~index);
            } else {
                count += (ints.length - index - 1);
            }
        }
        return count;
    }

    public static int searchIndex(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while(left <= right) {
            mid = (left + right) / 2;
            if(nums[mid] == target) {
                break;
            } else if(nums[mid] > target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if(nums[mid] == 0) {
            for (int i = mid + 1; i < nums.length; i++) {
                if(nums[i] != 0) {
                    return i - 1;
                }
            }
            return nums.length - 1;
        }

        return ~left;
    }

    public static void main(String[] args) {
        System.out.println(searchIndex(new int[]{4,3,-2,-1}, 0));
        System.out.println(searchIndex(new int[]{4,3,0,-2}, 0));
        System.out.println(searchIndex(new int[]{-1,-2,-3,-4}, 0));
        System.out.println(searchIndex(new int[]{4,3,2,1}, 0));
        System.out.println(new int[]{11,9,9,9,8,8,8,7,7,7,6,6,5,4,4,4,4,4,4,4,3,2,1,1,0,0}.length);
        System.out.println(searchIndex(new int[]{11,9,9,9,8,8,8,7,7,7,6,6,5,4,4,4,4,4,4,4,3,2,1,1,0,0}, 0));
    }
}
