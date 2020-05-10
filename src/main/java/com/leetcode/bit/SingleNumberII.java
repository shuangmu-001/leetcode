package com.leetcode.bit;

/**
 * @author wcl
 * @date 11:46 AM 2020/4/24
 * TODO <a href="https://leetcode.com/problems/single-number-ii/">
 *     Single Number II</a>
 * @see SingleNumber
 */
public class SingleNumberII {
    /**
     * Given a non-empty array of integers,
     * every element appears three times except for one,
     * which appears exactly once. Find that single one.
     * Note:
     * Your algorithm should have a linear runtime complexity.
     * Could you implement it without using extra memory?
     *
     * Example 1:
     * Input: [2,2,3,2]
     * Output: 3
     *
     * Example 2:
     * Input: [0,1,0,1,0,1,99]
     * Output: 99
     */
    public static int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        int s = nums[0];
        for (int num : nums) {
            s &= num;
        }
        return res ^ s;
    }

    public static void main(String[] args) {
//        System.out.println(singleNumber(new int[]{2,2,3,2}));
//        System.out.println(2 ^ 2^ 2^ 3 ^ 2 & 2 & 2 & 3);
//        System.out.println(3 ^ 3^ 3^ 99 ^ 3 & 3 & 3 & 99);
//        System.out.println((3 ^ 3^ 3^ 11 ^ 11^ 11 ) ^ 99 ^ (3 & 3 & 3 & 11 & 11 & 11 & 99));
//        System.out.println((3 ^ 3^ 3^ 11 ^ 11 ^ 11 ) ^ (3 & 3 & 3 & 11 & 11 & 11));
//        System.out.println((11 ^ 11^ 11) ^ (11 & 11 & 11));
//        System.out.println((11 ^ 11^ 11));
//        System.out.println((11 & 11 & 11));
//        System.out.println((11 & 11 & 11));
        System.out.println((3 ^ 11 ^ 99) ^ (3 | 11 | 99));
        System.out.println((3 & 11 & 99) & (3 ^ 11 ^ 99));

//        System.out.println(singleNumber(new int[]{0,1,0,1,0,1,99}));
//        System.out.println(singleNumber(new int[]{0,1,0,1,0,1,99,3,3,3,11,11,11,33,33,33}));
//        System.out.println(singleNumber(new int[]{99,3,3,3,11,11,11,33,33,33}));
    }
}
