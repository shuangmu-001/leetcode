package com.leetcode.dp;

import java.util.*;

/**
 * @Author: wcl
 * @Description:
 * @Date: Create in 下午4:17 2018/10/16
 * @Modified By:
 */
public class DynamicProgramming {

    /**
     * 有一座高度是10级台阶的楼梯，从下往上走，每跨一步只能向上1级或者2级台阶。要求用程序来求出一共有多少种走法
     * 思路一：排列组合->统计每种步数的走法，最多需要10步，最少需要5步，统计6-9步每种步数的走法
     * 思路二：dynamic programming->假设只剩最有一步到第10级台阶 有两种可能从9->10或从8->10，则问题成0->9和0->8，
     *                             即：f(n) = f(n-1) + f(n-2)
     *                                f(n-1) = f(n-2) + f(n-3)
     *                                f(n-2) = f(n-3) + f(n-4)
     *                                ...
     *                                f(3) = f(2) + f(1)
     */
    /**
     * 递归求解
     * @param n 台阶数
     * @return
     */
    public static int getClimbingWays_1(int n) {

        if(n <= 0) {
            throw new IllegalArgumentException("参数必须大于0");
        }

        if(n == 2) {
            return 2;
        }

        if(n == 1) {
            return 1;
        }

        return getClimbingWays_1(n - 1) + getClimbingWays_1(n - 2);
    }

    /**
     * 备忘录算法
     * @param n 台阶数
     * @return
     */
    public static int getClimbingWays_2(int n, HashMap<Integer, Integer> map) {

        if(n <= 0) {
            throw new IllegalArgumentException("参数必须大于0");
        }

        if(n == 2) {
            return 2;
        }

        if(n == 1) {
            return 1;
        }

        if(map.containsKey(n)) {
            return map.get(n);
        } else {
            int value = getClimbingWays_2(n-1, map) + getClimbingWays_2(n-2, map);
            map.put(n, value);
            return value;
        }

    }

    /**
     * 动态规划
     * @param n 台阶数
     * @return
     */
    public static int getClimbingWays(int n) {

        if(n <= 0) {
            throw new IllegalArgumentException("参数必须大于0");
        }

        if(n == 2) {
            return 2;
        }

        if(n == 1) {
            return 1;
        }

        int a = 1;
        int b = 2;
        int temp = 0;
        for(int i = 3; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }

        return temp;
    }

