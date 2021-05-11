package com.data.structure.heap;

import com.leetcode.Utils;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author wcl
 * @date 6:01 下午 2021/5/10
 */
public class Heap {

    private final int[] heap;
    private final int limit;
    private int heapSize;

    public Heap(int limit) {
        this.heap = new int[limit];
        this.limit = limit;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == limit;
    }

    public void push(int val) {
        if (isFull()) {
            throw new RuntimeException("heap is full");
        }
        heap[heapSize] = val;
        heapInsert(heap, heapSize++);
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("heap is empty");
        }
        int ans = heap[0];
        swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return ans;
    }

    private static void heapInsert(int[] arr, int index) {
        while (index > 0 && arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                return;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> integers = new PriorityQueue<>();
        Heap heap01 = new Heap(10);
        for (int i = 0; i < 10; i++) {
            int num = new Random().nextInt(1000);
            heap01.push(num);
            integers.offer(num);
            System.out.println(integers);
//            Utils.printArrays(heap01.heap);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(integers.poll());
            System.out.println(integers);
//            System.out.println(heap01.pop());
//            Utils.printArrays(heap01.heap);
        }
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }
}
