package com.algs.recursion;

/**
 * 暴力递归就是尝试
 * 1、把问题转化为规模缩小了的同类问题的子问题
 * 2、有明确的不需要继续进行递归的条件（base case）
 * 3、有当得到了子问题的结果之后的决策过程
 * 4、不记录每个子问题的解
 * <p>
 * 打印n层汉诺塔从最左移动到最右边的全过程
 * move %d from left to mid \n
 *
 * @author zms
 * @date 3:33 下午 2021/10/11
 */
public class Code01Hanoi {

    public static void hanoi01(int n) {
        if (n == 1) {
            System.out.printf("move %d from left to right \n", 1);
            return;
        }
        leftToRight(n);
    }

    // n层汉诺塔从最左移动到最右边
    private static void leftToRight(int n) {
        if (n == 1) {
            System.out.printf("move %d from left to right \n", 1);
            return;
        }
        // 先将n-1移动到中间
        leftToMid(n - 1);
        // 将n移动到最右
        System.out.printf("move %d from left to right \n", n);
        // 再将n-1移动到最右
        midToRight(n - 1);
    }

    // n层汉诺塔从最左移动到中间
    private static void leftToMid(int n) {
        if (n == 1) {
            System.out.printf("move %d from left to mid \n", 1);
            return;
        }
        // 先将n-1移动到最右
        leftToRight(n - 1);
        // 将n移动到中间
        System.out.printf("move %d from left to mid \n", n);
        // 再将n-1从最右移动到中间
        rightToMid(n - 1);
    }

    // n层汉诺塔从最右移动到中间
    private static void rightToMid(int n) {
        if (n == 1) {
            System.out.printf("move %d from right to mid \n", 1);
            return;
        }
        // 先将n-1移动到最最左
        rightToLeft(n - 1);
        // 将n移动到中间
        System.out.printf("move %d from right to mid \n", n);
        // 再将n-1从最右移动到中间
        leftToMid(n - 1);
    }

    // n层汉诺塔从最右移动到最左
    private static void rightToLeft(int n) {
        if (n == 1) {
            System.out.printf("move %d from right to left \n", 1);
            return;
        }
        // 先将n-1移动到中间
        rightToMid(n - 1);
        // 将n移动到最左
        System.out.printf("move %d from right to left \n", n);
        // 将剩下的n-1从中间移动到最左
        midToLeft(n - 1);
    }

    // n层汉诺塔从中间移动到最左
    private static void midToLeft(int n) {
        if (n == 1) {
            System.out.printf("move %d from mid to left \n", 1);
            return;
        }
        // 先将n-1移动到最右
        midToRight(n - 1);
        // 将n移动到最左
        System.out.printf("move %d from mid to left \n", n);
        // 将剩下的n-1从最右移动到最左
        rightToLeft(n - 1);
    }

    // n层汉诺塔从中间移动到最右
    private static void midToRight(int n) {
        if (n == 1) {
            System.out.printf("move %d from mid to right \n", 1);
            return;
        }
        // 先将n-1移动到最左
        midToLeft(n - 1);
        // 将n移动到最右
        System.out.printf("move %d from mid to right \n", n);
        // 将剩下的n-1从最左移动到最右
        leftToRight(n - 1);
    }

    public static void hanoi02(int n) {
        if (n == 1) {
            System.out.printf("move %d from left to right \n", 1);
            return;
        }
        move(n, "left", "right", "mid");
    }

    public static void move(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.printf("move %d from %s to %s \n", 1, from, to);
            return;
        }
        // 先将 n - 1 从from移动到other
        move(n - 1, from, other, to);
        // 再将 n 从from移动到to
        System.out.printf("move %d from %s to %s \n", n, from, to);
        // 再将 n - 1 从other移动到to
        move(n - 1, other, to, from);
    }


    public static void main(String[] args) {
        System.out.println("=======================");
        hanoi01(3);
        System.out.println("=======================");
        hanoi02(3);
    }
}
