package com.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 5:26 下午 2020/5/19
 * <a href="https://leetcode.com/problems/simplified-fractions/">
 * SimplifiedFractions</a>
 */
public class SimplifiedFractions {
    /**
     * Given an integer n, return a list of all simplified fractions between 0 and 1 (exclusive)
     * such that the denominator is less-than-or-equal-to n. The fractions can be in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 2
     * Output: ["1/2"]
     * Explanation: "1/2" is the only unique fraction with a denominator less-than-or-equal-to 2.
     * Example 2:
     *
     * Input: n = 3
     * Output: ["1/2","1/3","2/3"]
     * Example 3:
     *
     * Input: n = 4
     * Output: ["1/2","1/3","1/4","2/3","3/4"]
     * Explanation: "2/4" is not a simplified fraction because it can be simplified to "1/2".
     * Example 4:
     *
     * Input: n = 1
     * Output: []
     *
     *
     * Constraints:
     *
     * 1 <= n <= 100
     */

    public static List<String> simplifiedFractions(int n) {
        if(n == 1) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {

                if(i == 1 || maxGcd(i, j) == 1) {
                    res.add(i + "/" + j);
                }
            }
        }
        return res;
    }

    public static boolean hasCommonMultiple(int a, int b) {
        if(a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int i = 2; i <= Math.sqrt(b); i++) {
            if(a % i == 0 && 0 == b % i) {
                return  true;
            }
        }

        return false;
    }

    public static int maxGcd(int a, int b) {
        int max, min;
        max = Math.max(a, b);
        min = Math.min(a, b);

        if (max % min != 0) {
            return maxGcd(min, max % min);
        } else {
            return min;
        }
    }


}
