package com.leetcode.sort;


/**
 * @author zms
 * @date 5:44 下午 2020/8/25
 * <a href="https://leetcode.com/problems/shuffle-string/">Shuffle String</a>
 */
public class ShuffleString {
    /**
     * Given a string s and an integer array indices of the same length.
     * <p>
     * The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
     * <p>
     * Return the shuffled string.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
     * Output: "leetcode"
     * Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
     * Example 2:
     * <p>
     * Input: s = "abc", indices = [0,1,2]
     * Output: "abc"
     * Explanation: After shuffling, each character remains in its position.
     * Example 3:
     * <p>
     * Input: s = "aiohn", indices = [3,1,4,2,0]
     * Output: "nihao"
     * Example 4:
     * <p>
     * Input: s = "aaiougrt", indices = [4,0,2,6,7,3,1,5]
     * Output: "arigatou"
     * Example 5:
     * <p>
     * Input: s = "art", indices = [1,0,2]
     * Output: "rat"
     * <p>
     * <p>
     * Constraints:
     * <p>
     * s.length == indices.length == n
     * 1 <= n <= 100
     * s contains only lower-case English letters.
     * 0 <= indices[i] < n
     * All values of indices are unique (i.e. indices is a permutation of the integers from 0 to n - 1).
     */
    public static String restoreString(String s, int[] indices) {
        int len = s.length();
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[indices[i]] = s.charAt(i);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println("leetcode".equals(restoreString("codeleet", new int[]{4, 5, 6, 7, 0, 2, 1, 3})));
        System.out.println("abc".equals(restoreString("abc", new int[]{0, 1, 2})));
        System.out.println("nihao".equals(restoreString("aiohn", new int[]{3,1,4,2,0})));
        System.out.println("arigatou".equals(restoreString("aaiougrt", new int[]{4, 0, 2, 6, 7, 3, 1, 5})));
        System.out.println("rat".equals(restoreString("art", new int[]{1, 0, 2})));
    }
}
