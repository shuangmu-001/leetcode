package com.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zms
 * @date 2:45 PM 2020/4/30
 * TODO <a href="https://leetcode.com/problems/diagonal-traverse-ii/">
 *     Diagonal Traverse II</a>
 * @see DiagonalTraverse
 */
public class DiagonalTraverseII {
    /**
     * Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.
     *
     * Example 1:
     * Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
     * Output: [1,4,2,7,5,3,8,6,9]
     *
     * Example 2:
     * Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
     * Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
     *
     * Example 3:
     * Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
     * Output: [1,4,2,5,3,8,6,9,7,10,11]
     *
     * Example 4:
     * Input: nums = [[1,2,3,4,5,6]]
     * Output: [1,2,3,4,5,6]
     *
     * Constraints:
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i].length <= 10^5
     * 1 <= nums[i][j] <= 10^9
     * There at most 10^5 elements in nums.
     */
    public int[] findDiagonalOrder1(List<List<Integer>> nums) {
        if(nums == null || nums.isEmpty()) {
            return new int[0];
        }
        List<Integer> arr = new ArrayList<>();
        int m = nums.size();
        int count = 0;
        int curRow = 0;
        int row = 0;
        int column = 0;
        int curColumn = 0;
        while(count < m) {
            count = 0;
            while(row >= 0) {
                List<Integer> num = nums.get(row);
                if(num.size() <= column) {
                    count++;
                } else {
                    arr.add(num.get(column));
                }
                column++;
                row--;
            }
            if(curRow == m - 1) {
                row = m - 1;
                curColumn ++;
                column = curColumn;
            } else {
                curRow++;
                row = curRow;
                column = curColumn;
            }
        }
        int[] res = new int[arr.size()];
        int index = 0;
        for (int num : arr) {
            res[index++] = num;
        }
        return res;
    }

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        if(nums == null || nums.isEmpty()) {
            return new int[0];
        }
        List<Tuples> arr = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> integers = nums.get(i);
            if(integers != null) {
                for (int j = 0; j < integers.size(); j++) {
                    int val = integers.get(i);
                    arr.add(new Tuples(val, i, i + j));
                }
            }

        }
        Collections.sort(arr);
        int[] res = new int[arr.size()];
        int index = 0;
        for (Tuples num : arr) {
            res[index++] = num.val;
        }
        return res;
    }
    static class Tuples implements Comparable<Tuples> {

        Integer val;
        Integer index;
        Integer sum;

        public Tuples(int val, int index, int sum) {
            this.val = val;
            this.index = index;
            this.sum = sum;
        }

        @Override
        public int compareTo(Tuples o) {
            if(this.sum < o.sum) {
                return -1;
            } else if(this.sum.equals(o.sum)) {
                return o.index.compareTo(this.index);
            } else {
                return 1;
            }

        }
    }

    // TODO 方法三: 统计每层的个数

}
