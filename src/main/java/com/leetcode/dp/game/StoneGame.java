package com.leetcode.dp.game;


/**
 * @author wcl
 * @date 10:18 AM 2020/2/28
 * TODO {@link "https://leetcode.com/problems/stone-game/"}
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
    public static boolean stoneGame1(int[] piles) {
        int length = piles.length;
        if (length == 2) {
            return true;
        }
        int left = 0;
        int right = length - 1;
        int aSum = 0;
        int lSum = 0;
        for (int i = 0; i < length; i++) {
            int max;
            // 3,2,10,4
            if (piles[left] > piles[right]) {
                max = piles[left];
                left++;
            } else {
                max = piles[right];
                right--;
            }
            if ((i & 1) == 0) {
                aSum += max;
            } else {
                lSum += max;
            }
        }

        return aSum > lSum;
    }

    public static boolean stoneGame(int[] piles) {
        return stoneGame(piles, 0, piles.length - 1, 1);
    }

    static boolean result = false;
    static int aSum = 0;
    static int lSum = 0;

    public static boolean stoneGame(int[] piles, int left, int right, int depth) {
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
            stoneGame(piles, left + 1, right, depth + 1);
            lSum -= piles[left];
            lSum += piles[right];
            stoneGame(piles, left, right - 1, depth + 1);
            lSum -= piles[right];
        } else {
            aSum += piles[left];
            stoneGame(piles, left + 1, right, depth + 1);
            aSum -= piles[left];
            aSum += piles[right];
            stoneGame(piles, left, right - 1, depth + 1);
            aSum -= piles[right];
        }

        return result;
    }

    public static void main(String[] args) {
//        System.out.println(stoneGame(new int[]{5, 3, 4, 5}));
//        System.out.println(stoneGame(new int[]{3, 2, 10, 4}));
//        System.out.println(stoneGame(new int[]{3, 10, 4, 2}));
        System.out.println(stoneGame(new int[]{288,479,157,128,401,125,380,492,493,173,90,88,248,189,136,492,438,65,399,68,213,255,32,98,212,174,2,435,323,6,54,109,133,17,156,152,22,154,289,488,181,464,445,483,247,298,77,386,384,152,225,358,171,274,282,339,388,125,20,363,393,391,444,284,434,233,138,179,140,60,246,266,319,372,446,271,50,120,85,148,233,143,100,322,269,347,370,227,336,230,42,260,251,330,359,477,342,471,102,336,480,441,401,95,22,309,147,412,442,89,435,497,249,315,173,110,350,484,56,227,30,157,204,312,95,319,44,381,422,144,198,283,26,27,465,301,407,357,452,432,228,3,404,404,291,276,336,157,28,109,434,373,356,140,295,492,52,106,294,402,354,378,109,91,51,340,102,393,313,405,146,301,2,487,268,190,6,461,372,457,44,356,272,491,349,232,1,272,154,156,214,275,460,19,289,462,163,455,22,285,464,263,330,3,193,164,52,171,247,314,83,381,239,103,407,181,315,88,147,274,341,354,229,206,298,489,297,4,415,484,295,118,372,499,272,216,248,273,49,391,489,16,266,326,428,346,426,69,354,229,24,482,497,103,112,91,331,210,230,390,183,256,172,473,494,372,249,36,464,486,121,415,273,315,472,125,59,196,334,172,196,148,351,72,99,406,304,96,429,477,453,168,187,399,299,409,483,500,431,306,153,392,386,378,220,128,42,497,104,348,307,465,119,1,220,175,201,413,7,154,423,396,167,170,407,316,26,317,334,426,343,470,70,133,255,338,392,31,45,365,479,440,179,276,68,378,259,196,473,268,204,192,56,195,325,402,342,129,385,210,83,51,64,238,21,358,153,274,375,320,31,458,180,309,134,35,272,386,255,82,370,404,334,367,423,359,398,478,102,185,262,221,337,293,277,464,477,207,312,480,337,140,39,407,41,441,417,189,218,43,369,83,87,215,218,463,127,1,87,105,170,122,87,231,261,317,310,131,201,151,440,38,115,3,347,488,15,225,66,216,351,109,383,421,257,324,271,24,161,493,100,499,329,170,183,139,281,449,81,294,191,388,71,178,279,119,342,477,175,62,148,303,468,368,321,120,208,500,417,468,319,470,486,335,389,129,223,59,400,14,461,494,50,208,423,414,148,228,497,30}));
//        System.out.println(stoneGame(new int[]{3, 2, 111, 10, 10, 4}));
    }
}
