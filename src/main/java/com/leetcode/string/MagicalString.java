package com.leetcode.string;

/**
 * @author zms
 * @date 4:53 PM 2020/1/9
 * {@link "https://leetcode.com/problems/magical-string/"}
 */
public class MagicalString {
    /**
     * A magical string S consists of only '1' and '2' and obeys the following rules:
     * The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.
     * The first few elements of string S is the following: S = "1221121221221121122……"
     * If we group the consecutive '1's and '2's in S, it will be:
     * 1 22 11 2 1 22 1 22 11 2 11 22 ......
     * and the occurrences of '1's or '2's in each group are:
     * 1 2 2 1 1 2 1 2 2 1 2 2 ......
     * You can see that the occurrence sequence above is the S itself.
     * Given an integer N as input, return the number of '1's in the first N number in the magical string S.
     * <p>
     * Note: N will not exceed 100,000.
     * <p>
     * Example 1:
     * Input: 6
     * Output: 3
     * Explanation: The first 6 elements of magical string S is "12211" and it contains three 1's, so return 3.
     */
    public static int magicalString(int n) {
        int result = 0;
        int nextChar = 1;
        int length = 1;
        int[] str = new int[n];
        for (int i = 0, j = 0; i < n; i += length, j++) {
            str[i] = nextChar;
            length = str[j] == 0 ? str[j - 1] : str[j];
            if (nextChar == 1) {
                result += length;
                nextChar = 2;
            } else {
                nextChar = 1;
            }
        }
        return length == 2 && str[n - 1] == 1 ? result - 1 : result;
    }

    public static void main(String[] args) {
//        System.out.println(magicalString(0) == 0);
//        System.out.println(magicalString(1) == 1);
//        System.out.println(magicalString(2) == 1);
//        System.out.println(magicalString(3) == 1);
//        System.out.println(magicalString(4) == 2);
//        System.out.println(magicalString(5) == 3);
//        System.out.println(magicalString(6) == 3);
//        System.out.println(magicalString(7) == 4);
//        System.out.println(magicalString(8) == 4);
//        System.out.println(magicalString(9) == 4);
//        System.out.println(magicalString(10) == 5);
//        System.out.println(magicalString(11) == 5);
//        System.out.println(magicalString(12) == 5);
//        System.out.println(magicalString(13) == 6);
        new MagicalString().countAndSay(5);
    }

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        if (n == 2) {
            return "11";
        }
        String before = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        int slow = 0;
        for (int i = 1; i < before.length(); i++) {
            if (before.charAt(slow) != before.charAt(i)) {
                sb.append(i - slow).append(before.charAt(slow));
                slow = i;
            }
            if (before.length() - 1 == i) {
                int count = i - slow > 0 ? i - slow + 1 : 1;
                sb.append(count).append(before.charAt(i));
            }
        }
        return sb.toString();
    }
}
