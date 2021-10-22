package com.leetcode.dp.game;

/**
 * 区间dp
 * <a href="https://leetcode.com/problems/stone-game-v/">stone-game-v</a>
 *
 * @author zms
 * @date 2:38 下午 2021/10/13
 */
public class StoneGameV {
    /**
     * 几块石子 排成一行 ，每块石子都有一个关联值，关联值为整数，由数组 stoneValue 给出。
     * 游戏中的每一轮：Alice 会将这行石子分成两个 非空行（即，左侧行和右侧行）；Bob 负责计算每一行的值，即此行中所有石子的值的总和。
     * Bob 会丢弃值最大的行，Alice 的得分为剩下那行的值（每轮累加）。如果两行的值相等，Bob 让 Alice 决定丢弃哪一行。下一轮从剩下的那一行开始。
     * 只剩下一块石子时，游戏结束。Alice 的分数最初为 0 。 返回 Alice 能够获得的最大分数 。
     * <p>
     * 示例 1：
     * 输入：stoneValue = [6,2,3,4,5,5]
     * 输出：18
     * 解释：在第一轮中，Alice 将行划分为 [6，2，3]，[4，5，5] 。左行的值是 11 ，右行的值是 14 。Bob 丢弃了右行，Alice 的分数现在是 11 。
     * 在第二轮中，Alice 将行分成 [6]，[2，3] 。这一次 Bob 扔掉了左行，Alice 的分数变成了 16（11 + 5）。
     * 最后一轮 Alice 只能将行分成 [2]，[3] 。Bob 扔掉右行，Alice 的分数现在是 18（16 + 2）。游戏结束，因为这行只剩下一块石头了。
     * <p>
     * 示例 2：
     * 输入：stoneValue = [7,7,7,7,7,7,7]
     * 输出：28
     * <p>
     * 示例 3：
     * 输入：stoneValue = [4]
     * 输出：0
     * <p>
     * 提示：
     * 1 <= stoneValue.length <= 500
     * 1 <= stoneValue[i] <= 10^6
     */
    public static int stoneGameV01(int[] stoneValue) {
        if (stoneValue == null || stoneValue.length <= 1) {
            return 0;
        }
        int n = stoneValue.length;
        int[] sums = preSum(stoneValue);
        return process01(stoneValue, 0, n - 1, sums);
    }

