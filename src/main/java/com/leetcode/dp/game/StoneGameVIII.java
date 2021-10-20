package com.leetcode.dp.game;

import com.Utils;

/**
 * <a href="https://leetcode.com/problems/stone-game-viii/">Stone Game VIII</a>
 *
 * @author zms
 * @date 3:42 下午 2021/4/23
 */
public class StoneGameVIII {

    /**
     * Alice 和 Bob 玩一个游戏，两人轮流操作， Alice 先手。
     * 总共有n个石子排成一行。轮到某个玩家的回合时，如果石子的数目 大于 1，他将执行以下操作：
     * 选择一个整数x > 1，并且 移除最左边的x个石子。
     * 将移除的石子价值之 和累加到该玩家的分数中。
     * 将一个 新的石子放在最左边，且新石子的值为被移除石子值之和。
     * 当只剩下 一个石子时，游戏结束。
     * Alice 和 Bob 的 分数之差为(Alice 的分数- Bob 的分数)。Alice 的目标是最大化分数差，Bob 的目标是 最小化分数差。
     * 给你一个长度为 n的整数数组stones，其中stones[i]是 从左边起第i个石子的价值。请你返回在双方都采用 最优 策略的情况下，Alice 和 Bob 的 分数之差 。
     * <p>
     * 示例 1：
     * 输入：stones = [-1,2,-3,4,-5]
     * 输出：5
     * 解释：
     * - Alice 移除最左边的 4 个石子，得分增加 (-1) + 2 + (-3) + 4 = 2 ，并且将一个价值为 2 的石子放在最左边。stones = [2,-5] 。
     * - Bob 移除最左边的 2 个石子，得分增加 2 + (-5) = -3 ，并且将一个价值为 -3 的石子放在最左边。stones = [-3] 。
     * 两者分数之差为 2 - (-3) = 5 。
     * <p>
     * 示例 2：
     * 输入：stones = [7,-6,5,10,5,-2,-6]
     * 输出：13
     * 解释：
     * - Alice 移除所有石子，得分增加 7 + (-6) + 5 + 10 + 5 + (-2) + (-6) = 13 ，并且将一个价值为 13 的石子放在最左边。stones = [13] 。
     * 两者分数之差为 13 - 0 = 13 。
     * <p>
     * 示例 3：
     * 输入：stones = [-10,-12]
     * 输出：-22
     * 解释：
     * - Alice 只有一种操作，就是移除所有石子。得分增加 (-10) + (-12) = -22 ，并且将一个价值为 -22 的石子放在最左边。stones = [-22] 。
     * 两者分数之差为 (-22) - 0 = -22 。
     * <p>
     * <p>
     * 提示：
     * n == stones.length
     * 2 <= n <= 10^5
     * -10^4 <= stones[i] <= 10^4
     */
    public static int stoneGameVIII01(int[] stones) {
        int n = stones.length;
        Integer[] fMap = new Integer[n];
        Integer[] gMap = new Integer[n];
        return before01(stones, 1, fMap, gMap);
    }

    public static int before01(int[] arr, int index, Integer[] fMap, Integer[] gMap) {
        if (index == arr.length) {
            return 0;
        }
        if (fMap[index] != null) {
            return fMap[index];
        }
        int ans = Integer.MIN_VALUE;
        for (int i = index; i < arr.length; i++) {
            int diff = after01(arr, i + 1, fMap, gMap);
            ans = Math.max(ans, diff);
        }
        fMap[index] = ans;
        return ans;
    }

    public static int after01(int[] arr, int index, Integer[] fMap, Integer[] gMap) {
        if (index == arr.length) {
            int sum = 0;
            for (int j : arr) {
                sum += j;
            }
            return sum;
        }
        if (gMap[index] != null) {
            return gMap[index];
        }
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = index; i < arr.length; i++) {
            sum += arr[i];
            int diff = -sum + before01(arr, i + 1, fMap, gMap);
            ans = Math.min(ans, diff);
        }
        gMap[index] = ans;
        return ans;
    }

    public static int stoneGameVIII03(int[] stones) {
        int n = stones.length;
        int[] fMap = new int[n + 1];
        int[] gMap = new int[n + 1];
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        gMap[n] = sum;
        for (int index = n - 1; index > 0; index--) {
            fMap[index] = Integer.MIN_VALUE;
            for (int i = index; i < n; i++) {
                fMap[index] = Math.max(fMap[index], gMap[i + 1]);
            }
            gMap[index] = Integer.MAX_VALUE;
            sum = 0;
            for (int i = index; i < n; i++) {
                sum += stones[i];
                gMap[index] = Math.min(gMap[index], -sum + fMap[i + 1]);
            }
        }
        Utils.printArrays(fMap);
        Utils.printArrays(gMap);
        return fMap[1];
    }

    public static int stoneGameVIII04(int[] stones) {
        int n = stones.length;
        int[] fMap = new int[n + 1];
        int[] gMap = new int[n + 1];
        for (int stone : stones) {
            gMap[n] += stone;
        }
        fMap[n - 1] = gMap[n];
        gMap[n - 1] = -stones[n - 1];
        for (int index = n - 2; index > 0; index--) {
            fMap[index] = Math.max(gMap[index + 1], fMap[index + 1]);
            gMap[index] = Math.min(gMap[index + 1], fMap[index + 1]) - stones[index];
        }
        // gMap[index]    -stones[index] + fMap[index + 1]  -stones[index] + stones[index + 1] + fMap[index + 2]
        // gMap[index - 1]
        // -stones[index - 1] + fMap[index]
        // -stones[index - 1] -stones[index] + fMap[index + 1]
        // -stones[index - 1] -stones[index] - stones[index + 1] + fMap[index + 2]
        return fMap[1];
    }

    public static int stoneGameVIII05(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int max = sum;
        int min = -stones[n - 1];
        for (int index = n - 2; index > 0; index--) {
            int temp = max;
            max = Math.max(max, min);
            min = Math.min(temp, min) - stones[index];
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(stoneGameVIII05(new int[]{-1, 2, -3, 4, -5}) == 5);
        System.out.println(stoneGameVIII05(new int[]{7, -6, 5, 10, 5, -2, -6}) == 13);
        System.out.println(stoneGameVIII05(new int[]{-10, -12}) == -22);
        System.out.println(stoneGameVIII05(new int[]{-10, 12}) == 2);
        System.out.println(stoneGameVIII05(new int[]{-6, 5, 10, 5, -2, -6}) == 8);
        System.out.println(stoneGameVIII05(new int[]{10, 5, -2, -6}) == 8);
        System.out.println(3567 == stoneGameVIII05(new int[]{2666, -9688, 6507, -8558, -4507, -8480, -1760, -5948, -2322, -1213, -7889, -1069, 4082}));
    }

}
