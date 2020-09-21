package com.leetcode.backtracking;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wcl
 * @date 4:41 下午 2020/9/21
 * <a href="https://leetcode.com/problems/word-search-ii/">Word Search II</a>
 */
public class WordSearchII {
    /**
     * Given a 2D board and a list of words from the dictionary, find all words in the board.
     *
     * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
     *
     *
     *
     * Example:
     *
     * Input:
     * board = [
     *   ['o','a','a','n'],
     *   ['e','t','a','e'],
     *   ['i','h','k','r'],
     *   ['i','f','l','v']
     * ]
     * words = ["oath","pea","eat","rain"]
     *
     * Output: ["eat","oath"]
     *
     *
     * Note:
     *
     * All inputs are consist of lowercase letters a-z.
     * The values of words are distinct.
     */
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    boolean[][] flags;
    boolean flag = false;
    public List<String> findWords(char[][] board, String[] words) {
        Map<Character, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                List<int[]> index = new ArrayList<>();
                if(map.containsKey(board[i][j])) {
                    index = map.get(board[i][j]);
                }
                index.add(new int[]{i,j});
                map.put(board[i][j], index);
            }
        }
        flags = new boolean[board.length][board[0].length];
        List<String> res = new ArrayList<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            if(!map.containsKey(chars[0])) {
                continue;
            }
            List<int[]> ints = map.get(chars[0]);
            flag = false;
            for (int[] index : ints) {
                if(flag) {
                    break;
                }
                flags[index[0]][index[1]] = true;
                dfs(board, index[0], index[1], chars, 1);
                flags[index[0]][index[1]] = false;
            }
            if(flag) {
                res.add(word);
            }
        }
        return res;
    }

    private void dfs(char[][] board, int i, int j, char[] chars, int index) {
        if(flag) {
            return;
        }
        if(index >= chars.length) {
            flag = true;
            return;
        }

        for (int k = 0; k < 4; k++) {
            int x = i + dirs[k][0];
            int y = j + dirs[k][1];
            if(x >= 0 && x < board.length && y >=0 && y < board[0].length) {
                if(chars[index] == board[x][y] && !flags[x][y]) {
                    flags[x][y] = true;
                    dfs(board, x, y, chars, index + 1);
                    flags[x][y] = false;
                }
            }
        }
    }
    // TODO trie
}
