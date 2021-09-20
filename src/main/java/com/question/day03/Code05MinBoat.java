package com.question.day03;

import java.util.Arrays;

/**
 * TODO 对数器
 *
 * @author wcl
 * @date 7:45 下午 2021/9/19
 */
public class Code05MinBoat {

    // 给定一个正数数组arr，代表若个人的体重，再给定一个正数limit，表示所有船共同拥有的载重体重
    // 每艘船最多做两人，且不能超过载重，想让所有的人同时过河，并且用最好的分配方法让船尽量少，
    // 返回最少的船数
    public int minBoat(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        int length = arr.length;
        // 表示不是所有的人都能过河
        if (arr[length - 1] > limit) {
            return -1;
        }
        int mid = limit >> 1;
        // 需要首个大于mid的索引
        int index = binarySearch(arr, mid);
        int res = 0;
        int l = index - 1;
        int r = index;
        // 左边剩余的
        int lRemaining = 0;
        while (l >= 0 && r < length) {
            if (arr[r] + arr[l] <= limit) {
                r++;
                l--;
                res++;
            } else {
                lRemaining++;
                l--;
            }
        }
        // 左边 剩余的两两一艘船
        // 右边 剩余的一人一艘船
        return res + ((l + lRemaining + 1) >> 1) + (length - r);
    }

    /**
     * @param arr    有序数组
     * @param target 目标数据
     * @return 返回最近比目标数据大的索引
     */
    public int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            // arr[mid] >= target start 如果有重复，则为最左边的数据，如果不存在，则为首个大于目标的索引
            // arr[mid] <= target end 如果有重复，则为最右边的数据，如果不存在，则为最后一个小于目标的索引
            if (arr[mid] >= target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
