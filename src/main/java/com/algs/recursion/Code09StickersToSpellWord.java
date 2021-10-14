package com.algs.recursion;

/**
 * <a href="https://leetcode.com/problems/stickers-to-spell-word/">Stickers to Spell Word</a>
 *
 * @author zms
 * @date 3:30 下午 2021/10/14
 */
public class Code09StickersToSpellWord {

    // 给定一个字符串 str, 给定一个字符串类型的数组arr，出现的字符都是小写英文字母
    // arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来，
    // 可以不止一次地使用每一张贴纸，而且每一张贴纸的数量都是无限的
    // 返回需要至少多少张贴纸可以完成这个任务，
    // 例子：str = "babac", arr= {"ba", "c", "abcd"}
    // 至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，
    // 含有2个a、2个b、1个c。是可以拼出str的，所以返回2
    public static int minStickers1(String[] stickers, String target) {
        return 0;
    }
}
