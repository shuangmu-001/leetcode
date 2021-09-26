package com.leetcode.dp.fibonacci;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zms
 * @date 3:10 下午 2020/5/22
 * <a href="https://leetcode.com/problems/knight-dialer/">
 * Knight Dialer</a>
 */
public class KnightDialer {
    /**
     * A chess knight can move as indicated in the chess diagram below:
     * This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.
     * <p>
     * Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.
     * <p>
     * How many distinct numbers can you dial in this manner?
     * <p>
     * Since the answer may be large, output the answer modulo 10^9 + 7.
     * <p>
     * Example 1:
     * <p>
     * Input: 1
     * Output: 10
     * Example 2:
     * <p>
     * Input: 2
     * Output: 20
     * Example 3:
     * <p>
     * Input: 3
     * Output: 46
     * <p>
     * <p>
     * Note:
     * <p>
     * 1 <= N <= 5000
     */
    private static final int[][] nums = new int[][]{
            {4, 6},
            {6, 8},
            {7, 9},
            {4, 8},
            {3, 9, 0},
            {},
            {1, 7, 0},
            {2, 6},
            {1, 3},
            {2, 4}
    };
    private final static int MAX = 1_000_000_007;

    // dp
    public static int knightDialer(int N) {
        if (N == 1) {
            return 10;
        }
        long[] ints = new long[10];
        long[] newInts = new long[10];
        Arrays.fill(ints, 1);
        ints[5] = 0L;
        for (int i = 2; i <= N; i++) {
            newInts[0] = (ints[6] + ints[4]) % MAX;
            newInts[1] = (ints[6] + ints[8]) % MAX;
            newInts[2] = (ints[7] + ints[9]) % MAX;
            newInts[3] = (ints[8] + ints[4]) % MAX;
            newInts[4] = (ints[3] + ints[9] + ints[0]) % MAX;
            newInts[6] = (ints[1] + ints[7] + ints[0]) % MAX;
            newInts[7] = (ints[6] + ints[2]) % MAX;
            newInts[8] = (ints[1] + ints[3]) % MAX;
            newInts[9] = (ints[2] + ints[4]) % MAX;
            long[] temp = ints;
            ints = newInts;
            newInts = temp;
        }
        System.out.println(Arrays.toString(ints));
        long res = 0;
        for (int i = 0; i < 10; i++) {
            res += ints[i];
        }
        return (int) (res % MAX);
    }

    static int len = 0;
    static List<Integer> list = new ArrayList<>();

    public static void dfs(int depth, int index, int target) {
        if (depth == target) {
            for (int n : nums[index]) {
                list.add(n);
            }
            len += nums[index].length;
            return;
        }
        for (int i = 0; i < nums[index].length; i++) {
            dfs(depth + 1, nums[index][i], target);
        }

    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for (int j = 1; j <= 10; j++) {
            for (int i = 0; i < 10; i++) {
                dfs(1, i, j);
            }
            System.out.println(len + "------" + knightDialer(j + 1));
//            System.out.println(len );
            int count2 = 0;
            int count1 = 0;
            int count3 = 0;
            for (int n : list) {
                switch (n) {
                    case 1:
                    case 3:
                    case 7:
                    case 9:
                        count1++;
                        break;
                    case 4:
                    case 6:
                        count2++;
                        break;
                    case 0:
                        count3++;
                        break;
                    default:
                        break;
                }
            }
            System.out.println(count1 + " : " + count3 + " : " + count2 + " : " + (list.size() - count1 - count2 - count3));
            list = new ArrayList<>();
            len = 0;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);


    }
}
