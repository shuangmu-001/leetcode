package com.leetcode.dp.knapsack;

import com.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author zms
 * @date 6:15 下午 2020/5/19
 * <a href="https://leetcode.com/problems/form-largest-integer-with-digits-that-add-up-to-target/">
 * Form Largest Integer With Digits That Add Up To Target</a>
 * @see CombinationSumIV
 */
public class FormLargestIntegerWithDigitsThatAddUpToTarget {
    /**
     * Given an array of integers cost and an integer target. Return the maximum integer you can paint under the following rules:
     *
     * The cost of painting a digit (i+1) is given by cost[i] (0 indexed).
     * The total cost used must be equal to target.
     * Integer does not have digits 0.
     * Since the answer may be too large, return it as string.
     *
     * If there is no way to paint any integer given the condition, return "0".
     *
     *
     *
     * Example 1:
     *
     * Input: cost = [4,3,2,5,6,7,2,5,5], target = 9
     * Output: "7772"
     * Explanation:  The cost to paint the digit '7' is 2, and the digit '2' is 3. Then cost("7772") = 2*3+ 3*1 = 9. You could also paint "997", but "7772" is the largest number.
     * Digit    cost
     *   1  ->   4
     *   2  ->   3
     *   3  ->   2
     *   4  ->   5
     *   5  ->   6
     *   6  ->   7
     *   7  ->   2
     *   8  ->   5
     *   9  ->   5
     * Example 2:
     *
     * Input: cost = [7,6,5,5,5,6,8,7,8], target = 12
     * Output: "85"
     * Explanation: The cost to paint the digit '8' is 7, and the digit '5' is 5. Then cost("85") = 7 + 5 = 12.
     * Example 3:
     *
     * Input: cost = [2,4,6,2,4,6,4,4,4], target = 5
     * Output: "0"
     * Explanation: It's not possible to paint any integer with total cost equal to target.
     * Example 4:
     *
     * Input: cost = [6,10,15,40,40,40,40,40,40], target = 47
     * Output: "32211"
     *
     *
     * Constraints:
     *
     * cost.length == 9
     * 1 <= cost[i] <= 5000
     * 1 <= target <= 5000
     */
    static String res = "0";
    public static String largestNumber1(int[] cost, int target) {
        dfs(cost, cost.length - 1, target, new StringBuilder());
        return res;
    }
    public static void dfs(int[] cost, int index, int target, StringBuilder cur) {
        if(target == 0) {
            String sb = cur.toString();
            if(compare(new StringBuilder(res), new StringBuilder(sb)) < 0) {
                res = sb;
            }
            return;
        }
        if(index < 0 || target < 0) {
            return ;
        }
        for (int i = index; i >= 0; i--) {
            dfs(cost, i, target - cost[i], cur.append(i + 1));
            int len = cur.length();
            cur.deleteCharAt(len - 1);
        }
    }

    public static int compare(StringBuilder res, StringBuilder cur) {
        if(res == null) {
            return -1;
        }
        if(cur == null) {
            return 1;
        }
        int a = res.length();
        int b = cur.length();
        if(a > b) {
            return 1;
        } else if(a < b) {
            return -1;
        }
        for (int i = 0; i < a; i++) {
            char ac = res.charAt(i);
            char bc = cur.charAt(i);
            if(ac > bc) {
                return 1;
            } else if(ac < bc){
                return -1;
            }
        }
        return 0;
    }

    public static String largestNumber2(int[] cost, int target) {
        int m = cost.length;
        StringBuilder[][] dp = new StringBuilder[m][target + 1];
        for (int i = 0; i < m; i++) {
            dp[i][0] = new StringBuilder();
        }
        for (int i = m; i > 0 ; i--) {
            for (int j = 1; j <= target; j++) {
                if(j >= cost[i - 1]) {
                    if( i == m) {
                        dp[i - 1][j] = dp[i - 1][j - cost[i - 1]] == null ?  null : new StringBuilder(dp[i - 1][j - cost[i - 1]]).append(i);
                    } else {
                        StringBuilder bef = dp[i][j];
                        StringBuilder cur = dp[i - 1][j - cost[i - 1]] == null ? null: new StringBuilder(dp[i - 1][j - cost[i - 1]]).append(i);
                        if(compare(cur, bef) > 0) {
                            dp[i - 1][j] = cur;
                        } else {
                            dp[i - 1][j] = bef;
                        }
                    }

                } else {
                    if(i != m) {
                        dp[i - 1][j] = dp[i][j];
                    }
                }
            }
        }
        Utils.printTwoArrays(dp);
        return dp[0][target] == null ? "0" : dp[0][target].toString();
    }

    public static String largestNumber3(int[] cost, int target) {
        int m = cost.length;
        StringBuilder[] dp = new StringBuilder[target + 1];
        dp[0] = new StringBuilder();
        for (int i = m; i > 0 ; i--) {
            for (int j = 0; j <= target; j++) {
                if(j + cost[i - 1] <= target) {
                    StringBuilder bef = dp[j + cost[i - 1]];
                    StringBuilder cur = dp[j] == null ? null: new StringBuilder(dp[j]).append(i);
                    if(compare(cur, bef) > 0) {
                        dp[j + cost[i - 1]] = cur;
                    }
                }
            }
        }
        Utils.printArrays(dp);
        return dp[target] == null ? "0" : dp[target].toString();
    }
    // TODO 变态
    public static String largestNumber(int[] cost, int target) {
        // dp[i]表示 长度
        int[] dp = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            dp[i] = Integer.MIN_VALUE;
            for (int j = 0; j < 9; j++) {
                if(i >= cost[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[i - cost[j]]);
                }
            }
        }
        if(dp[target] < 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 9; i > 0 ; i--) {
            while(target >= cost[i - 1] && dp[target] == dp[target - cost[i - 1]] + 1) {
                res.append(i);
                target -= cost[i - 1];
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {

        //;null;null;null;null;5;6;8;9;null;55;65;66;
        //;null;null;null;null;5;6;8;9;null;55;65;85;
        //[7,6,5,5,5,6,8,7,8] 12
        System.out.println(largestNumber(new int[]{7,6,5,5,5,6,8,7,8}, 12));


        System.out.println(largestNumber(new int[]{4,3,2,5,6,7,2,5,5}, 9));

        System.out.println(largestNumber(new int[]{4,3,2,1,5,6,7,8,9}, 9));
        System.out.println("997".compareTo("7772"));
        List<int[]> read = Optional.ofNullable(Utils.read("./src/main/resources/non-negative.txt", 9, true)).orElse(new ArrayList<>());
        for (int[] nums : read) {
            int[] newNums = new int[9];
            int index = 0;
            for (int n : nums) {
                newNums[index++] = n % 100;
            }
            Utils.printArrays(newNums);
            System.out.println(largestNumber1(newNums, 23) );
            System.out.println(largestNumber(newNums, 23) );
        }
    }
}
