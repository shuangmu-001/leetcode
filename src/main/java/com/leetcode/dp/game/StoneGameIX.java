package com.leetcode.dp.game;

/**
 * TODO <a href="https://leetcode.com/problems/stone-game-ix/">stone-game-ix</a>
 *
 * @author zms
 * @date 3:52 下午 2021/10/13
 */
public class StoneGameIX {
    /**
     * Alice 和 Bob 再次设计了一款新的石子游戏。现有一行 n 个石子，每个石子都有一个关联的数字表示它的价值。
     * 给你一个整数数组 stones ，其中 stones[i] 是第 i 个石子的价值。
     * Alice 和 Bob 轮流进行自己的回合，Alice 先手。每一回合，玩家需要从 stones中移除任一石子。
     * 如果玩家移除石子后，导致 所有已移除石子 的价值总和 可以被 3 整除，那么该玩家就 输掉游戏 。
     * 如果不满足上一条，且移除后没有任何剩余的石子，那么 Bob 将会直接获胜（即便是在 Alice 的回合）。
     * 假设两位玩家均采用最佳 决策。如果 Alice 获胜，返回 true ；如果 Bob 获胜，返回 false 。
     * <p>
     * 示例 1：
     * 输入：stones = [2,1]
     * 输出：true
     * 解释：游戏进行如下：
     * - 回合 1：Alice 可以移除任意一个石子。
     * - 回合 2：Bob 移除剩下的石子。
     * 已移除的石子的值总和为 1 + 2 = 3 且可以被 3 整除。因此，Bob 输，Alice 获胜。
     * 示例 2：
     * <p>
     * 输入：stones = [2]
     * 输出：false
     * 解释：Alice 会移除唯一一个石子，已移除石子的值总和为 2 。
     * 由于所有石子都已移除，且值总和无法被 3 整除，Bob 获胜。
     * 示例 3：
     * <p>
     * 输入：stones = [5,1,2,4,3]
     * 输出：false
     * 解释：Bob 总会获胜。其中一种可能的游戏进行方式如下：
     * - 回合 1：Alice 可以移除值为 1 的第 2 个石子。已移除石子值总和为 1 。
     * - 回合 2：Bob 可以移除值为 3 的第 5 个石子。已移除石子值总和为 = 1 + 3 = 4 。
     * - 回合 3：Alices 可以移除值为 4 的第 4 个石子。已移除石子值总和为 = 1 + 3 + 4 = 8 。
     * - 回合 4：Bob 可以移除值为 2 的第 3 个石子。已移除石子值总和为 = 1 + 3 + 4 + 2 = 10.
     * - 回合 5：Alice 可以移除值为 5 的第 1 个石子。已移除石子值总和为 = 1 + 3 + 4 + 2 + 5 = 15.
     * Alice 输掉游戏，因为已移除石子值总和（15）可以被 3 整除，Bob 获胜。
     * <p>
     * 提示：
     * 1 <= stones.length <= 10^5
     * 1 <= stones[i] <= 10^4
     */
    public static boolean stoneGameIX01(int[] stones) {
        return before01(stones, 0, 0);
    }

    // index 已经移除的石子
    // sum 已经移除的石子的总和
    public static boolean before01(int[] arr, int index, int sum) {
        if (index == arr.length) {
            return false;
        }
        boolean ans = false;
        for (int i = index; i < arr.length; i++) {
            sum += arr[i];
            if (sum % 3 == 0) {
                sum -= arr[i];
                continue;
            }
            swap(arr, i, index);
            ans |= after01(arr, index + 1, sum);
            swap(arr, i, index);
            sum -= arr[i];
        }
        return ans;
    }

    public static boolean after01(int[] arr, int index, int sum) {
        if (index == arr.length) {
            return false;
        }
        boolean ans = true;
        for (int i = index; i < arr.length; i++) {
            sum += arr[i];
            if (sum % 3 == 0) {
                sum -= arr[i];
                continue;
            }
            swap(arr, i, index);
            ans &= before01(arr, index + 1, sum);
            swap(arr, i, index);
            sum -= arr[i];
        }
        return ans;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean stoneGameIX02(int[] stones) {
        int n = stones.length;
        int[] mods = new int[3];
        for (int stone : stones) {
            mods[stone % 3]++;
        }
        boolean ans = false;
        for (int i = 1; i < 3; i++) {
            mods[i]--;
            ans |= after02(mods, i, n - 1);
            mods[i]++;
        }
        return ans;
    }

    public static boolean before02(int[] mods, int mod, int count) {
        if (count == 0) {
            return false;
        }

        boolean ans = false;
        for (int i = 0; i < 3; i++) {
            if (mods[i] == 0) {
                continue;
            }
            if (mod + i != 3) {
                mods[i]--;
                int newMod = i == 0 ? mod : mod == 1 ? 2 : 1;
                ans |= after02(mods, newMod, count - 1);
                mods[i]++;
            }
        }
        return ans;
    }

    public static boolean after02(int[] mods, int mod, int count) {
        if (count == 0) {
            return false;
        }

        boolean ans = true;
        for (int i = 0; i < 3; i++) {
            if (mods[i] == 0) {
                continue;
            }
            if (mod + i != 3) {
                mods[i]--;
                int newMod = i == 0 ? mod : mod == 1 ? 2 : 1;
                ans &= before02(mods, newMod, count - 1);
                mods[i]++;
            }
        }
        return ans;
    }

//    public static boolean stoneGameIX03(int[] stones) {
//        int n = stones.length;
//        int[] mods = new int[3];
//        for (int stone : stones) {
//            mods[stone % 3]++;
//        }
//        boolean[][][] dp = new boolean[mods[0] + 1][mods[1] + 1][mods[2] + 1];
//        for (int i = 0; i <= mods[0]; i++) {
//            for (int j = 0; j <= mods[1]; j++) {
//                for (int k = 0; k <= mods[2]; k++) {
//                    if (i != 0 || j != 0 || k != 0) {
//                        int num = i + j + k;
//                        if ((num & 1) == 0 && (j + (k << 1)) % 3 == 0) {
//                            dp[i][j][k] = true;
//                        }
//                    }
//                }
//            }
//        }
//
//        return dp[mods[0]][mods[1]][mods[2]];
//    }

    // [25, 18, 26]
    public static void main(String[] args) {
//        System.out.println(stoneGameIX03(new int[]{2, 1}));
//        System.out.println(!stoneGameIX03(new int[]{2}));
//        System.out.println(!stoneGameIX03(new int[]{5, 1, 2, 4, 3}));
        System.out.println(stoneGameIX02(new int[]{1, 1, 7, 10, 8, 17, 10, 20, 2, 10}));
        System.out.println(stoneGameIX02(new int[]{77, 74, 12, 63, 95, 23, 19, 91, 48, 87, 26, 22, 21, 30, 41, 10, 22, 80, 14, 36, 62, 29, 13, 3, 15, 47, 71, 1, 95, 21, 43, 84, 62, 70, 10, 86, 70, 9, 38, 30, 51, 32, 75, 87, 73, 8, 54, 64, 35, 22, 68, 75, 4, 59, 69, 82, 27, 9, 20, 32, 64, 59, 58, 48, 32, 21, 15, 20, 75}));
    }

}
