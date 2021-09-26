package com.leetcode.slidingwindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zms
 * @date 10:47 上午 2020/5/25
 */
public class SlidingWindow {

    public static int[] getMax(int[] nums, int k) {
        int length = nums.length;
        int newLen = length - k + 1;
        int[] res = new int[newLen];
        int index = 0;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            while(!deque.isEmpty() && deque.getLast() < nums[i]) {
                deque.pollLast();
            }
            deque.add(nums[i]);
            if(i >= k - 1) {
                if(i >= k && nums[i - k] == deque.getFirst()) {
                    deque.removeFirstOccurrence(nums[i - k]);
                }
                res[index++] = deque.getFirst();
            }
        }
        return res;
    }

    public static int[] getMin(int[] nums, int k) {
        int length = nums.length;
        int newLen = length - k + 1;
        int[] res = new int[newLen];
        int index = 0;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            while(!deque.isEmpty() && deque.getLast() > nums[i]) {
                deque.pollLast();
            }
            deque.add(nums[i]);
            if(i >= k - 1) {
                if(i >= k && nums[i - k] == deque.getFirst()) {
                    deque.removeFirstOccurrence(nums[i - k]);
                }
                res[index++] = deque.getFirst();
            }
            System.out.println(deque);
        }
        return res;
    }

}
