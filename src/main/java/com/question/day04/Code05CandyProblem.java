package com.question.day04;


import com.Test;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/candy/">candy</a>
 *
 * @author wcl
 * @date 7:45 下午 2021/9/19
 */
public class Code05CandyProblem implements Test {
    /**
     * 老师想给孩子们分发糖果，有N个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
     * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
     * 1、每个孩子至少分配到 1 个糖果。
     * 2、评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
     * 那么这样下来，老师至少需要准备多少颗糖果呢？
     * <p>
     * 示例1：
     * 输入：[1,0,2]
     * 输出：5
     * 解释：你可以分别给这三个孩子分发 2、1、2 颗糖果。
     * <p>
     * 示例2：
     * 输入：[1,2,2]
     * 输出：4
     * 解释：你可以分别给这三个孩子分发 1、2、1 颗糖果。
     * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
     */
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int length = ratings.length;
        // 统计所有单调递增的获得的糖果数
        int[] left = new int[length];
        left[0] = 1;
        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        // 统计所有单调递减的获得的糖果数
        int[] right = new int[length];
        right[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < length; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }

    public int candy1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int length = arr.length;
        int ans = 0;
        int l = 0;
        int r = 0;
        // 当 arr[r] < arr[ r + 1];则 r 位置的小孩分到一个糖果
        // l 到 r 是单调递减的
        // l 位置在单调递增的过程冲分配的糖果数
        // TODO 当相邻两个孩子的分数相同怎么处理
        int rating = 1;
        while (l < length && r < length) {
            if (r == length - 1 || arr[r] < arr[r + 1]) {
                if (l != r) {
                    int cur = 1;
                    for (int i = r; i > l; i--) {
                        ans += cur++;
                    }
                    ans += Math.max(cur, rating);
                    rating = 2;
                    l = r = r + 1;
                } else if (r == length - 1) {
                    ans += rating++;
                } else {
                    ans += rating++;
                    l++;
                    r++;
                }
            } else {
                r++;
            }
        }

        return ans;
    }

    @Override
    public void test(int n) {
        int[] ints = genRandomArr(n);
        ints = new int[]{1, 2, 2};
        if (candy(ints) != candy1(ints)) {
            System.out.printf("实验错误:%d:%d\n", candy(ints), candy1(ints));
            System.out.println(Arrays.toString(ints));
        }
    }

    // 补充条件：相邻两个孩子的分数相同，则这两个孩子获得的糖果必须相同
    public int candyPlus(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int length = ratings.length;
        // 统计所有单调递增的获得的糖果数
        int[] left = new int[length];
        left[0] = 1;
        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else if (ratings[i] == ratings[i - 1]) {
                left[i] = left[i - 1];
            } else {
                left[i] = 1;
            }
        }
        // 统计所有单调递减的获得的糖果数
        int[] right = new int[length];
        right[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else if (ratings[i] == ratings[i + 1]) {
                right[i] = right[i + 1];
            } else {
                right[i] = 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < length; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }
}