    private static int[] preSum(int[] arr) {
        int n = arr.length;
        // 前n项和
        int[] sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + arr[i];
        }
        return sums;
    }

    public static int process01(int[] arr, int l, int r, int[] sums) {
        if (l == r) {
            return 0;
        }
        int ans = 0;
        // 以i界，将数组分成两部分
        for (int i = l; i < r; i++) {
            int lSum = sums[i + 1] - sums[l];
            int rSum = sums[r + 1] - sums[i + 1];
            if (lSum > rSum) {
                ans = Math.max(ans, rSum + process01(arr, i + 1, r, sums));
            } else if (lSum < rSum) {
                ans = Math.max(ans, lSum + process01(arr, l, i, sums));
            } else {
                int left = lSum + process01(arr, l, i, sums);
                int right = lSum + process01(arr, i + 1, r, sums);
                ans = Math.max(ans, Math.max(left, right));
            }
        }
        return ans;
    }

    public static int stoneGameV02(int[] stoneValue) {
        if (stoneValue == null || stoneValue.length <= 1) {
            return 0;
        }
        int n = stoneValue.length;
        int[] sums = preSum(stoneValue);
        Integer[][] dp = new Integer[n][n];
        return process02(stoneValue, 0, n - 1, sums, dp);
    }

    // 记忆化搜索
    public static int process02(int[] arr, int l, int r, int[] sums, Integer[][] dp) {
        if (dp[l][r] != null) {
            return dp[l][r];
        }
        if (l == r) {
            dp[l][r] = 0;
            return dp[l][r];
        }
        int ans = 0;
        // 以i界，将数组分成两部分
        for (int i = l; i < r; i++) {
            int lSum = sums[i + 1] - sums[l];
            int rSum = sums[r + 1] - sums[i + 1];
            if (lSum > rSum) {
                ans = Math.max(ans, rSum + process02(arr, i + 1, r, sums, dp));
            } else if (lSum < rSum) {
                ans = Math.max(ans, lSum + process02(arr, l, i, sums, dp));
            } else {
                int left = lSum + process02(arr, l, i, sums, dp);
                int right = lSum + process02(arr, i + 1, r, sums, dp);
                ans = Math.max(ans, Math.max(left, right));
            }
        }
        dp[l][r] = ans;
        return ans;
    }

    // 记忆化搜索转dp
    public static int stoneGameV03(int[] stoneValue) {
        if (stoneValue == null || stoneValue.length <= 1) {
            return 0;
        }
        int n = stoneValue.length;
        int[] sums = preSum(stoneValue);
        int[][] dp = new int[n][n];
        for (int len = 1; len < n; len++) {
            for (int l = 0; l + len < n; l++) {
                int r = l + len;
                for (int i = l; i < r; i++) {
                    int lSum = sums[i + 1] - sums[l];
                    int rSum = sums[r + 1] - sums[i + 1];
                    if (lSum > rSum) {
                        dp[l][r] = Math.max(dp[l][r], rSum + dp[i + 1][r]);
                    } else if (lSum < rSum) {
                        dp[l][r] = Math.max(dp[l][r], lSum + dp[l][i]);
                    } else {
                        int left = lSum + dp[l][i];
                        int right = rSum + dp[i + 1][r];
                        dp[l][r] = Math.max(dp[l][r], Math.max(left, right));
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    // 斜率优化
    public static int stoneGameV04(int[] stoneValue) {
        if (stoneValue == null || stoneValue.length <= 1) {
            return 0;
        }
        int n = stoneValue.length;
        int[] sums = preSum(stoneValue);
        int[][] dp = new int[n][n];
        // left[l][mid]  l 到 mid 的所有都小于 mid 到r的和
        // left[l][mid] ==>  dp[l][r] = Math.max(dp[l][r], lSum + dp[l][i]);
        int[][] left = new int[n][n];
        // right[mid][r] l 到 mid 的所有都大于 mid 到r的和
        // right[mid][l] ==> Math.max(dp[l][r], rSum + dp[i + 1][r]);
        int[][] right = new int[n][n];
        for (int i = 0; i < n; i++) {
            left[i][i] = stoneValue[i];
            right[i][i] = stoneValue[i];
        }
        for (int len = 1; len < n; len++) {
            for (int l = 0; l + len < n; l++) {
                int r = l + len;
                int sum = sums[r + 1] - sums[l];
                // l 到 r 中总能找到 mid 使得 sum(l..mid) < sum (mid + 1, r) && sum(l..mid + 1) > sum(mid + 2, r);
                int i = search(sums, l, r);
                int half = sums[i + 1] - sums[l];
                if((half << 1) == sum) {
                    dp[l][r] = Math.max(left[l][i], right[i + 1][r]);
                } else {
                    dp[l][r] = Math.max(i == l ? 0 : left[l][i - 1], i == r ? 0 : right[i + 1][r]);
                }
                left[l][r] = Math.max(left[l][r - 1], dp[l][r] + sum);
                right[l][r] = Math.max(right[l + 1][r], dp[l][r] + sum);
            }
        }
        return dp[0][n - 1];
    }

    private static int search(int[] pre, int l, int r) {
        int sum = pre[r+1] - pre[l], L = l;
        while(l < r) {
            int m = l + ((r - l) >> 1);
            if(((pre[m+1] - pre[L]) << 1) >= sum) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(stoneGameV04(new int[]{6, 2, 3, 4, 5, 5}));
        System.out.println(stoneGameV04(new int[]{7, 7, 7, 7, 7, 7, 7}));
    }
}
