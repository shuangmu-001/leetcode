package com.leetcode.dp;

import java.util.Arrays;
import java.util.TreeSet;

import static com.leetcode.Utils.printTwoArrays;

/**
 * @author wcl
 * @date 3:30 PM 2019/12/13
 * TODO {@link "https://leetcode.com/problems/make-array-strictly-increasing/"}
 */
public class MakeArrayStrictlyIncreasing {
    /**
     * Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.
     *
     * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].
     *
     * If there is no way to make arr1 strictly increasing, return -1.
     *
     * Example 1:
     *  Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
     *  Output: 1
     *  Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
     *
     * Example 2:
     *  Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
     *  Output: 2
     *  Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
     *
     * Example 3:
     * Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
     * Output: -1
     * Explanation: You can't make arr1 strictly increasing.
     *
     * Constraints:
     *
     * 1 <= arr1.length, arr2.length <= 2000
     * 0 <= arr1[i], arr2[i] <= 10^9
     */
    public static int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int arr1Len = arr1.length;
        if(arr1Len == 1) {
            return 0;
        }
        Arrays.sort(arr2);
        int arr2Len = arr2.length;

        int[][] dp = new int[arr1Len + 1][arr1Len + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = Integer.MIN_VALUE;
        for (int j = 1; j < dp.length; j++) {
            for (int i = 0; i <= j; i++) {
                if (arr1[j - 1] > dp[i][j - 1]) {
                    dp[i][j] = arr1[j - 1];
                }

                if (i > 0 ) {
                    int index = getIndex(arr2, dp[i - 1][j - 1]);
                    if(index < arr2Len - 1) {
                        int i1 = arr2[index];
                        if( i1 == dp[i - 1][j - 1]) {
                            i1 = arr2[index + 2];
                        }
                        dp[i][j] = Math.min(dp[i][j], i1);
                    }

                }
                printTwoArrays(dp);
                if (j == dp.length - 1 && dp[i][j] != Integer.MAX_VALUE) {
                    return i;
                }
            }
        }
        return -1;
    }



    public static int getIndex(int[]arr,int key){
        int low=0;
        int high=arr.length-1;

        while(low<=high){
            int mid=low + (high-low)/2;
            if(arr[mid]<key){
                low=mid+1;
            }else if(arr[mid]>key){
                high=mid-1;
            }else{
                return mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
//        System.out.println(makeArrayIncreasing(new int[]{1,5,3,6,7}, new int[]{1,3,2,4}));
//        System.out.println(makeArrayIncreasing(new int[]{1,5,3,6,7}, new int[]{4,3,1}));
//        System.out.println(makeArrayIncreasing2(new int[]{1,5,3,6,7}, new int[]{1,6,3,3}));
//        System.out.println(makeArrayIncreasing2(new int[]{1,6,7,3}, new int[]{3,4,5}));
        System.out.println(makeArrayIncreasing2(new int[]{7,6,3}, new int[]{3,4,5}));

        System.out.println("---------------二分查找分割线-------------------");
        System.out.println(getIndex(new int[]{1,2,3,4}, 3));
        System.out.println(getIndex(new int[]{1,2,3,4}, 0));
        System.out.println(getIndex(new int[]{1,2,3,4}, 5));
        System.out.println(getIndex(new int[]{1,2,3,4,5}, 4));

    }
    public static int makeArrayIncreasing2(int[] arr1, int[] arr2) {
        int arr1Len = arr1.length;
        if(arr1Len == 1) {
            return 0;
        }
        TreeSet<Integer> ts = new TreeSet<>();
        for (int value : arr2) {
            ts.add(value);
        }
        int[][] dp = new int[arr1Len + 1][arr1Len + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        dp[0][0] = Integer.MIN_VALUE;
        System.out.println(ts.higher(Integer.MIN_VALUE));
        for (int j = 1; j < dp.length; j++) {
            for (int i = 0; i <= j; i++) {
                if (arr1[j - 1] > dp[i][j - 1]) {
                    dp[i][j] = arr1[j - 1];
                }
                if (i > 0 && ts.higher(dp[i - 1][j - 1]) != null) {
                    dp[i][j] = Math.min(dp[i][j], ts.higher(dp[i - 1][j - 1]));
                }
                printTwoArrays(dp);
                if (j == dp.length - 1 && dp[i][j] != Integer.MAX_VALUE) {
                    return i;
                }
            }
        }
        return -1;
    }
}
