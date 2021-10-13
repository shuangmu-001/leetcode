package com.leetcode.dp.game;

import java.util.Set;
import java.util.TreeSet;

/**
 * TODO 怎么合并先手和后手的递归
 * <a href="https://leetcode-cn.com/problems/stone-game-iv/">stone-game-iv</a>
 *
 * @author zms
 * @date 3:52 下午 2021/10/13
 */
public class StoneGameIV {
    /**
     * Alice 和 Bob 两个人轮流玩一个游戏，Alice 先手。
     * 一开始，有 n个石子堆在一起。每个人轮流操作，正在操作的玩家可以从石子堆里拿走 任意非零 平方数个石子。
     * 如果石子堆里没有石子了，则无法操作的玩家输掉游戏。
     * 给你正整数n，且已知两个人都采取最优策略。如果 Alice 会赢得比赛，那么返回True，否则返回False。
     * <p>
     * 示例 1：
     * 输入：n = 1
     * 输出：true
     * 解释：Alice 拿走 1 个石子并赢得胜利，因为 Bob 无法进行任何操作。
     * <p>
     * 示例 2：
     * 输入：n = 2
     * 输出：false
     * 解释：Alice 只能拿走 1 个石子，然后 Bob 拿走最后一个石子并赢得胜利（2 -> 1 -> 0）。
     * <p>
     * 示例 3：
     * 输入：n = 4
     * 输出：true
     * 解释：n 已经是一个平方数，Alice 可以一次全拿掉 4 个石子并赢得胜利（4 -> 0）。
     * <p>
     * 示例 4：
     * 输入：n = 7
     * 输出：false
     * 解释：当 Bob 采取最优策略时，Alice 无法赢得比赛。
     * 如果 Alice 一开始拿走 4 个石子， Bob 会拿走 1 个石子，然后 Alice 只能拿走 1 个石子，Bob 拿走最后一个石子并赢得胜利（7 -> 3 -> 2 -> 1 -> 0）。
     * 如果 Alice 一开始拿走 1 个石子， Bob 会拿走 4 个石子，然后 Alice 只能拿走 1 个石子，Bob 拿走最后一个石子并赢得胜利（7 -> 6 -> 2 -> 1 -> 0）。
     * <p>
     * 示例 5：
     * 输入：n = 17
     * 输出：false
     * 解释：如果 Bob 采取最优策略，Alice 无法赢得胜利。
     * <p>
     * 提示：1 <= n <= 10^5
     */
    public static boolean winnerSquareGame01(int n) {
        Set<Integer> sqrtMap = sqrtMap(n);
        return before01(n, sqrtMap);
    }

    public static boolean before01(int n, Set<Integer> sqrtMap) {
        if (n <= 0) {
            return false;
        }
        if (sqrtMap.contains(n)) {
            return true;
        }
        boolean ans = false;
        for (Integer integer : sqrtMap) {
            if (integer > n) {
                break;
            }
            ans |= after01(n - integer, sqrtMap);
        }
        return ans;
    }

    private static boolean after01(int n, Set<Integer> sqrtMap) {
        if (n <= 0) {
            return true;
        }
        if (sqrtMap.contains(n)) {
            return false;
        }
        boolean ans = true;
        for (Integer integer : sqrtMap) {
            if (integer > n) {
                break;
            }
            ans &= before01(n - integer, sqrtMap);
        }
        return ans;
    }

    public static Set<Integer> sqrtMap(int n) {
        Set<Integer> sqrtMap = new TreeSet<>();
        int sqrt = 1;
        int index = 1;
        while (sqrt <= n) {
            sqrtMap.add(sqrt);
            index++;
            sqrt = index * index;
        }
        return sqrtMap;
    }

    public static boolean winnerSquareGame02(int n) {
        Set<Integer> sqrtMap = sqrtMap(n);
        Boolean[] fMap = new Boolean[n + 1];
        Boolean[] gMap = new Boolean[n + 1];
        return before02(n, sqrtMap, fMap, gMap);
    }

    public static boolean before02(int n, Set<Integer> sqrtMap, Boolean[] fMap, Boolean[] gMap) {
        if (fMap[n] != null) {
            return fMap[n];
        }
        if (n <= 0) {
            fMap[0] = false;
            return false;
        }
        if (sqrtMap.contains(n)) {
            fMap[n] = true;
            return true;
        }
        boolean ans = false;
        for (Integer integer : sqrtMap) {
            if (integer > n) {
                break;
            }
            ans |= after02(n - integer, sqrtMap, fMap, gMap);

        }
        fMap[n] = ans;
        return ans;
    }

    private static boolean after02(int n, Set<Integer> sqrtMap, Boolean[] fMap, Boolean[] gMap) {
        if (gMap[n] != null) {
            return gMap[n];
        }
        if (n <= 0) {
            gMap[0] = true;
            return true;
        }
        if (sqrtMap.contains(n)) {
            gMap[n] = false;
            return false;
        }
        boolean ans = true;
        for (Integer integer : sqrtMap) {
            if (integer > n) {
                break;
            }
            ans &= before02(n - integer, sqrtMap, fMap, gMap);
        }
        gMap[n] = ans;
        return gMap[n];
    }

    public static boolean winnerSquareGame03(int n) {
        Set<Integer> sqrtMap = sqrtMap(n);
        boolean[] fMap = new boolean[n + 1];
        boolean[] gMap = new boolean[n + 1];
        fMap[1] = true;
        for (int i = 2; i <= n; i++) {
            if (sqrtMap.contains(i)) {
                fMap[i] = true;
                gMap[i] = false;
                continue;
            }
            gMap[i] = true;
            for (Integer integer : sqrtMap) {
                if (integer > i || (fMap[i] && !gMap[i])) {
                    break;
                }
                fMap[i] |= gMap[i - integer];
                gMap[i] &= fMap[i - integer];
            }
        }
        return fMap[n];
    }

    public static void main(String[] args) {
        System.out.println(winnerSquareGame03(1));
        System.out.println(!winnerSquareGame03(2));
        System.out.println(winnerSquareGame03(4));
        System.out.println(!winnerSquareGame03(7));
        System.out.println(!winnerSquareGame03(17));
        System.out.println(winnerSquareGame03(1121));
        System.out.println(winnerSquareGame03(91212));
    }
}
