package com.algs.sort;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 12:02 PM 2020/4/13
 */
public class Sort {

    public static void bubbleSort(int[] nums) {
        if(nums == null || nums.length < 2) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                if(nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
            }
        }
    }

    public static void selectSort(int[] nums) {
        if(nums == null || nums.length < 2) {
            return;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i; j < nums.length; j++) {
                if(nums[i] > nums[j]) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }
    }

    public static void insertionSort(int[] nums) {
        if(nums == null || nums.length < 2) {
            return;
        }
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int index = i - 1;
            while(index >= 0 && nums[index] > temp) {
                nums[index + 1] = nums[index];
                index--;
            }
            nums[index + 1] = temp;
        }
    }

    public static void mergeSort(int[] nums) {
        if(nums == null || nums.length < 2) {
            return;
        }
        sort(nums, 0, nums.length - 1);
    }

    public static void sort(int[] nums, int start, int end) {
        if(start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(nums, start, mid);
        sort(nums, mid + 1, end);
        merge(nums, start, mid + 1, end);
    }

    public static void merge(int[] nums, int l, int r, int end) {
        int[] arr = new int[end - l + 1];
        int index = 0;
        int lstart = l;
        int rStart = r;
        while(l < rStart && r <= end) {
            if(nums[l] < nums[r]) {
                arr[index++] = nums[l++];
            } else {
                arr[index++] = nums[r++];
            }
        }
        while(l < rStart) {
            arr[index++] = nums[l++];
        }
        while(r <= end) {
            arr[index++] = nums[r++];
        }
        for (int num : arr) {
            nums[lstart++] = num;
        }
    }

    public static void swap(int[] nums, int k, int j) {
        int temp = nums[k];
        nums[k] = nums[j];
        nums[j] = temp;
    }

    public static void makeHeap(int[] nums, int start, int end) {
        int temp = nums[start];
        for (int i = start * 2 + 1; i < end; i = i * 2 + 1) {
            if (i + 1 < end && nums[i] < nums[i + 1]) {
                i++;
            }
            if (nums[i] > temp) {
                swap(nums, start, i);
                start = i;
            } else {
                break;
            }
        }
    }

    public static void heapSort(int[] nums) {
        // make max heap
        for (int i = nums.length - 1; i >= 0; i--) {
            int k = i * 2 + 1;
            if(k < nums.length) {
                int temp = nums[i];
                if(k + 1 < nums.length && nums[k] < nums[k + 1]) {
                    k++;
                }
                if(nums[k] > temp) {
                    swap(nums, i, k);
                }
            }
        }
        Utils.printArrays(nums);
        // heap sort
        for (int i = nums.length - 1; i >= 0; i--) {
            swap(nums, 0, i);
            makeHeap(nums, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,2,4,1,4};
//        selectSort(nums);
//        bubbleSort(nums);
//        insertionSort(nums);
//        mergeSort(nums);
        heapSort(nums);
        Utils.printArrays(nums);
    }
}
