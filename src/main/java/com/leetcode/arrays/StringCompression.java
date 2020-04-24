package com.leetcode.arrays;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 10:59 AM 2020/4/22
 * <a href='https://leetcode.com/problems/string-compression/'>
 *     String Compression</a>
 */
public class StringCompression {
    /**
     * Given an array of characters, compress it in-place.
     *
     * The length after compression must always be smaller than or equal to the original array.
     *
     * Every element of the array should be a character (not int) of length 1.
     *
     * After you are done modifying the input array in-place, return the new length of the array.
     *
     *
     * Follow up:
     * Could you solve it using only O(1) extra space?
     *
     *
     * Example 1:
     *
     * Input:
     * ['a','a','b','b','c','c','c']
     *
     * Output:
     * Return 6, and the first 6 characters of the input array should be: ['a','2','b','2','c','3']
     *
     * Explanation:
     * 'aa' is replaced by 'a2'. 'bb' is replaced by 'b2'. 'ccc' is replaced by 'c3'.
     *
     *
     * Example 2:
     *
     * Input:
     * ['a']
     *
     * Output:
     * Return 1, and the first 1 characters of the input array should be: ['a']
     *
     * Explanation:
     * Nothing is replaced.
     *
     *
     * Example 3:
     *
     * Input:
     * ['a','b','b','b','b','b','b','b','b','b','b','b','b']
     * Output:
     * Return 4, and the first 4 characters of the input array should be: ['a','b','1','2'].
     * Explanation:
     * Since the character 'a' does not repeat, it is not compressed. 'bbbbbbbbbbbb' is replaced by 'b12'.
     * Notice each digit has it's own entry in the array.
     *
     * Note:
     *
     * All characters have an ASCII value in [35, 126].
     * 1 <= len(chars) <= 1000.
     */
    public static int compress(char[] chars) {
        if(chars.length == 1) {
            return 1;
        }
        char before = chars[0];
        int count = 1;
        int index = 1;
        for (int i = 1; i < chars.length; i++) {

            if(chars[i] != before || i == chars.length - 1) {
                if(chars[i] == before) {
                    count++;
                }
                if(count > 1 && count < 10) {
                    chars[index++] = (char)('0' + count);
                } else if(count >= 10 && count < 100) {
                    chars[index++] = (char)('0' + count / 10);
                    chars[index++] = (char)('0' + count % 10);
                } else if(count >= 100 && count < 1000) {
                    chars[index++] = (char)('0' + count / 100);
                    chars[index++] = (char)('0' + (count % 100) / 10);
                    chars[index++] = (char)('0' + (count % 100) % 10);
                }
                if(index < chars.length && chars[i] != before) {
                    chars[index++] = chars[i];
                }

                count = 1;
                before = chars[i];

            } else {
                count++;
            }
        }
        Utils.printArrays(chars);
        return index;
    }

    public static void main(String[] args) {
        System.out.println(compress(new char[]{'a'}) == 1);

        System.out.println(compress(new char[]{'a','a'}) == 2);

        System.out.println(compress(new char[]{'a','b'}) == 2);

        System.out.println(compress(new char[]{'a','b','c'}) == 3);

        System.out.println(compress(new char[]{'a','b','b'}) == 3);

        System.out.println(compress(new char[]{'a','a','b','b'}) == 4);

        System.out.println(compress(new char[]{'a','a','b','b','c'}) == 5);

        System.out.println(compress(new char[]{'a','a','b','b','c','c','c'}) == 6);

        char[] ch = new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'};
        System.out.println(ch.length);
        System.out.println(compress(ch));
    }
}
