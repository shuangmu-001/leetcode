package com.leetcode.greedy;

import java.util.Stack;

/**
 * @author wcl
 * @date 3:55 PM 2020/5/13
 * <a href="https://leetcode.com/problems/remove-k-digits/">
 * Remove K Digits</a>
 */
public class RemoveKDigits {
    /**
     * Given a non-negative integer num represented as a string,
     * remove k digits from the number so that the new number is the smallest possible.
     *
     * Note:
     * The length of num is less than 10002 and will be ≥ k.
     * The given num does not contain any leading zero.
     *
     * Example 1:
     * Input: num = "1432219", k = 3
     * Output: "1219"
     * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
     *
     * Example 2:
     * Input: num = "10200", k = 1
     * Output: "200"
     * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
     *
     * Example 3:
     * Input: num = "10", k = 2
     * Output: "0"
     * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
     */
    public static String removeKdigits(String num, int k) {
        if(num == null || num.length() == 0 || num.length() <= k) {
            return "0";
        }
        char[] chars = num.toCharArray();
        int length = num.length();
        int count = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            // 入栈数据必须不大于当前数据
            while(!stack.isEmpty() && count < k && stack.peek() > chars[i]){
                stack.pop();
                count++;
            }
            stack.push(chars[i]);
        }
        // 1432219
        // 143442219
        while(count < k) {
            stack.pop();
            count++;
        }
        int index = length - k - 1;
        while(!stack.isEmpty()) {
            char c = stack.pop();
            chars[index--] = c;
        }
        index = 0;
        int newLen = length - k;
        // 开头不能是'0'
        while(index < length && chars[index] == '0') {
            index++;
            newLen--;
        }
        if(index >= (length - k)) {
            return "0";
        }
        return new String(chars, index, newLen);
    }


    public static void main(String[] args) {
//        char[] chars = new char[]{'1','4','3','2'};
//        char[] chars1 = new char[]{'1',0,0,'2'};
//        System.out.println(new String(chars));
//        System.out.println(new String(chars1));
//        System.out.println(removeKdigits("1432219", 3));
//        System.out.println(removeKdigits("1434419", 4));
//        System.out.println(removeKdigits("123459", 4));
//        System.out.println(removeKdigits("1023459", 4));
//        System.out.println(removeKdigits("143442219", 4));
        System.out.println(removeKdigits("1234567890", 9));
    }
}
