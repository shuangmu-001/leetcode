package com.algs.recursion;

import com.Test;
import com.Utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zms
 * @date 6:28 下午 2021/10/14
 */
public class Code13Coffee implements Test {

    // 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
    // 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
    // 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
    // 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
    // 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
    // 四个参数：arr, n, a, b
    // 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drinkTimes = new int[n];
        return forceMake(arr, times, drinkTimes, a, b, 0, n);
    }

    // arr 每个咖啡机制作咖啡的时间
    // times 每个咖啡机什么时间空闲
    // drinkTimes 每个人喝咖啡的时间
    // k 第几个人选择咖啡机
    public static int forceMake(int[] arr, int[] times, int[] drinkTimes, int a, int b, int k, int n) {
        if (k == n) {
            int[] drinkTimesSorted = Arrays.copyOf(drinkTimes, n);
            Arrays.sort(drinkTimesSorted);
            return forceWash(drinkTimesSorted, a, b, 0, 0);
        }
        int drinkTime = Integer.MAX_VALUE;
        // 每台咖啡机都尝试一下，看哪个最小
        for (int i = 0; i < arr.length; i++) {
            times[i] += arr[i];
            drinkTimes[k] = times[i];
            drinkTime = Math.min(drinkTime, forceMake(arr, times, drinkTimes, a, b, k + 1, n));
            times[i] -= arr[i];
        }
        return drinkTime;
    }

    /**
     * @param drinkTimes 每个咖啡杯空闲的时间（可以进行清理的时间）
     * @param a          洗咖啡杯消耗的时间
     * @param b          自然挥发的时间
     * @param freeTime   洗咖啡杯机器什么时间空闲
     * @param index      第几个咖啡杯开始做选择，从0开始
     * @return drinkTimes[index...]的咖啡杯最少需要多少时间让每个咖啡都杯清理干净
     */
    public static int forceWash(int[] drinkTimes, int a, int b, int freeTime, int index) {
        // 边界
        if (index == drinkTimes.length) {
            return 0;
        }
        // 选择洗咖啡杯机器 会影响到会面的决策，所以不能每一步进行贪心选择
        // 当前咖啡杯什么时间清理干净
        int selfClean1 = Math.max(freeTime, drinkTimes[index]) + a;
        // 剩下咖啡杯干净时间
        int restClean1 = forceWash(drinkTimes, a, b, selfClean1, index + 1);
        int ans1 = Math.max(restClean1, selfClean1);
        // 选择自然挥发
        // 当前咖啡杯什么时间清理干净
        int selfClean2 = drinkTimes[index] + b;
        // 剩下咖啡杯干净时间
        int restClean2 = forceWash(drinkTimes, a, b, freeTime, index + 1);
        int ans2 = Math.max(selfClean2, restClean2);
        return Math.min(ans1, ans2);
    }

    // 每个人喝完咖啡的时间
    public static int[] drinkTimes(int[] arr, int n) {
        // 构建一个小根堆
        PriorityQueue<Machine> machines = new PriorityQueue<>(new MachineComparator());
        for (int workTime : arr) {
            machines.add(new Machine(0, workTime));
        }
        // 每个人喝咖啡的时间
        int[] drinkTimes = new int[n];
        // 每个人都最早能喝到咖啡的咖啡机
        // 贪心选择最快能喝到的咖啡的咖啡机
        for (int i = 0; i < n; i++) {
            Machine poll = machines.poll();
            assert poll != null;
            poll.freeTime += poll.workTime;
            drinkTimes[i] = poll.freeTime;
            machines.add(poll);
        }

        return drinkTimes;
    }

    public static int right01(int[] arr, int n, int a, int b) {
        return forceWash(drinkTimes(arr, n), a, b, 0, 0);
    }

    public static int right02(int[] arr, int n, int a, int b) {
        return dp(drinkTimes(arr, n), a, b);
    }

    public static int dp(int[] drinkTimes, int a, int b) {
        int n = drinkTimes.length;
        int maxFreeTime = 0;
        for (int drinkTime : drinkTimes) {
            maxFreeTime = Math.max(drinkTime, maxFreeTime) + a;
        }
        int[][] dp = new int[n + 1][maxFreeTime + 1];
        for (int index = n - 1; index >= 0; index--) {
            for (int freeTime = 0; freeTime <= maxFreeTime; freeTime++) {
                // 选择洗咖啡杯机器
                // 当前咖啡杯什么时间清理干净
                int selfClean1 = Math.max(freeTime, drinkTimes[index]) + a;
                if (selfClean1 > maxFreeTime) {
                    // 因为后面的也都不用填了
                    break;
                }
                // 剩下咖啡杯干净时间
                int restClean1 = dp[index + 1][selfClean1];
                int ans1 = Math.max(restClean1, selfClean1);
                // 选择自然挥发
                // 当前咖啡杯什么时间清理干净
                int selfClean2 = drinkTimes[index] + b;
                // 剩下咖啡杯干净时间
                int restClean2 = dp[index + 1][freeTime];
                int ans2 = Math.max(selfClean2, restClean2);
                dp[index][freeTime] = Math.min(ans1, ans2);
            }
        }
        return dp[0][0];
    }

    public static class Machine {
        // 咖啡机什么时间空闲
        public int freeTime;
        // 完成咖啡制作所需要的时间
        public int workTime;

        public Machine(int freeTime, int workTime) {
            this.freeTime = freeTime;
            this.workTime = workTime;
        }
    }

    public static class MachineComparator implements Comparator<Machine> {

        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.freeTime + o1.workTime) - (o2.freeTime + o2.workTime);
        }
    }

    @Override
    public void test(int n) {
        int[] arr = genRandomArr(n);
        int a = genRandomNum(n);
        int b = genRandomNum(n);
        int ans01 = right01(arr, n, a, b);
        int ans02 = right02(arr, n, a, b);
        if (ans01 != ans02) {
            Utils.printArrays(drinkTimes(arr, n));
            Utils.printArrays(arr);
            System.out.printf("错误的输入:%d,%d,%d\n", a, b, n);
            System.out.printf("错误的输出:%d,%d\n", ans01, ans02);
            right02(arr, n, a, b);
            throw new RuntimeException();
        }
    }
}
