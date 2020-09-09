package com.leetcode.dp.linear;

/**
 * @Author: wcl
 * @Description:
 * @Date: Create in 11:14 上午 2019/11/12
 * @Modified By:
 */
public class MaximumSubarray {

    public static void main(String[] args) {
//        new Thread(() ->{
//            Thread thread = Thread.currentThread();
//            System.out.println(thread.getName());
//            System.out.println(thread.getThreadGroup());
//            Thread thread1 = new Thread(() -> {
//                Thread thread2 = Thread.currentThread();
//                System.out.println(thread2.getName());
//                System.out.println(thread2.getThreadGroup());
//            });
//            thread1.setDaemon(true);
//            thread1.start();
//        }).start();
        System.out.println(sumSub1(new int[] {1, -3, -4, 5}));
        System.out.println(sumSub2(new int[] {-3,-2,1,2,2,0,1,0}));
    }

    public static int sumSub1(int[] a) {
        int maxSum = 0;
        int thisSum;
        int length = a.length;
        for(int i = 0; i < length; i++) {
            thisSum = 0;
            for(int j = i; j < length; j++) {
                thisSum += a[j];
                if(thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    public static int sumSub2(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return Integer.MIN_VALUE;
        }

        int maxSum = nums[0];
        int thisSum = 0;

        for (int i = 0; i < length; i++) {
            if(nums[i] >= 0 && thisSum < 0) {
                thisSum = 0;
            }
            thisSum += nums[i];
            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }
        return maxSum;
    }

    public static int sumSub3(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int num : nums) {
            sum = Math.max(num, num + sum);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

}
