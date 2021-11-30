package com.question.day09;

/**
 * @author zms
 * @date 4:36 下午 2021/9/27
 */
public class Code06IsStepSum {

    // 定义何为step sum?
    // 比如680，680 + 68 + 6 = 754， 680的step sum叫754
    // 给定一个正数num，判断它是不是某个数的step sum;
    // 通过stepSum 求num
    public int getNum(int stepSum) {
        if (stepSum < 10) {
            return stepSum;
        }
        int start = 10;
        int end = stepSum;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            int target = getStepSum(mid);
            if (target > stepSum) {
                end = mid - 1;
            } else if (target < stepSum) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int getStepSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num;
            num /= 10;
        }
        return sum;
    }

    public boolean isStepSum(int num, int stepSum) {
        if (num < stepSum) {
            return false;
        }
        int sum = 0;
        while (num != 0) {
            sum += num;
            num /= 10;
        }
        return sum == stepSum;
    }
}
