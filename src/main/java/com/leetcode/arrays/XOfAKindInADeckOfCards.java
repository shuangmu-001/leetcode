package com.leetcode.arrays;

import java.util.Arrays;

/**
 * @author zms
 * @date 5:19 PM 2020/3/27
 * <a href="https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards/">
 *     X of a Kind in a Deck of Cards</a>
 */
public class XOfAKindInADeckOfCards {
    /**
     * In a deck of cards, each card has an integer written on it.
     * Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards,
     * where:
     *      Each group has exactly X cards.
     *      All the cards in each group have the same integer.
     *
     * Example 1:
     *      Input: deck = [1,2,3,4,4,3,2,1]
     *      Output: true
     *      Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
     *
     * Example 2:
     *      Input: deck = [1,1,1,2,2,2,3,3]
     *      Output: false´
     *      Explanation: No possible partition.
     *
     * Example 3:
     *      Input: deck = [1]
     *      Output: false
     *      Explanation: No possible partition.
     *
     * Example 4:
     *      Input: deck = [1,1]
     *      Output: true
     *      Explanation: Possible partition [1,1].
     *
     * Example 5:
     *      Input: deck = [1,1,2,2,2,2]
     *      Output: true
     *      Explanation: Possible partition [1,1],[2,2],[2,2].
     *
     * Constraints:
     *      1 <= deck.length <= 10^4
     *      0 <= deck[i] < 10^4
     */
    public boolean hasGroupsSizeX(int[] deck) {
        if(deck.length <= 1) {
            return false;
        }
        Arrays.sort(deck);
        int before = 0;
        int len = deck.length;
        for (int i = 1, j = 0; i < len; i++) {
            if(i == len - 1 || deck [i + 1] != deck[j]) {
                int cur = i - j + 1;
                if( cur < 2) {
                    return false;
                }
                if(before == 0) {
                    before = cur;
                } else if(before != cur) {
                    int gcd = gcd(before, cur);
                    if(gcd < 2) {
                        return false;
                    }
                    before = gcd;
                }
                j = i + 1;
            }
        }
        return true;
    }
    // 最大公约数
    public static int gcd(int p, int q) {
        if(q == 0) {
            return p;
        }
        int r = p % q;
        return gcd(q, r);
    }

    public static void main(String[] args) {
        System.out.println(gcd(5,7));
    }
}
