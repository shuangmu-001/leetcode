package com.leetcode.search;

import java.util.List;

/**
 * @author zms
 * @date 3:19 PM 2020/4/21
 * <a href="https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/530/week-3/3306/">
 * Leftmost Column with at Least a One</a>
 */
public class LeftmostColumnWithAtLeastAOne {
    /**
     * (This problem is an interactive problem.)
     * A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.
     * Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn't exist, return -1.
     * You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:
     *      1、BinaryMatrix.get(x, y) returns the element of the matrix at index (x, y) (0-indexed).
     *      2、BinaryMatrix.dimensions() returns a list of 2 elements [n, m], which means the matrix is n * m.
     * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.
     * For custom testing purposes you're given the binary matrix mat as input in the following four examples. You will not have access the binary matrix directly.
     * Example 1:
     * Input: mat = [[0,0],[1,1]]
     * Output: 0
     *
     * Example 2:
     * Input: mat = [[0,0],[0,1]]
     * Output: 1
     *
     * Example 3:
     * Input: mat = [[0,0],[0,0]]
     * Output: -1
     *
     * Example 4:
     * Input: mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
     * Output: 1
     *
     * Constraints:
     * 1 <= mat.length, mat[i].length <= 100
     * mat[i][j] is either 0 or 1.
     * mat[i] is sorted in a non-decreasing way.
     */
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        if(binaryMatrix == null) {
            return -1;
        }
        List<Integer> dimensions = binaryMatrix.dimensions();
        int n = dimensions.get(0);
        int y = dimensions.get(1) - 1;
        int x = 0;
        int min = -1;
        while(y >= 0 && x < n) {
            int val = binaryMatrix.get(x, y);
            if(val == 0) {
                x++;
            } else if(val == 1){
                min = y;
                y--;
            }
        }
        return min;
    }

    interface BinaryMatrix {
        public int get(int x, int y);

        public List<Integer> dimensions();
    }

    ;
}
