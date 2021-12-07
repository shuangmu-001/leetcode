package com.question.day09;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO <a href="https://leetcode.com/problems/remove-invalid-parentheses/">remove invalid parentheses</a>
 *
 * @author zms
 * @date 6:52 下午 2021/9/27
 */
public class Code02RemoveInvalidParentheses {
    /**
     * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
     * 返回所有可能的结果。答案可以按 任意顺序 返回。
     * <p>
     * 示例 1：
     * 输入：s = "()())()"
     * 输出：["(())()","()()()"]
     * <p>
     * 示例 2：
     * 输入：s = "(a)())()"
     * 输出：["(a())()","(a)()()"]
     * <p>
     * 示例 3：
     * 输入：s = ")("
     * 输出：[""]
     * <p>
     * 提示：
     * 1 <= s.length <= 25
     * s 由小写英文字母以及括号 '(' 和 ')' 组成
     * s 中至多含 20 个括号
     */
    public static List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public static void remove(String s, List<String> ans, int checkIndex, int removeIndex, char[] t) {
        for (int count = 0, i = checkIndex; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == t[0]) {
                count++;
            }
            if (c == t[1]) {
                count--;
            }
            if (count < 0) {
                for (int j = removeIndex; j <= i; j++) {
                    // ")("
                    if (s.charAt(j) == t[1] && (j == 0 || s.charAt(j - 1) != t[1])) {
                        remove(s.substring(0, j) + s.substring(j + 1), ans, i, j, t);
                    }
                }
                return;
            }
        }
        String reverse = new StringBuilder(s).reverse().toString();
        if (t[0] == '(') {
            remove(reverse, ans, 0, 0, new char[]{')', '('});
        } else {
            ans.add(reverse);
        }
    }

    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("()())()"));
    }
}
