package com.leetcode.search;

/**
 * @author zms
 * @date 5:01 PM 2020/4/7
 * TODO <a href="https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/">
 *     Kth Smallest Element in a Sorted Matrix</a>
 */
public class KthSmallestElementInASortedMatrix {
    /**
     * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
     *
     * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
     *
     * Example:
     *
     * matrix = [
     *    [ 1,  5,  9],
     *    [10, 11, 13],
     *    [12, 13, 15]
     * ],
     * k = 8,
     *
     * return 13.
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ n2.
     */
    public int kthSmallest(int[][] matrix, int k) {
//        int row = matrix.length - 1, col = 0;
//        while(row >= 0 && col < matrix[0].length){
//            if(matrix[row][col] == target) {
//                return 0;
//            } else if(matrix[row][col] > target) {
//                row --;
//            } else {
//                col++;
//            }
//        }
        return 0;
    }

}
