package com.leetcode.integers;

/**
 * @author zms
 * @date 4:18 PM 2019/12/23
 */
public class Palindrome {
    public static boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        }
        if(x < 10) {
            return true;
        }
//        int log = (int)Math.log10(x);
//        int count = log / 2 ;
//        if(log % 2 != 0) {
//            count += 1;
//        }
//
//        for(int i = 0; i< count; i++) {
//            int i1 = x / (int)Math.pow(10, log - i);
//            int i2 = x % ((int)Math.pow(10, i + 1)) / (int)Math.pow(10, i);
//            if(i1 != i2) {
//                return false;
//            }
//            x = x - i1 * ((int)Math.pow(10, log - i));
//        }
        if(x % 10 == 0) {
            return false;
        }

        int res = 0;
        while (res < x) {
            res = res * 10 + x % 10;
            x = x / 10;
        }
        System.out.println(res / 10);
        System.out.println(x);
        return res / 10 == x ||res == x;
    }

    public static void main(String[] args) {
//        System.out.println((int)Math.log10(222));
//        System.out.println(222 % 10);
        System.out.println(isPalindrome(121));
        System.out.println(Math.log(123321) / Math.log(10));

//        System.out.println(Math.pow(10, 3));

    }

}
