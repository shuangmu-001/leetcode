package com.leetcode;

/**
 * @author wcl
 * @date 3:28 PM 2019/12/10
 */
public class ReverseInteger {

    /**
     * Given a 32-bit signed integer, reverse digits of an integer.
     *
     * @param x signed integer
     * @return int
     */
    public static int reverse(int x) {
        if(x == Integer.MIN_VALUE) {
            return 0;
        }
        boolean flag = x > 0;
        x = flag ? x : -x;
        long newNum = 0;
        while (x != 0) {
            long c = x % 10;
            x = x / 10;
            newNum = newNum * 10L + c;
        }
        if(newNum > Integer.MAX_VALUE) {
            return 0;
        }
        if(newNum < Integer.MIN_VALUE) {
            return 0;
        }
        int result = (int) newNum;
        return flag ? result : -result;
    }

    public static void main(String[] args) {
//        System.out.println(reverse(-123) == -321);
        System.out.println(reverse(-2147483648));
//        System.out.println(reverse(120) == 21);
    }

}
