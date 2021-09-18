package com.lcp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 2:12 PM 2020/4/29
 * <a href="https://leetcode-cn.com/problems/zui-xiao-tiao-yue-ci-shu/">
 *     最小跳跃次数</a>
 */
public class JumpGame {
    /**
     * 为了给刷题的同学一些奖励，力扣团队引入了一个弹簧游戏机。
     * 游戏机由 N 个特殊弹簧排成一排，编号为 0 到 N-1。初始有一个小球在编号 0 的弹簧处。
     * 若小球在编号为 i 的弹簧处，通过按动弹簧，可以选择把小球向右弹射 jump[i] 的距离，或者向左弹射到任意左侧弹簧的位置。
     * 也就是说，在编号为 i 弹簧处按动弹簧，小球可以弹向 0 到 i-1 中任意弹簧或者 i+jump[i] 的弹簧（若 i+jump[i]>=N ，则表示小球弹出了机器）。
     * 小球位于编号 0 处的弹簧时不能再向左弹。
     * 为了获得奖励，你需要将小球弹出机器。请求出最少需要按动多少次弹簧，可以将小球从编号 0 弹簧弹出整个机器，即向右越过编号 N-1 的弹簧。
     *
     * 示例 1：
     * 输入：jump = [2, 5, 1, 1, 1, 1]
     * 输出：3
     * 解释：小 Z 最少需要按动 3 次弹簧，小球依次到达的顺序为 0 -> 2 -> 1 -> 6，最终小球弹出了机器。
     *
     * 限制：
     * 1 <= jump.length <= 10^6
     * 1 <= jump[i] <= 10000
     */
    public static int minJump(int[] jump) {
        int m = jump.length;
        int[] maxJump = new int[m];
        maxJump[0] = jump[0];
        for (int i = 1; i < m; i++) {
            maxJump[i] = Math.max(i + jump[i], maxJump[i - 1]);
        }
        int[] steps = new int[m + 1];
        steps[1] = jump[0];
        if(jump[0] >= m) {
            return 1;
        }
        steps[2] = jump[0] + jump[jump[0]];
        int step = 2;
        List<Integer> indexs = new ArrayList<>();
        indexs.add(steps[1]);
        indexs.add(steps[2]);
        while(steps[step] < m) {
            List<Integer> cur = new ArrayList<>();
            cur.add(steps[step]);
            int max = maxJump[steps[step - 1]];
            for (int i = steps[step - 2]; i < steps[step - 1]; i++) {
                max = Math.max(i + jump[i], max);
                if(i + jump[i] > steps[step]) {
                    cur.add(i + jump[i]);
                }
            }
            for (int i = 1; i < indexs.size(); i++) {
                int index = indexs.get(i);
                max = Math.max(index + jump[index], max);
                if(index + jump[index] > steps[step]) {
                    cur.add(index + jump[index]);
                }
            }
            step++;
            steps[step] = max;
            indexs = cur;
        }
        return step;
    }

    public static void main(String[] args) {
//        System.out.println(minJump(new int[]{2, 5, 1, 1, 1, 1}));
//        System.out.println(minJump(new int[]{2,1,5,6,2,3}));
        System.out.println(48 == minJump(new int[]{4,1,8,7,7,11,7,9,8,3,11,1,8,6,8,2,2,1,4,11,8,1,5,4,4,4,11,7,9,8,3,8,6,1,1,6,3,2,9,4,1,9,4,8,9,1,8,2,5,7,11,7,11,6,1,4,6,3,11,1,8,2,5,3,1,1,7,1,7,9,11,3,2,4,2,2,3,4,1,7,7,1,9,4,9,3,7,1,11,7,2,7,2,3,9,2,1,9,7,1,9,11,3,2,8,9,9,5,5,9,7,1,2,9,11,5,7,11,2,6,8,1,11,7,9,5,6,11,3,1,7,1,8,2,2,3,3,6,1,4,8,8,1,1,7,1,6,6,3,6,6,1,1,3,11,6,9,3,1,9,4,3,8,1,6,11,9,1,4,5,11,2,9,6,8,2,5,6,9,3,1,2,1,3,8,2,7,9,4,8,6,7,11,5,8,2,6,2,3,6,9,4,1,6,8,11,6,3,2,1,1,8,7,4,6,4,3,4,8,3,4,9,2,8,3,6,8,9,8,5,1,9,9,7,1,7,4,8,7,3,1,9,3,3,8,11,4,7,9,8,8,7,9,6,9,8,1,5,1,4,3,5,11,3,9,6,4}));
        System.out.println(5 == minJump(new int[]{4,1,8,7,7,11,7,9,8,3,11,1,8,6,8,2,2,1,4,11,8,1,1}));

    }
}
