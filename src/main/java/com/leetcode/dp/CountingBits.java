package com.leetcode.dp;

import com.Utils;

/**
 * @author zms
 * @date 2:06 PM 2020/3/24
 * <a href="https://leetcode.com/problems/counting-bits/">
 *     Counting Bits</a>
 */
public class CountingBits {
    /**
     * Given a non negative integer number num.
     * For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
     *
     * Example 1:
     *      Input: 2
     *      Output: [0,1,1]
     *
     * Example 2:
     *      Input: 5
     *      Output: [0,1,1,2,1,2]
     *
     * Follow up:
     *      It is very easy to come up with a solution with run time O(n*sizeof(integer)).
     *      But can you do it in linear time O(n) /possibly in a single pass?
     *      Space complexity should be O(n).
     *      Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
     */
    public int[] countBits(int num) {
        if(num == 0) {
            return new int[]{0};
        }
        if(num == 1) {
            return new int[]{0,1};
        }
        int[] results = new int[num + 1];
        int bit = 2;
        int len = 1;
        results[0] = 0;
        results[1] = 1;
        for (int i = 2; i < num + 1; i++) {
            if((1 << len) == i) {
                bit = i;
                len++;
                results[i] = 1;
            } else {
                results[i] = results[i - bit] + 1;
            }
        }
        return results;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 28; i++) {
            Utils.printArrays(new CountingBits().countBits(i));
        }
    }
}
