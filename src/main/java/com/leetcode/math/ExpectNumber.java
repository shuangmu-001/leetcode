package com.leetcode.math;

import java.util.Arrays;

/**
 * @author zms
 * @date 5:50 PM 2020/4/28
 * <a href="https://leetcode-cn.com/contest/season/2020-spring/problems/qi-wang-ge-shu-tong-ji/">
 *     期望个数统计</a>
 */
public class ExpectNumber {
    /**
     * 某互联网公司一年一度的春招开始了，一共有 n 名面试者入选。每名面试者都会提交一份简历，
     * 公司会根据提供的简历资料产生一个预估的能力值，数值越大代表越有可能通过面试。
     * 小 A 和小 B 负责审核面试者，他们均有所有面试者的简历，并且将各自根据面试者能力值从大到小的顺序浏览。
     * 由于简历事先被打乱过，能力值相同的简历的出现顺序是从它们的全排列中等可能地取一个。
     * 现在给定 n 名面试者的能力值 scores，设 X 代表小 A 和小 B 的浏览顺序中出现在同一位置的简历数，求 X 的期望。
     *
     * 示例 1：
     * 输入：scores = [1,2,3]
     * 输出：3
     * 解释：由于面试者能力值互不相同，小 A 和小 B 的浏览顺序一定是相同的。X的期望是 3 。
     *
     * 示例 2：
     * 输入：scores = [1,1]
     * 输出：1
     * 解释：设两位面试者的编号为 0, 1。由于他们的能力值都是 1，小 A 和小 B 的浏览顺序都为从全排列 [[0,1],[1,0]] 中等可能地取一个。
     * 如果小 A 和小 B 的浏览顺序都是 [0,1] 或者 [1,0] ，那么出现在同一位置的简历数为 2 ，否则是 0 。所以 X 的期望是 (2+0+2+0) * 1/4 = 1
     *
     * 示例 3：
     * 输入：scores = [1,1,2]
     * 输出：2
     *
     * 限制：
     * 1 <= scores.length <= 10^5
     * 0 <= scores[i] <= 10^6
     */
    public static int expectNumber(int[] scores) {

        int res = 1;
        int len = scores.length;
        Arrays.sort(scores);
        int cur = scores[0];
        for (int i = 1; i < len; i++) {
            if(scores[i] != cur) {
                res ++;
                cur = scores[i];
            }
        }


        return res;
    }

    public static void main(String[] args) {
//        System.out.println(expectNumber(new int[]{1,2,3}) == 3);
//        System.out.println(expectNumber(new int[]{1,2,2}) == 2);
//        System.out.println(expectNumber(new int[]{1,1}) == 1);
//        System.out.println(expectNumber(new int[]{1,1,2}) == 2);
        System.out.println(expectNumber(new int[]{3,9,9,7,0,3,9,8,6,5}) == 7);
    }
}
