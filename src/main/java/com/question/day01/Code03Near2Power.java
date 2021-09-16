package com.question.day01;


import com.Test;

/**
 * 给定一个非负整数num，如何不用循环语句返回 >=num, 并且离num最近的，2的某次方
 *
 * @author wcl
 * @date 3:46 下午 2021/9/15
 */
public class Code03Near2Power implements Test {

    public static int tableSizeFor(int n) {
        // 防止n本身是2的某次方
        n--;
        // n最右边为1的位数的左边全部变成1
        n |= (n >> 1);
        n |= (n >> 2);
        n |= (n >> 4);
        n |= (n >> 8);
        n |= (n >> 16);
        return (n < 0) ? 1 : n + 1;
    }

    public static int tableSizeFor1(int n) {
        int res = 1;
        while (res < n) {
            res <<= 1;
        }
        return res;
    }

    @Override
    public void test(int n) {
        int i = genRandomNum(n);
        if (tableSizeFor(i) != tableSizeFor1(i)) {
            System.out.printf("当数字为%d时，计算离他最近2的某次幂是%d\n", i, tableSizeFor(i));
        }
    }
}
