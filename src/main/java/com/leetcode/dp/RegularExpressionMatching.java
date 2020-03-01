package com.leetcode.dp;

/**
 * @author wcl
 * @date Create in 5:12 下午 2019/11/26
 * TODO
 */
public class RegularExpressionMatching {
    /**
     * Given an input string (s) and a pattern (p),
     * implement regular expression matching with support for '.' and '*'.
     *
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     *
     * @param s could be empty and contains only lowercase letters a-z.
     * @param p could be empty and contains only lowercase letters a-z,
     *           and characters like . or *.
     * @return boolean
     */
    private static boolean isMatch(String s, String p) {
        char[] sChar = s.toCharArray();
        char[] pChars = p.toCharArray();
        int max = 0;
        int sLen = s.length();
        int pLen = p.length();
        if(sLen > pLen) {
            return false;
        }
        if(sLen == pLen && sLen == 0) {
            return true;
        }
        int[] arr = new int[sLen];
        for(int i = 0; i < sLen; i++) {
            for(int j = i, l = 0; j < pLen - sLen + i + 1; j++ ) {
                boolean a = pChars[j] == '.' || sChar[i] == pChars[j];

                if(a || (pChars[j] == '*' && j != 0 && pChars[j - 1] == pChars[j])) {
                    arr[l] += 1;
                    max = Math.max(arr[l], max);
                    l++;
                }

            }
        }

        return max == sLen - 1;
//        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
//        dp[s.length()][p.length()] = true;
//
//        for (int i = s.length(); i >= 0; i--){
//            for (int j = p.length() - 1; j >= 0; j--){
//                boolean first_match = (i < s.length() &&
//                        (p.charAt(j) == s.charAt(i) ||
//                                p.charAt(j) == '.'));
//                if (j + 1 < p.length() && p.charAt(j+1) == '*'){
//                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
//                } else {
//                    dp[i][j] = first_match && dp[i+1][j+1];
//                }
//            }
//        }
//        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("", ""));
        System.out.println(!isMatch("a", ""));
        System.out.println(!isMatch("aa", "a"));
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("ab", ".*"));
        System.out.println(isMatch("aab", "c*a*c*b"));
        System.out.println(!isMatch("mississippi", "mis*is*p*."));
    }

}
