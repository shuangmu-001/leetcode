package com.question.day05;

/**
 * TODO <a herf="https://leetcode.com/problems/edit-distance/">edit distance</a>
 *
 * @author zms
 * @date 6:09 下午 2021/9/23
 */
public class Code03EditCost {

    /**
     * 给你两个单词word1 和word2，请你计算出将word1转换成word2 所使用的最少操作数。
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     * <p>
     * 示例1：
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     * <p>
     * 示例2：
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     * <p>
     * <p>
     * 提示：
     * 0 <= word1.length, word2.length <= 500
     * word1 和 word2 由小写英文字母组成
     */
    public int minDistance(String word1, String word2) {
        return 0;
    }
}
