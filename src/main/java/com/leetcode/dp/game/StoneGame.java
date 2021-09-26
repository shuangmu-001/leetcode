package com.leetcode.dp.game;


import com.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zms
 * @date 10:18 AM 2020/2/28
 * TODO {@link "https://leetcode.com/problems/stone-game/"}
 * 博弈+区间动态规划
 */
public class StoneGame {
    /**
     * Alex and Lee play a game with piles of stones.
     * There are an even number of piles arranged in a row,
     * and each pile has a positive integer number of stones piles[i].
     * The objective of the game is to end with the most stones.
     * The total number of stones is odd, so there are no ties.
     * Alex and Lee take turns, with Alex starting first.
     * Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.
     * This continues until there are no more piles left, at which point the person with the most stones wins.
     * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
     * <p>
     * Example 1:
     * Input: [5,3,4,5]
     * Output: true
     * Explanation:
     * Alex starts first, and can only take the first 5 or the last 5.
     * Say he takes the first 5, so that the row becomes [3, 4, 5].
     * If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
     * If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
     * This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
     * Note:
     * 2 <= piles.length <= 500
     * piles.length is even.
     * 1 <= piles[i] <= 500
     * sum(piles) is odd.
     */
    // 3,2,10,4
    public static boolean stoneGame(int[] piles) {
        int len = piles.length;
        if (len == 2) {
            return true;
        }
        // dp[i + 1][j + 1] 表示i + 1 到 j + 1 最大值
        int[][] dp = new int[len + 2][len + 2];
        // 不同长度表示接下来谁拿
        for (int size = 1; size <= len; size++) {
            for (int i = 0; i + size <= len; i++) {
                int j = i + size - 1;
                if (((i + j) & 1) != 0) {
                    dp[i + 1][j + 1] = Math.max(piles[i] + dp[i + 2][j + 1], piles[j] + dp[i + 1][j]);
                } else {
                    dp[i + 1][j + 1] = Math.min(-piles[i] + dp[i + 2][j + 1], -piles[j] + dp[i + 1][j]);
                }
            }
        }
        Utils.printTwoArrays(dp);
        return dp[1][len] > 0;
    }

    public static boolean stoneGame1(int[] piles) {
        int len = piles.length;
        if (len == 2) {
            return true;
        }
        // dp[i][j] 表示i 到 j 最大值
        int[][] dp = new int[len + 1][len + 1];
        // 不同长度表示接下来谁拿
        for (int size = 1; size <= len; size++) {
            for (int i = 0; i + size <= len; i++) {
                int j = i + size - 1;
                if (size == 1) {
                    dp[i][j] = piles[i];
                } else {
                    dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
                }

            }
        }
        Utils.printTwoArrays(dp);
        return dp[0][len - 1] > 0;
    }

    public static boolean stoneGame3(int[] piles) {
        int len = piles.length;
        if (len == 2) {
            return true;
        }
        // dp[i][j] 表示i 到 j 最大值
        int[] dp = new int[len];
        // 不同长度表示接下来谁拿
        System.arraycopy(piles, 0, dp, 0, len);
        for (int size = 2; size <= len; size++) {
            int pre = dp[size - 2];
            for (int i = 0; i + size <= len; i++) {
                int j = i + size - 1;
                // dp[i] = Math.max(piles[i] - dp[i + 1], piles[i + size - 1] - dp[i]);
                // dp[0] > 0

                // i的移动会造成j的移动
                int temp = dp[j];
                dp[j] = Math.max(piles[i] - dp[j], piles[j] - pre);
                pre = temp;
            }
            Utils.printArrays(dp);
        }
        return dp[len - 1] > 0;
    }

    public static boolean stoneGame2(int[] piles) {
        result = false;
        aSum = 0;
        map = new HashMap<>();
        keys = new ArrayList<>();
        return stoneGame(piles, 0, piles.length - 1, 1);
    }

    static boolean result = false;
    static int aSum = 0;
    static int lSum = 0;

    static Map<String, Boolean> map = new HashMap<>();
    static List<String> keys = new ArrayList<>();

