package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zms
 * @date 4:03 下午 2020/9/21
 * <a href="https://leetcode.com/problems/word-search/">Word Search</a>
 */
public class WordSearch {
    /**
     * Given a 2D board and a word, find if the word exists in the grid.
     * The word can be constructed from letters of sequentially adjacent cell,
     * where "adjacent" cells are those horizontally or vertically neighboring.
     * The same letter cell may not be used more than once.
     * <p>
     * Example:
     * <p>
     * board =
     * [
     * ['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']
     * ]
     * <p>
     * Given word = "ABCCED", return true.
     * Given word = "SEE", return true.
     * Given word = "ABCB", return false.
     * <p>
     * Constraints:
     * board and word consists only of lowercase and uppercase English letters.
     * 1 <= board.length <= 200
     * 1 <= board[i].length <= 200
     * 1 <= word.length <= 10^3
     */
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    boolean res;
    boolean[][] flag;
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        if (m * n < word.length()) {
            return false;
        }
        flag = new boolean[m][n];
        char[] chars = word.toCharArray();
        List<int[]> startIndex = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == chars[0]) {
                    startIndex.add(new int[]{i, j});
                }
            }
        }
        if(startIndex.isEmpty()) {
            return false;
        }
        for(int[] index : startIndex) {
            if(res) {
                return true;
            }
            flag[index[0]][index[1]] = true;
            dfs(board, index[0], index[1], chars, 1);
            flag[index[0]][index[1]] = false;
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, char[] chars, int index) {
        if(res) {
            return;
        }
        if(index >= chars.length) {
            res = true;
            return;
        }
        for (int k = 0; k < 4; k++) {
            int x = i + dirs[k][0];
            int y = j + dirs[k][1];
            if(x >= 0 && x < board.length && y >=0 && y < board[0].length) {
                if(chars[index] == board[x][y] && !flag[x][y]) {
                    flag[x][y] = true;
                    dfs(board, x, y, chars, index + 1);
                    flag[x][y] = false;
                }
            }
        }
    }
}
