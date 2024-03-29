package com.leetcode.dp.tree;

/**
 * @author zms
 * @date 11:33 AM 2020/2/28
 * TODO {@link "https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/"}
 */
public class MinimumCostTreeFromLeafValues {
    /**
     * Given an array arr of positive integers, consider all binary trees such that:
     * Each node has either 0 or 2 children;
     * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
     * (Recall that a node is a leaf if and only if it has 0 children.)
     * The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
     * Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.
     *
     * Example 1:
     *      Input: arr = [6,2,4]
     *      Output: 32
     *      Explanation:
     *          There are two possible trees.
     *          The first has non-leaf node sum 36, and the second has non-leaf node sum 32.
     *              24            24
     *             /  \          /  \
     *            12   4        6    8
     *            /  \              / \
     *           6    2            2   4
     *
     *
     * Constraints:
     *      2 <= arr.length <= 40
     *      1 <= arr[i] <= 15
     *      It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
     */
    public static int mctFromLeafValues01(int[] arr) {
        int len = arr.length;
        int max = Math.max(arr[0], arr[1]);
        int sum = arr[0] * arr[1];
        for (int i = 2; i < len; i++) {
            if(arr[i] > max) {
                sum += max * arr[i];
                max = arr[i];
            } else if(arr[i] < arr[i - 1]) {
                sum += arr[i - 1] * arr[i];
            } else {
                int first = arr[i];
                int second = arr[i - 1];
                for (int j = i - 1; j >= 0; j--) {
                    if(arr[j] < arr[i]) {
                        second = Math.max(second, arr[j]);
                    } else {
                        first = arr[j];
                        break;
                    }
                }
                sum -= first * second;
                sum += first * arr[i] + second * arr[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(mctFromLeafValues01(new int[]{}));
    }

    public static int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int sum = arr[0] * arr[1];
        for (int i = 0; i < n; i++) {

        }
        return sum;
    }
}
