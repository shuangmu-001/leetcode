package com.leetcode.search;

/**
 * @author wcl
 * @date 4:32 PM 2020/4/3
 * <a href="https://leetcode.com/problems/find-smallest-letter-greater-than-target/">
 *     Find Smallest Letter Greater Than Target</a>
 */
public class FindSmallestLetterGreaterThanTarget {
    /**
     * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.
     *
     * Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
     *
     * Examples:
     * Input:
     * letters = ["c", "f", "j"]
     * target = "a"
     * Output: "c"
     *
     * Input:
     * letters = ["c", "f", "j"]
     * target = "c"
     * Output: "f"
     *
     * Input:
     * letters = ["c", "f", "j"]
     * target = "d"
     * Output: "f"
     *
     * Input:
     * letters = ["c", "f", "j"]
     * target = "g"
     * Output: "j"
     *
     * Input:
     * letters = ["c", "f", "j"]
     * target = "j"
     * Output: "c"
     *
     * Input:
     * letters = ["c", "f", "j"]
     * target = "k"
     * Output: "c"
     * Note:
     * letters has a length in range [2, 10000].
     * letters consists of lowercase letters, and contains at least 2 unique letters.
     * target is a lowercase letter.
     */
    public static char nextGreatestLetter(char[] letters, char target) {
        for (char c: letters) {
            if (c > target) {
                return c;
            }
        }
        return letters[0];
    }

    public static void main(String[] args) {
        System.out.println(nextGreatestLetter(new char[]{'c','a','c','f','j'}, 'a'));
    }
}
