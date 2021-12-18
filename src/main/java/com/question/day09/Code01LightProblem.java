package com.question.day09;

/**
 * 从左到右尝试模型，尝试函数，前面做的决定被后面的做的决定改变了
 *
 * @author zms
 * @date 4:30 下午 2021/9/27
 */
public class Code01LightProblem {

    // 给定一个数组arr，长度为N，arr中的值不是0就是1。arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯
    // 每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+1栈灯的状态

    // 问题一：
    // 如果N栈灯排成一条直线,请问最少按下多少次开关,能让灯都亮起来
    // 排成一条直线说明：
    // i为中间位置时，i号灯的开关能影响i-1、i和i+1
    // 0号灯的开关只能影响0和1位置的灯
    // N-1号灯的开关只能影响N-2和N-1位置的灯
    public static int noLoopRight01(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        if (len == 1) {
            return arr[0] ^ 1;
        }
        int ans01 = noLoopProcess01(arr, 2, arr[1], arr[0]);
        int ans02 = noLoopProcess01(arr, 2, arr[1] ^ 1, arr[0] ^ 1);
        if (ans02 != Integer.MAX_VALUE) {
            ans02++;
        }
        return Math.min(ans01, ans02);
    }

    /**
     * index - 2 之前的灯一定都是亮着的，
     * 用有限的变量记录之前的状态，当前的操作的状态，有可能被之后的操作改变
     *
     * @param arr       所有灯的原始状态
     * @param index     下次操作的索引
     * @param curStatus 当前操作的灯的状态
     * @param preStatus 之前操作的灯的状态
     * @return 让所有灯都亮的操作次数
     */
    public static int noLoopProcess01(int[] arr, int index, int curStatus, int preStatus) {
        if (index == arr.length) {
            return curStatus != preStatus ? Integer.MAX_VALUE : curStatus ^ 1;
        }
        // 必须保证index - 2之前的灯都亮着
        if (preStatus == 0) {
            // 操作当前开关
            // preStatus 亮， 当前的灯的状态: curStatus ^ 1 index 位置的灯的状态是 arr[index] ^ 1
            int res = noLoopProcess01(arr, index + 1, arr[index] ^ 1, curStatus ^ 1);
            return res == Integer.MAX_VALUE ? res : res + 1;
        } else {
            // 不操作当前开关
            return noLoopProcess01(arr, index + 1, arr[index], curStatus);
        }
    }

    public static int noLoopRight02(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        if (len == 1) {
            return arr[0] ^ 1;
        }
        int ans01 = noLoopProcess02(arr, false);
        int ans02 = noLoopProcess02(arr, true);
        return Math.min(ans01, ans02);
    }

    /**
     * @param arr 所有灯的状态
     * @param p   第一盏灯的是否使用开关
     * @return 所有灯都亮着的次数，如果不能返回Integer.MAX_VALUE
     */
    public static int noLoopProcess02(int[] arr, boolean p) {
        int index = 2, count = p ? 1 : 0;
        int preStatus = p ? arr[0] ^ 1 : arr[0];
        int curStatus = p ? arr[1] ^ 1 : arr[1];
        while (index < arr.length) {
            if (preStatus == 0) {
                count++;
                preStatus = curStatus ^ 1;
                curStatus = arr[index] ^ 1;
            } else {
                preStatus = curStatus;
                curStatus = arr[index];
            }
            index++;
        }
        return preStatus == curStatus ? count + preStatus ^ 1 : Integer.MAX_VALUE;
    }

    // 问题二：
    // 如果N栈灯排成一个圈,请问最少按下多少次开关,能让灯都亮起来
    // 排成一个圈说明：
    // i为中间位置时，i号灯的开关能影响i-1、i和i+1
    // 0号灯的开关能影响N-1、0和1位置的灯
    // N-1号灯的开关能影响N-2、N-1和0位置的灯
    public static int loopRight(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        if (len == 1) {
            return arr[0] ^ 1;
        }
        if (len == 2) {
            return arr[0] == arr[1] ? arr[0] ^ 1 : Integer.MAX_VALUE;
        }
        // 0 不变 1不变
        int ans01 = loopProcess01(arr, 3, arr[2], arr[1], arr[0], arr[len - 1]);
        // 0 改变 1不变
        int ans02 = loopProcess01(arr, 3, arr[2], arr[1] ^ 1, arr[0] ^ 1, arr[len - 1] ^ 1);
        ans02 = ans02 == Integer.MAX_VALUE ? ans02 : ans02 + 1;
        // 0 不变 1改变
        int ans03 = loopProcess01(arr, 3, arr[2] ^ 1, arr[1] ^ 1, arr[0] ^ 1, arr[len - 1]);
        ans03 = ans03 == Integer.MAX_VALUE ? ans03 : ans03 + 1;
        // 0 改变 1改变
        int ans04 = loopProcess01(arr, 3, arr[2] ^ 1, arr[1], arr[0], arr[len - 1] ^ 1);
        ans04 = ans04 == Integer.MAX_VALUE ? ans04 : ans04 + 2;
        return Math.min(Math.min(ans01, ans02), Math.min(ans03, ans04));
    }

    /**
     * 1 到 index - 2 的灯都是亮着的
     * 当前操作从2开始
     *
     * @param arr         所有灯的原始状态
     * @param index       下次操作的索引
     * @param curStatus   当前操作的灯的状态
     * @param preStatus   之前操作的灯的状态
     * @param firstStatus 第一个灯的状态
     * @param endStatus   最后一个灯的状态
     * @return 让所有灯都亮的操作次数，如果不能返回Integer.MAX_VALUE
     */
    public static int loopProcess01(int[] arr, int index, int curStatus, int preStatus, int firstStatus, int endStatus) {
        if (index == arr.length) {
            // 当前灯也就是最后一个灯
            return curStatus == preStatus && curStatus == firstStatus ? curStatus ^ 1 : Integer.MAX_VALUE;
        }

        // 必须保证1 到index - 2的灯都亮着
        if (preStatus == 0) {
            // 操作当前开关
            // preStatus 亮， 当前的灯的状态: curStatus ^ 1 index 位置的灯的状态是 arr[index] ^ 1
            // 当index == arr.length - 1的时候endStatus 会被影响
            int nextEndStatus = index == arr.length - 1 ? endStatus ^ 1 : endStatus;
            int nextCurStatus = index == arr.length - 1 ? nextEndStatus : arr[index] ^ 1;
            int res = loopProcess01(arr, index + 1, nextCurStatus, curStatus ^ 1, firstStatus, nextEndStatus);
            return res == Integer.MAX_VALUE ? res : res + 1;
        } else {
            // 不操作当前开关
            int nextCurStatus = index == arr.length - 1 ? endStatus : arr[index];
            return loopProcess01(arr, index + 1, nextCurStatus, curStatus, firstStatus, endStatus);
        }
    }
}
