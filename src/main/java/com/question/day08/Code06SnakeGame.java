package com.question.day08;

import com.Test;
import com.Utils;


/**
 * @author wcl
 * @date 5:35 下午 2021/9/26
 */
public class Code06SnakeGame implements Test {

    // 贪吃蛇

    // 给定一个矩阵matrix，值有正、负、0，蛇可以空降到最左列的任何一个位置，出事增长值是0，
    // 蛇每一步可以选择右上、右、右下三个方向的任何一个前进
    // 沿途的数字累加起来，作为增长值，但是蛇一旦增长值为负数，就会死去，蛇有一种能力，
    // 可以使用一次，把某个格子里的数变成相反数，蛇可以走到任何格子的时候停止，
    // 返回蛇能获得的最大增长值

    public int walk1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length, m = matrix[0].length, ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int[] process = process(matrix, i, j);
                ans = Math.max(ans, Math.max(process[0], process[1]));
            }
        }
        return ans;
    }

    // 蛇到达(i,j)这个位置的最大增长值
    // int[0] 表示没有使用能力的最大值
    // int[1] 表示使用能力的最大值
    public int[] process(int[][] matrix, int i, int j) {
        // 蛇落的位置
        if (j == 0) {
            int no = Math.max(matrix[i][j], -1);
            int yes = Math.max(-matrix[i][j], -1);
            return new int[]{no, yes};
        }
        // i,j的左侧
        int[] left = process(matrix, i, j - 1);
        int no = left[0];
        int yes = left[1];
        // i,j的左上
        if (i > 0) {
            int[] leftUp = process(matrix, i - 1, j - 1);
            no = Math.max(no, leftUp[0]);
            yes = Math.max(yes, leftUp[1]);
        }
        // i,j的左下
        if (i < matrix.length - 1) {
            int[] leftDown = process(matrix, i + 1, j - 1);
            no = Math.max(no, leftDown[0]);
            yes = Math.max(yes, leftDown[1]);
        }
        int curNo = no == -1 ? -1 : Math.max(no + matrix[i][j], -1);
        // 当前位置使用能力
        int p1 = no == -1 ? -1 : Math.max(no - matrix[i][j], -1);
        // 当前位置不使用能力，前面位置使用能力
        int p2 = yes == -1 ? -1 : Math.max(yes + matrix[i][j], -1);
        yes = Math.max(p1, p2);
        return new int[]{curNo, yes};
    }

    // -1 表示蛇到不了当前位置
    public int walk(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length, m = matrix[0].length, ans = Integer.MIN_VALUE;
        // 没有使用能力走到当前格子的最大增长值
        int[] no = new int[n];
        // 使用能力走到当前格子的最大增长值
        int[] yes = new int[n];
        int curNo = 0;
        int curYes = 0;
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                int tempNo = no[i];
                int tempYes = yes[i];
                // 空降到最左列
                if (j == 0) {
                    no[i] = Math.max(matrix[i][j], -1);
                    yes[i] = Math.max(-matrix[i][j], -1);
                } else {
                    // 左边 一直存在
                    int maxNo = tempNo;
                    int maxYes = tempYes;
                    // 当不是第一行 左上存在
                    if (i > 0) {
                        maxNo = Math.max(maxNo, curNo);
                        maxYes = Math.max(maxYes, curYes);
                    }
                    // 当不是最后一行 左下存在
                    if (i < n - 1) {
                        maxNo = Math.max(maxNo, no[i + 1]);
                        maxYes = Math.max(maxYes, yes[i + 1]);
                    }
                    // maxNo == -1 表示到不了当前节点，
                    no[i] = maxNo == -1 ? -1 : Math.max(maxNo + matrix[i][j], -1);
                    // 当前位置使用能力
                    int p1 = maxNo == -1 ? -1 : Math.max(maxNo - matrix[i][j], -1);
                    // 当前位置不使用能力，前面位置使用能力
                    int p2 = maxYes == -1 ? -1 : Math.max(maxYes + matrix[i][j], -1);
                    yes[i] = Math.max(p1, p2);
                }
                ans = Math.max(ans, Math.max(no[i], yes[i]));
                curNo = tempNo;
                curYes = tempYes;
            }
        }
        return ans;
    }

    @Override
    public void test(int n) {
        int r = (int) (Math.random() * (n + 1));
        int c = (int) (Math.random() * (n + 1));
        int[][] matrix = genRandomTwoArr(r, c, 10);
        int ans1 = walk(matrix);
        int ans2 = walk1(matrix);
        if (ans1 != ans2) {
            Utils.printTwoArrays(matrix);
            System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
            throw new RuntimeException("数据不一致");
        }
    }
}