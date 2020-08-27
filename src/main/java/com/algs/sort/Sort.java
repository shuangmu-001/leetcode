package com.algs.sort;


import java.util.Arrays;

/**
 * @author wcl
 * @date 12:02 PM 2020/4/13
 */
public class Sort {

    public static void swap(int[] nums, int k, int j) {
        int temp = nums[k];
        nums[k] = nums[j];
        nums[j] = temp;
    }

    /**
     * 冒泡排序 Bubble Sort
     * 比较相邻的数据，
     */
    public static void bubbleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                }
            }
        }
    }

    /**
     * 选择排序 Select Sort
     * 拿当前的数据与之后的每个数据进行比较
     */
    public static void selectSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    swap(nums, j, i);
                }
            }
        }
    }

    /**
     * 插入排序 insertion sort
     * 找到当前数据在之前排好序中的位置，
     * 从后向前搜索只要比当前数据大就向后移动一位
     */
    public static void insertionSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int index = i - 1;
            while (index >= 0 && nums[index] > temp) {
                nums[index + 1] = nums[index];
                index--;
            }
            nums[index + 1] = temp;
        }
    }

    /**
     * 归并排序 Merge Sort
     * 两个有序数组合并成一个有序数组
     * 将数组拆成多个有序数组然后两两合并
     */
    public static void mergeSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        sort(nums, 0, nums.length - 1);
    }

    /**
     * 拆分数组
     * 折半拆分
     */
    public static void sort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(nums, start, mid);
        sort(nums, mid + 1, end);
        merge(nums, start, mid + 1, end);
    }

    /**
     * 两个有序数组合并成一个有序数组
     */
    public static void merge(int[] nums, int l, int r, int end) {
        int[] arr = new int[end - l + 1];
        int index = 0;
        int lstart = l;
        int rStart = r;
        while (l < rStart && r <= end) {
            if (nums[l] < nums[r]) {
                arr[index++] = nums[l++];
            } else {
                arr[index++] = nums[r++];
            }
        }
        while (l < rStart) {
            arr[index++] = nums[l++];
        }
        while (r <= end) {
            arr[index++] = nums[r++];
        }
        for (int num : arr) {
            nums[lstart++] = num;
        }
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

    public static void buildMaxHeapify(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            int k = i * 2 + 1;
            if (k < nums.length) {
                int temp = nums[i];
                if (k + 1 < nums.length && nums[k] < nums[k + 1]) {
                    k++;
                }
                if (nums[k] > temp) {
                    swap(nums, i, k);
                }
            }
        }
    }

    public static void heapSort(int[] nums) {
        // make max heap
        buildMaxHeapify(nums);
        // heap sort
        for (int i = nums.length - 1; i >= 0; i--) {
            swap(nums, 0, i);
            makeHeap(nums, 0, i);
        }
    }

    /**
     * 快排 : 分治思想
     * 分解 : 数组A[p...r]被划分为两个(可能为空)子数组A[p...q-1]和A[q+1...r],
     * 使得A[p...q-1]中的每一个元素都小于等于A[q],
     * 使得A[q+1...r]中的每一个元素都大于等于A[q],
     * 下标q是划分过程的一部分
     * 解决 : 通过递归调用快速排序，对数组A[p...q-1]和A[q+1...r]进行排序
     * 合并 : 因为子数组都是原址排序，所以不需要合并操作 : A[p...r]已经有序
     * 关键 : partition 数组划分
     */
    public static void quickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int p, int r) {
        if (p < r) {
            int q = partition(nums, p, r);
            quickSort(nums, p, q - 1);
            quickSort(nums, q + 1, r);
        }
    }

    /**
     * 以nums[r]为pivot element(比较对象) 找到有多少个小于nums[r]的元素然后将nums[r]放到对应的位置
     */
    private static int partition(int[] nums, int p, int r) {
        int x = nums[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (nums[j] <= x) {
                i++;
                swap(nums, i, j);
            }
        }

        swap(nums, i + 1, r);
        return i + 1;
    }

    /**
     * TODO dual-pivot quick sort
     */
    public static void dualPivotQuickSort(int[] nums) {

    }

    /**
     * 数据范围不能太大
     */
    public static void countingSort(int[] nums) {
        // 找到数组中最大的数据
        if (nums == null || nums.length < 2) {
            return;
        }
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(nums[i], max);
        }
        int[] counts = new int[max + 1];
        // 统计个数
        for (int num : nums) {
            counts[num]++;
        }
        int index = 0;
        for (int i = 0; i < max + 1; i++) {
            while (counts[i] > 0) {
                nums[index++] = i;
                counts[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 4, 1, 4};
        selectSort(nums);
        System.out.println("选择排序的结果 : " + Arrays.toString(nums));
        nums = new int[]{3, 2, 2, 4, 1, 4};
        bubbleSort(nums);
        System.out.println("冒泡排序的结果 : " + Arrays.toString(nums));
        nums = new int[]{3, 2, 2, 4, 1, 4};
        insertionSort(nums);
        System.out.println("插入排序的结果 : " + Arrays.toString(nums));
        nums = new int[]{3, 2, 2, 4, 1, 4};
        mergeSort(nums);
        System.out.println("归并排序的结果 : " + Arrays.toString(nums));
        nums = new int[]{3, 2, 2, 4, 1, 4};
        heapSort(nums);
        System.out.println("堆排序的结果 : " + Arrays.toString(nums));
        nums = new int[]{3, 2, 2, 4, 1, 4};
        quickSort(nums);
        System.out.println("快速排序的结果 : " + Arrays.toString(nums));
        nums = new int[]{3, 2, 2, 4, 1, 4};
        countingSort(nums);
        System.out.println("计数排序的结果 : " + Arrays.toString(nums));
    }
}
