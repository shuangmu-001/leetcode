package com.leetcode.hash;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wcl
 * @date 9:43 PM 2020/5/2
 * <a href="https://leetcode.com/problems/jewels-and-stones/">
 *     Jewels and Stones</a>
 */
public class JewelsAndStones {
    /**
     * You're given strings J representing the types of stones that are jewels,
     * and S representing the stones you have.  Each character in S is a type of stone you have.
     * You want to know how many of the stones you have are also jewels.
     * The letters in J are guaranteed distinct, and all characters in J and S are letters.
     * Letters are case sensitive, so "a" is considered a different type of stone from "A".
     *
     * Example 1:
     * Input: J = "aA", S = "aAAbbbb"
     * Output: 3
     *
     * Example 2:
     * Input: J = "z", S = "ZZ"
     * Output: 0
     *
     * Note:
     * S and J will consist of letters and have length at most 50.
     * The characters in J are distinct.
     */
    public int numJewelsInStones1(String J, String S) {
        if(S == null || S.length() == 0 || J == null || J.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }
        int count = 0;
        for (char c : J.toCharArray()) {
            count += map.getOrDefault(c, 0);
        }
        return count;
    }

    public int numJewelsInStones(String J, String S) {
        if(S == null || S.length() == 0 || J == null || J.length() == 0) {
            return 0;
        }
        int count = 0;
        Set<Character> set = new HashSet<>();
        for (char c : J.toCharArray()) {
            set.add(c);
        }
        for (char c : S.toCharArray()) {
            if(set.contains(c)) {
                count++;
            }
        }

        return count;
    }
}
