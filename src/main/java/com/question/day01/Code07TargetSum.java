package com.question.day01;


/**
 * 给定一个数组arr，你可以在每个数字之前决定 + 或者 - 但是必须所有数字都参与，在给定一个数target，请问最后算出target的方法数是多少？
 * leetcode 494题
 *
 * @author wcl
 * @date 3:46 下午 2021/9/15
 */
public class Code07TargetSum {

    public static int findTargetSumWay1(int[] arr, int target) {
        return findTargetSumWay1Helper(arr, 0, target);
    }

    public static int findTargetSumWay1Helper(int[] arr, int index, int target) {
        if (index == arr.length) {
            return target == 0 ? 1 : 0;
        }
        return findTargetSumWay1Helper(arr, index + 1, target + arr[index])
                + findTargetSumWay1Helper(arr, index + 1, target - arr[index]);
    }

    public static int findTargetSumWay2(int[] arr, int target) {
        return 0;
    }

    public static void main(String[] args) {


    }

}
