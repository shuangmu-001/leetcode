package com.question.day07;

import com.Test;
import com.Utils;

/**
 * @author zms
 * @date 8:54 下午 2021/9/25
 */
public class Code01MaxAndValue implements Test {

    // 给定一个正数组成的数组，长度一定大于1，想知道数组中哪两个数&的结果最大，返回这个最大结果
    // 时间复杂度O(n) 空间复杂度O(1)
    public int maxAndValue(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        // 正数数组，所以符号位一定位0
        int ans = 0;
        // 前一次的删选结果
        int pre = arr.length;
        for (int move = 30; move >= 0; move--) {
            // 当某一位 为1的个数
            // == 2的时候结果就是这两个数&
            // > 2 的时候，继续下一位的删选
            // < 2 的时候，继续下一位的删选，但是要从之前的数据中进行删选
            // 遍历到的当前索引
            int index = 0;
            // 下一次交换的位置
            int cur = 0;
            while (index < pre) {
                int bit = (arr[index] >> move) & 1;
                if (bit == 1) {
                    swap(arr, cur++, index);
                }
                index++;
            }
            if (cur == 2) {
                return arr[0] & arr[1];
            }
            if (cur > 2) {
                ans |= (1 << move);
                pre = cur;
            }
        }

        return ans;
    }

    // 数组元素进行交换
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int maxAndValue01(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int ans = -1;
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                ans = Math.max(ans, arr[i] & arr[j]);
            }
        }
        return ans;
    }

    @Override
    public void test(int n) {
        int[] arr = genRandomArr(n);
        int ans1 = maxAndValue01(arr);
        int ans2 = maxAndValue(arr);
        if (ans1 != ans2) {
            System.out.println("Oops!");
            Utils.printArrays(arr);
            for (int i : arr) {
                System.out.printf("%31s : i \n", Integer.toBinaryString(i));
            }
            System.out.println(ans1);
            System.out.println(ans2);
            throw new RuntimeException();
        }
    }
}
