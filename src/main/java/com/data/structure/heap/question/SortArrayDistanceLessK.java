package com.data.structure.heap.question;

import java.util.PriorityQueue;

/**
 * @author wcl
 * @date 11:53 上午 2021/5/11
 */
public class SortArrayDistanceLessK {
    /**
     * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好序的话，每个元素移动的距离一定不超过K，
     * 并且K相对于数组长度来说是比较小的
     * 请选择一个合适的排序策略，对这个数组进行排序。
     *
     * @param arr 目标数组
     * @param k   移动的最大距离
     */
    public static void sortArrDistanceLessK(int[] arr, int k) {
        if (k == 0 || arr == null || arr.length < 2) {
            return;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int index = 0;
        int n = Math.min(arr.length - 1, k);
        while (index <= n) {
            queue.offer(arr[index++]);
        }
        int i = 0;
        while (index < arr.length && !queue.isEmpty()) {
            arr[i++] = queue.poll();
            queue.offer(arr[index++]);
        }
        while (!queue.isEmpty()) {
            arr[i++] = queue.poll();
        }
    }

}