    public static void main(String[] args) {
        System.out.println(getClimbingWays_1(5));
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        System.out.println(getClimbingWays_2(5, hashMap));
        System.out.println(hashMap);
        System.out.println(getClimbingWays(5));
        int[] golds = new int[]{500, 200, 350, 300, 400};
        int[] persons = new int[]{5, 3, 3, 4, 5};
        Set<Integer> goldList = new HashSet();
        Map<String, Integer> map = new HashMap();
        System.out.println(getMaxGold_2(5, 10, golds, persons, goldList, map));
        System.out.println(map);
        System.out.println(getMaxGold(5, 10, golds, persons));
        int[][] grid = new int[][]{{5,0,1,1,2,1,0,1,3,6,3,0,7,3,3,3,1},{1,4,1,8,5,5,5,6,8,7,0,4,3,9,9,6,0},{2,8,3,3,1,6,1,4,9,0,9,2,3,3,3,8,4},{3,5,1,9,3,0,8,3,4,3,4,6,9,6,8,9,9},{3,0,7,4,6,6,4,6,8,8,9,3,8,3,9,3,4},{8,8,6,8,3,3,1,7,9,3,3,9,2,4,3,5,1},{7,1,0,4,7,8,4,6,4,2,1,3,7,8,3,5,4},{3,0,9,6,7,8,9,2,0,4,6,3,9,7,2,0,7},{8,0,8,2,6,4,4,0,9,3,8,4,0,4,7,0,4},{3,7,4,5,9,4,9,7,9,8,7,4,0,4,2,0,4},{5,9,0,1,9,1,5,9,5,5,3,4,6,9,8,5,6},{5,7,2,4,4,4,2,1,8,4,8,0,5,4,7,4,7},{9,5,8,6,4,4,3,9,8,1,1,8,7,7,3,6,9},{7,2,3,1,6,3,6,6,6,3,2,3,9,9,4,4,8}};
        int[][] grid2 = new int[][] {{1,5,1,8,8,2,4,0,6,1,4,3,7,4,8,5,7,3},{8,7,4,0,5,8,4,4,6,0,1,8,8,3,5,2,1,6},{4,7,8,2,0,2,5,7,8,5,5,5,0,5,2,4,5,9},{5,2,5,1,2,7,1,1,0,9,3,3,5,9,2,3,3,2},{7,1,1,7,6,8,2,7,3,6,3,9,6,8,3,1,2,5},{8,3,8,0,4,1,3,1,2,7,6,8,9,5,9,3,2,7},{1,4,6,5,3,0,6,1,0,9,2,2,6,3,8,5,5,2},{8,1,6,1,8,2,1,8,8,0,1,0,9,2,7,6,9,2},{6,7,3,8,8,7,1,5,2,1,0,8,5,8,9,1,1,7},{4,2,7,4,4,0,4,4,5,3,0,4,5,8,4,8,6,2},{8,9,9,0,0,1,8,8,0,9,9,3,7,5,6,6,9,2}};
        long start = System.currentTimeMillis();
        System.out.println(minPathSum(grid2));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /**
     * 有一个国家发现了5座金矿，每座金矿的黄金储量不同，需要参与挖掘的工人数也不同。
     * 参与挖矿工人的总数是10人。
     * 每座金矿要么全挖，要么不挖，不能派出一半人挖取一半金矿。
     * 要求用程序求解出，要想得到尽可能多的黄金，应该选择挖取哪几座金矿？
     * 500/5 400/5 350/3 300/4 200/3
     * G[]表示金矿黄金，P[]表示每个金矿所需人数
     * F(5,10) = Max(F(4,10), F(4,10-P[4]) + G[4])
     * ...
     * F(n,w) = 0    (n<=1, w<p[0]);
     * F(n,w) = g[0]     (n==1, w>=p[0]);
     * F(n,w) = F(n-1,w)    (n>1, w<p[n-1]);
     * F(n,w) = max(F(n-1,w),  F(n-1,w-p[n-1])+g[n-1])    (n>1, w>=p[n-1]);
     */

    public static int getMaxGold_1(int n, int w, int[] golds, int[] persons, Set<Integer> goldList) {

        if(n <= 1 && w < persons[0]) {
            return 0;
        }

        if(n == 1 && w >= persons[0]) {
            goldList.add(0);
            return golds[0];
        }

        int notDigging = getMaxGold_1(n-1, w, golds, persons, goldList);

        int digging = getMaxGold_1(n-1, w - persons[n-1], golds, persons, goldList) + golds[n-1];

        if(n > 1 && w < persons[n - 1]) {
            return notDigging;
        }

        return Math.max(notDigging, digging);
    }

    public static int getMaxGold_2(int n, int w, int[] golds, int[] persons, Set<Integer> goldList, Map<String, Integer> map) {
        if(n <= 1 && w < persons[0]) {
            return 0;
        }

        if(n == 1 && w >= persons[0]) {
            return golds[0];
        }

        String key = n + "" + w;
        if(map.containsKey(key)) {
            return map.get(key);
        }

        int notDigging = getMaxGold_2(n-1, w, golds, persons, goldList,map);

        if(n > 1 && w < persons[n - 1]) {
            map.put(key,notDigging);
            return notDigging;
        }

        int digging = getMaxGold_2(n-1, w - persons[n-1], golds, persons, goldList,map) + golds[n-1];

        int max = Math.max(notDigging, digging);

        map.put(key, max);

        return max;

    }

    public static int getMaxGold(int n, int w, int[] g, int[] p) {
        int[] preResults = new int[w];
        int[] results = new int[w];
        //边界
        for (int i = 1; i <= w; i++) {
            if(i < p[0]) {
                preResults[i -1] = 0;
            } else {
                preResults[i - 1] = g[0];
            }
        }
        int goldLen = g.length;
        for(int i = 1; i < goldLen; i++) {
            for (int j = 0; j < w; j++) {
                if(j < p[i]) {
                    results[j] = preResults[j];
                } else {
                    results[j] = Math.max(preResults[j], preResults[j - p[i]] + g[i]);
                }
            }
        }
        return results[w-1];
    }
    /**
     * f[n][n] = min(f[n][n-1], f[n-1][n])
     */

    public static int minPathSum(int[][] grid) {

        int out = grid.length;
        if(out == 0) {
            return 0;
        }
        int in = grid[0].length;
        Map<String, Integer> map = new HashMap<String, Integer>();
        int result = minPathSum(grid, out, in, map);
        return result;

    }

    public static int minPathSum(int[][] grid, int out, int in, Map<String, Integer> map) {
        if(in == 1) {
            int sum = 0;
            for(int i = 0; i < out; i++) {
                sum += grid[i][0];
            }
            return sum;
        }

        if(out == 0) {
            int sum = 0;
            for(int i = 0; i < in; i++) {
                sum += grid[0][i];
            }
            return sum;
        }
        String key = out + "---" + in;
        if(map.containsKey(key)) {
            return map.get(key);
        }
        int preIn = minPathSum(grid, out, in -1, map);
        int preOut = minPathSum(grid, out - 1, in, map);
        int result = Math.min(preIn, preOut) + grid[out - 1][in - 1];
        map.put(key, result);
        return result;
    }
    
}