    public static boolean stoneGame(int[] piles, int left, int right, int depth) {
        keys.add(left + "-" + right);
        if (map.containsKey(left + "-" + right) && map.get(left + "-" + right)) {
            return true;
        }
        if (result) {
            return true;
        }

        if (left > right) {
            result = aSum > 0;
            return result;
        }

        int lPile = piles[left];
        int rPile = piles[left];
        if ((depth & 1) == 0) {
            lPile = -lPile;
            rPile = -rPile;
        }
        aSum += lPile;
        stoneGame(piles, left + 1, right, depth + 1);
        aSum -= lPile;
        aSum += rPile;
        stoneGame(piles, left, right - 1, depth + 1);
        aSum -= rPile;
        map.put(left + "-" + right, result);
        return result;
    }

    public static boolean stoneGame2(int[] piles, int left, int right, int depth) {
        System.out.println(aSum + "   " + lSum);
        if (result) {
            return true;
        }

        if (left > right) {
            result = aSum > lSum;
            return result;
        }

        if ((depth & 1) == 0) {
            lSum += piles[left];
            stoneGame2(piles, left + 1, right, depth + 1);
            lSum -= piles[left];
            lSum += piles[right];
            stoneGame2(piles, left, right - 1, depth + 1);
            lSum -= piles[right];
        } else {
            aSum += piles[left];
            stoneGame2(piles, left + 1, right, depth + 1);
            aSum -= piles[left];
            aSum += piles[right];
            stoneGame2(piles, left, right - 1, depth + 1);
            aSum -= piles[right];
        }

        return result;
    }

    public static void main(String[] args) {
//        System.out.println(stoneGame1(new int[]{5, 3, 4, 5}));
        System.out.println(stoneGame3(new int[]{3, 2, 10, 4}));
        System.out.println(stoneGame1(new int[]{3, 2, 10, 4}));
//        System.out.println(stoneGame(new int[]{3, 10, 4, 2}));
//        System.out.println(stoneGame(new int[]{3, 7, 2, 3}));
//        System.out.println(map);
//        Utils.addLog(keys.toString());
//        System.out.println(stoneGame(new int[]{288,479,157,128,401,125,380,492,493,173,90,88,248,189,136,492,438,65,399,68,213,255,32,98,212,174,2,435,323,6,54,109,133,17,156,152,22,154,289,488,181,464,445,483,247,298,77,386,384,152,225,358,171,274,282,339,388,125,20,363,393,391,444,284,434,233,138,179,140,60,246,266,319,372,446,271,50,120,85,148,233,143,100,322,269,347,370,227,336,230,42,260,251,330,359,477,342,471,102,336,480,441,401,95,22,309,147,412,442,89,435,497,249,315,173,110,350,484,56,227,30,157,204,312,95,319,44,381,422,144,198,283,26,27,465,301,407,357,452,432,228,3,404,404,291,276,336,157,28,109,434,373,356,140,295,492,52,106,294,402,354,378,109,91,51,340,102,393,313,405,146,301,2,487,268,190,6,461,372,457,44,356,272,491,349,232,1,272,154,156,214,275,460,19,289,462,163,455,22,285,464,263,330,3,193,164,52,171,247,314,83,381,239,103,407,181,315,88,147,274,341,354,229,206,298,489,297,4,415,484,295,118,372,499,272,216,248,273,49,391,489,16,266,326,428,346,426,69,354,229,24,482,497,103,112,91,331,210,230,390,183,256,172,473,494,372,249,36,464,486,121,415,273,315,472,125,59,196,334,172,196,148,351,72,99,406,304,96,429,477,453,168,187,399,299,409,483,500,431,306,153,392,386,378,220,128,42,497,104,348,307,465,119,1,220,175,201,413,7,154,423,396,167,170,407,316,26,317,334,426,343,470,70,133,255,338,392,31,45,365,479,440,179,276,68,378,259,196,473,268,204,192,56,195,325,402,342,129,385,210,83,51,64,238,21,358,153,274,375,320,31,458,180,309,134,35,272,386,255,82,370,404,334,367,423,359,398,478,102,185,262,221,337,293,277,464,477,207,312,480,337,140,39,407,41,441,417,189,218,43,369,83,87,215,218,463,127,1,87,105,170,122,87,231,261,317,310,131,201,151,440,38,115,3,347,488,15,225,66,216,351,109,383,421,257,324,271,24,161,493,100,499,329,170,183,139,281,449,81,294,191,388,71,178,279,119,342,477,175,62,148,303,468,368,321,120,208,500,417,468,319,470,486,335,389,129,223,59,400,14,461,494,50,208,423,414,148,228,497,30}));
//        Utils.addLog(keys.toString());
//        System.out.println(stoneGame(new int[]{3, 2, 111, 10, 10, 4}));
    }
}
