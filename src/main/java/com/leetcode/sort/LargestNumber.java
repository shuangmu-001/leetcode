package com.leetcode.sort;

import java.util.*;

/**
 * @author wcl
 * @date 6:08 PM 2020/5/14
 * <a href="https://leetcode.com/problems/largest-number/">
 * Largest Number</a>
 */
public class LargestNumber {
    /**
     * Given a list of non negative integers, arrange them such that they form the largest number.
     *
     * Example 1:
     *
     * Input: [10,2]
     * Output: "210"
     * Example 2:
     *
     * Input: [3,30,34,5,9]
     * Output: "9534330"
     * Note: The result may be very large, so you need to return a string instead of an integer.
     */
    public static String largestNumber(int[] nums) {
        if(nums== null || nums.length == 0) {
            return "";
        }
        int length = nums.length;
        if(length == 1) {
            return nums[0] + "";
        }
        String[] strs = new String[length];
        int index = 0;
        for (int n : nums) {
           strs[index++] = String.valueOf(n);
        }
//        Arrays.sort(strs, (a, b) -> {
//            int len1 = a.length();
//            int len2 = b.length();
//            int aIndex = 0;
//            int bIndex = 0;
//            for (int i = 0; i < len1 + len2; i++) {
//                aIndex = aIndex < len1 ? aIndex : 0;
//                char ac = a.charAt(aIndex++);
//                bIndex = bIndex < len2 ? bIndex : 0;
//                char bc = b.charAt(bIndex++);
//                if(ac > bc) {
//                    return 1;
//                } else if(ac < bc) {
//                    return -1;
//                }
//            }
//            return 0;
//        });
        Arrays.sort(strs, new LargerNumberComparator());
        if("0".equals(strs[length - 1])) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }

    static class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            int len1 = a.length();
            int len2 = b.length();
            int aIndex = 0;
            int bIndex = 0;
            for (int i = 0; i < len1 + len2; i++) {
                aIndex = aIndex < len1 ? aIndex : 0;
                char ac = a.charAt(aIndex++);
                bIndex = bIndex < len2 ? bIndex : 0;
                char bc = b.charAt(bIndex++);
                if(ac > bc) {
                    return 1;
                } else if(ac < bc) {
                    return -1;
                }
            }
            return 0;
        }
    }


    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{1,2,3,4,5,6,7,8,9,0}));
        Number number1 = new Number(1, 0);
        Number number2 = new Number(1,9);
        System.out.println(number1.compareTo(number2));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Math.pow(10, 0));
        System.out.println(Math.pow(10, 1));
    }

    static class Number implements Comparable<Number>{
        int len;
        long num;

        public Number(int len, long num) {
            this.len = num == 0 ? 1 : len;
            this.num = num;

        }

        @Override
        public int compareTo(Number o) {
            long pow1 = (long)Math.pow(10, o.len);
            Long t1 = this.num * pow1 + o.num;
            long pow2 = (long)Math.pow(10, this.len);
            Long t2 = o.num * pow2 + this.num;
            return t1.compareTo(t2);
        }
    }
}
