package com.question.day01;


import com.Test;

import java.util.Random;

/**
 * 一个数组中只有两种字符'G'和'B'想让所有的G都放在左侧，所有的B都放在右侧，
 * 但是只能在相邻字符之间进行交换操作，返回至少需要交换几次
 * 如果B都放在左侧，G都放在右侧，那么至少需要交换几次，
 * B放在左侧和G放在左侧，那个交换次数少？至少需要交换几次
 *
 * @author wcl
 * @date 3:46 下午 2021/9/15
 */
public class Code04MinSwapStep implements Test {

    public static int minSwapStep1(String target) {
        if (target == null || target.length() <= 1) {
            return 0;
        }
        int len = target.length();
        // 当G放在左侧时候的最少步数
        int step1 = 0;
        int currIndex1 = 0;
        // 当B放在左侧时候的最少步数
        int step2 = 0;
        int currIndex2 = 0;
        for (int i = 0; i < len; i++) {
            char c = target.charAt(i);
            // 当G放在左侧
            if (c == 'G') {
                step1 += i - currIndex1++;
            }
            // 当B放在左侧
            else {
                step2 += i - currIndex2++;
            }
        }
        return Math.min(step1, step2);
    }

    public static int minSwapStep2(String target) {
        if (target == null || target.length() <= 1) {
            return 0;
        }
        int step1 = 0;
        int bCount = 0;
        int step2 = 0;
        int gCount = 0;
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            if (c == 'G') {
                step1 += bCount;
                gCount++;
            } else {
                step2 += gCount;
                bCount++;
            }
        }
        return Math.min(step1, step2);
    }

    private static final String TARGET_CHAR = "GB";

    @Override
    public String genTargetStr(int len) {
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = TARGET_CHAR.charAt(new Random().nextInt(TARGET_CHAR.length()));
        }
        return new String(chars);
    }

    @Override
    public void test(int n) {
        String target = genTargetStr(n);
        if (minSwapStep1(target) != minSwapStep2(target)) {
            System.out.println("不相同步数的字符串是 : " + target);
        }
    }

}
