package com.leetcode.sort;

import com.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author wcl
 * @date 4:28 下午 2020/8/27
 * <a href="https://leetcode.com/problems/maximum-number-of-coins-you-can-get/">Maximum Number of Coins You Can Get</a>
 */
public class MaximumNumberOfCoinsYouCanGet {
    /**
     * There are 3n piles of coins of varying size, you and your friends will take piles of coins as follows:
     * <p>
     * In each step, you will choose any 3 piles of coins (not necessarily consecutive).
     * Of your choice, Alice will pick the pile with the maximum number of coins.
     * You will pick the next pile with maximum number of coins.
     * Your friend Bob will pick the last pile.
     * Repeat until there are no more piles of coins.
     * Given an array of integers piles where piles[i] is the number of coins in the ith pile.
     * <p>
     * Return the maximum number of coins which you can have.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: piles = [2,4,1,2,7,8]
     * Output: 9
     * Explanation: Choose the triplet (2, 7, 8), Alice Pick the pile with 8 coins, you the pile with 7 coins and Bob the last one.
     * Choose the triplet (1, 2, 4), Alice Pick the pile with 4 coins, you the pile with 2 coins and Bob the last one.
     * The maximum number of coins which you can have are: 7 + 2 = 9.
     * On the other hand if we choose this arrangement (1, 2, 8), (2, 4, 7) you only get 2 + 4 = 6 coins which is not optimal.
     * Example 2:
     * <p>
     * Input: piles = [2,4,5]
     * Output: 4
     * Example 3:
     * <p>
     * Input: piles = [9,8,7,6,5,1,2,3,4]
     * Output: 18
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 3 <= piles.length <= 10^5
     * piles.length % 3 == 0
     * 1 <= piles[i] <= 10^4
     */
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int result = 0;
        int length = piles.length;
        int count = length / 3;
        int index = length - 2;
        for (int i = 0; i < count; i++) {
            result += piles[index];
            index -= 2;
        }
        return result;
    }

    public static void main(String[] args) {
        MaximumNumberOfCoinsYouCanGet get = new MaximumNumberOfCoinsYouCanGet();

        List<int[]> read = Utils.read("/Users/wangchenglin/Documents/GitHub/leetcode/src/main/resources/non-negative.txt", 9999);
        assert read != null;
        read.forEach(nums -> {
            int[] newNums = new int[nums.length];
            int index = 0;
            for (int num : nums) {
                if (num > 10000 || num <= 0) {
                    num = ThreadLocalRandom.current().nextInt(1, 9999);
                }
                newNums[index++] = num;
            }
            System.out.println(Arrays.toString(newNums));
            System.out.println(get.maxCoins(newNums));
        });
    }

}
