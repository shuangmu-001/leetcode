package com.leetcode.dp;


import com.leetcode.Utils;

import java.util.Arrays;
import java.util.Comparator;


/**
 * @author wcl
 * @date 6:00 PM 2020/1/2
 * {@link "https://leetcode.com/problems/maximum-length-of-pair-chain/"}
 * @see LongestIncreasingSubsequence
 * TODO 堆排序
 */
public class MaximumLengthOfPairChain {

    /**
     * You are given n pairs of numbers.
     * In every pair, the first number is always smaller than the second number.
     * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c.
     * Chain of pairs can be formed in this fashion.
     * Given a set of pairs, find the length longest chain which can be formed.
     * You needn't use up all the given pairs. You can select pairs in any order.
     *
     * Example 1:
     *      Input: [[1,2], [2,3], [3,4]] or [[2,3], [3,4],[1,2]]
     *      Output: 2
     *      Explanation: The longest chain is [1,2] -> [3,4]
     *
     * Note:
     *      1、The number of given pairs will be in the range [1, 1000].
     *
     * Runtime: 81 ms, faster than 7.56% of Java online submissions for Maximum Length of Pair Chain.
     * Memory Usage: 64.1 MB, less than 8.33% of Java online submissions for Maximum Length of Pair Chain.
     * @param pairs n pairs of numbers
     * @return length
     */
    public static int findLongestChain1(int[][] pairs) {
        int length = pairs.length;
        int result = 0;
        for (int i = 1; i < length ; i++) {
            for (int j = 0; j < i; j++) {
                if(pairs[i][0] < pairs[j][0]) {
                    int[] temp = pairs[i];
                    pairs[i] = pairs[j];
                    pairs[j] = temp;
                }
            }
        }
        Utils.printTwoArrays(pairs);
        int[][] results = new int[length][length];
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                results[i][i] = results[i - 1][i];
            }
            for (int j = i + 1; j < length; j++) {

                if(pairs[i][1] < pairs[j][0]) {
                    if(i != 0) {
                        results[i][j] = Math.max(results[i][i] + 1, results[i - 1][j]);
                    } else {
                        results[i][j] = results[i][i] + 1;
                    }

                    result = Math.max(result, results[i][j]);
                } else {
                    if(i!=0) {
                        results[i][j] = results[i - 1][j];
                    }
                }
            }
        }
        Utils.printTwoArrays(results);
        return result + 1;
    }

    /**
     * Runtime: 71 ms, faster than 7.82% of Java online submissions for Maximum Length of Pair Chain.
     * Memory Usage: 52.4 MB, less than 8.33% of Java online submissions for Maximum Length of Pair Chain.
     */
    public static int findLongestChain2(int[][] pairs) {
        int length = pairs.length;
        int result = 0;
        int before = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if(pairs[i][1] > pairs[j][0]) {
                    if(pairs[i][1] > pairs[j][1]) {
                        int[] temp = pairs[i];
                        pairs[i] = pairs[j];
                        pairs[j] = temp;
                    }
                } else if(pairs[i][0] > pairs[j][0]) {
                    int[] temp = pairs[i];
                    pairs[i] = pairs[j];
                    pairs[j] = temp;
                }
            }

            if(i == 0) {
                before = pairs[i][1];
            } else if( before < pairs[i][0]) {
                before = pairs[i][1];
                result++;
            }
        }

        return result + 1;
    }

    /**
     * Runtime: 29 ms, faster than 52.78% of Java online submissions for Maximum Length of Pair Chain.
     * Memory Usage: 47.3 MB, less than 41.67% of Java online submissions for Maximum Length of Pair Chain.
     */
    public static int findLongestChain3(int[][] pairs) {
        int length = pairs.length;
        int result = 0;
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if(pairs[i][1] < pairs[j][1]) {
                    int[] temp = pairs[i];
                    pairs[i] = pairs[j];
                    pairs[j] = temp;
                }
            }
        }
        int before = pairs[0][1];
        for (int i = 1; i < length; i++) {
            if(before < pairs[i][0]) {
                before = pairs[i][1];
                result++;
            }
        }
        Utils.printTwoArrays(pairs);

        return result + 1;
    }

    /**
     * 堆排序的所消耗的时间(6ms)小于Arrays.sort排序(13ms)
     */
    public static int findLongestChain(int[][] pairs) {
        int length = pairs.length;
        int result = 0;
//        heapSort(pairs);
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        int before = pairs[0][1];
        for (int i = 1; i < length; i++) {
            if(before < pairs[i][0]) {
                before = pairs[i][1];
                result++;
            }
        }
        Utils.printTwoArrays(pairs);

        return result + 1;
    }

    public static void heapSort(int[][] pairs) {
        int len = pairs.length;
        for(int i = pairs.length - 1; i >= 0; i--) {
            heapify(pairs, i, len);
        }
        for(int i = pairs.length - 1; i > 0; i--) {
            swap(pairs, 0, i);
            heapify(pairs, 0, i);
        }
    }
    public static void swap(int[][] pairs, int i, int j) {
        int[] temp = pairs[i];
        pairs[i] = pairs[j];
        pairs[j] = temp;
    }

    public static void heapify(int[][] pairs, int i, int len) {
       int temp = pairs[i][1];
        for (int k = 2 * i + 1; k < len; k = 2 * k + 1) {
            if (k + 1 < len && pairs[k][1] < pairs[k + 1][1]) {  //如果有右子树,并且右子树大于左子树
                k++;
            }
            if (pairs[k][1] > temp) {
                swap(pairs, i, k);
                i  =  k;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[][] pairs01 = {{1,2},{2,3},{3,4}};
        System.out.println(findLongestChain(pairs01));
        int[][] pairs02 = {{2,3},{3,4},{1,2}};
        System.out.println(findLongestChain(pairs02));
        int[][] pairs03 = {{5,6},{3,4},{1,2}, {1,3}};
        System.out.println(findLongestChain(pairs03));
        int[][] pairs04 = {{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}};
        System.out.println(findLongestChain(pairs04));
    }
}
