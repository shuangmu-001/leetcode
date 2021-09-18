package com.leetcode.search;


import java.util.Arrays;

/**
 * @author wcl
 * @date 2:26 PM 2020/4/3
 * <a href="https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/">
 *     The K Weakest Rows in a Matrix</a>
 */
public class TheKWeakestRowsInAMatrix {
    /**
     * Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians),
     * return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.
     * A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j,
     * or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row,
     * that is, always ones may appear first and then zeros.
     *
     * Example 1:
     * Input: mat =
     * [[1,1,0,0,0],
     *  [1,1,1,1,0],
     *  [1,0,0,0,0],
     *  [1,1,0,0,0],
     *  [1,1,1,1,1]],
     * k = 3
     * Output: [2,0,3]
     * Explanation:
     * The number of soldiers for each row is:
     * row 0 -> 2
     * row 1 -> 4
     * row 2 -> 1
     * row 3 -> 2
     * row 4 -> 5
     * Rows ordered from the weakest to the strongest are [2,0,3,1,4]
     * Example 2:
     *
     * Input: mat =
     * [[1,0,0,0],
     *  [1,1,1,1],
     *  [1,0,0,0],
     *  [1,0,0,0]],
     * k = 2
     * Output: [0,2]
     * Explanation:
     * The number of soldiers for each row is:
     * row 0 -> 1
     * row 1 -> 4
     * row 2 -> 1
     * row 3 -> 1
     * Rows ordered from the weakest to the strongest are [0,2,3,1]
     *
     *
     * Constraints:
     *      m == mat.length
     *      n == mat[i].length
     *      2 <= n, m <= 100
     *      1 <= k <= m
     *      matrix[i][j] is either 0 or 1.
     */
    public static int[] kWeakestRows(int[][] mat, int k) {
        k = Math.min(k, mat.length);
        int[] res = new int[k];
        int[] person = new int[mat.length];
        for (int i = 0; i < mat.length; i++) {
            int index = searchIndex(mat[i],0);
            index = index < 0 ? mat[i].length : index;
            int len = Math.min(i, k - 1);
            if(i >= k && person[res[k - 1]] <= index ) {
                continue;
            }
            person[i] = index;
            copy(res, len, i, index, person);
        }

        return res;
    }
    private static void copy(int[] res, int end, int index, int target, int[] person) {
        if(end == 0) {
            res[0] = index;
            return;
        }
        end = Math.min(end , res.length);
        for (int i = end - 1; i >=0; i--) {
            if(person[res[i]] <= target ) {
                res[i + 1] = index;
                return;
            } else if(person[res[i]] > target) {
                res[i + 1] = res[i];
            }
        }
        res[0] = index;
    }
    private static int searchIndex(int[] num, int k) {
        int left = 0;
        int right = num.length - 1;
        int mid = 0;
        while(left <= right) {
            mid = (left + right) / 2;
            if(num[mid] == k) {
                break;
            }  else if(num[mid] > k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if(num[mid] == 0) {
            for (int i = mid - 1; i >= 0; i--) {
                if(num[i] != 0) {
                    return i + 1;
                }
            }
            return 0;
        }
        return ~left;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(kWeakestRows(new int[][]{
//                {1, 0, 0, 0},
//                {1, 1, 1, 1},
//                {1, 0, 0, 0},
//                {1, 0, 0, 0}
//        }, 5)));
        System.out.println(Arrays.toString(kWeakestRows(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        }, 3)));
    }
}
