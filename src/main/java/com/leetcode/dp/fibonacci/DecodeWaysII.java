package com.leetcode.dp.fibonacci;

/**
 * @author wcl
 * @date 11:31 上午 2020/9/11
 * <a href="https://leetcode.com/problems/decode-ways-ii/">Decode Ways II</a>
 */
public class DecodeWaysII {
    /**
     * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.
     *
     * Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
     *
     * Also, since the answer may be very large, you should return the output mod 10^9 + 7.
     *
     * Example 1:
     * Input: "*"
     * Output: 9
     * Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
     * Example 2:
     * Input: "1*"
     * Output: 9 + 9 = 18
     * Note:
     * The length of the input string will fit in range [1, 105].
     * The input string will only contain the character '*' and digits '0' - '9'.
     */
    private static final int MOD = 1_000_000_007;

    public static int numDecodings(String s) {
        char c = s.charAt(0);
        if(c == '0') {
            return 0;
        }
        long first = 1;
        long second = c == '*' ? 9 : 1;
        for (int i = 1; i < s.length(); i++) {
            char before = s.charAt(i - 1);
            char cur = s.charAt(i);
            if(cur == '0') {
                if(before == '1' || before == '2') {
                    second = first;
                } else if (before == '*') {
                    second = first * 2;
                } else {
                    return 0;
                }
            } else if( cur != '*'){
                long temp = second;
                if(before == '1' || (before == '2' && cur <= '6')) {
                    temp += first;
                } else if( before == '*') {
                    if(cur <= '6') {
                        temp += (first * 2);
                    } else {
                        temp += first;
                    }
                }
                first = second;
                second = temp;
            } else {
                long temp = second * 9;
                if(before == '1') {
                    temp += (first * 9);
                } else if(before == '2') {
                    temp += (first * 6);
                } else if(before == '*') {
                    temp += (first * 9 + first * 6);
                }
                first = second;
                second = temp;
            }
        }
        return (int)(second % MOD);
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("1***1210310**0*14*1321**123123*212") == 534318276);
        System.out.println(numDecodings("*") == 9);
        System.out.println(numDecodings("**") == 96);
        System.out.println(numDecodings("1*") == 18);
        System.out.println(numDecodings("2*") == 15);
    }
}
