package com.question.day06;

/**
 * TODO 怎么将数组划分不同的区间
 * @author zms
 * @date 3:23 下午 2021/9/24
 */
public class Code04MostXORZero {

    // 给定一个数组arr，可以任意切分成若干个不相交的子数组，
    // 其中一定存在一种最优方案，使得切出异或和为0的子数组最多，返回这个最多数量
    public int mostXor(int[] arr) {
        // 暴力怎么解决
        return 0;
    }

    // 假设答案 dp
    // 0 ～ i 的异或和  与 0 ～ j 的异或和相同 ( i < j) 则 i ~ j 的异或和一定等于0
    // 记录每个异或和的最后一个位置
}
