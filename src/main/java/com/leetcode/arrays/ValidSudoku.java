package com.leetcode.arrays;

/**
 * <a href="https://leetcode.com/problems/valid-sudoku/">Valid Sudoku</a>
 *
 * @author zms
 * @date 11:52 上午 2021/11/16
 */
public class ValidSudoku {
    /**
     * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
     * Each row must contain the digits 1-9 without repetition.
     * Each column must contain the digits 1-9 without repetition.
     * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     * Note:
     * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
     * Only the filled cells need to be validated according to the mentioned rules.
     * <p>
     * Example 1:
     * <p>
     * Input: board =
     * [["5","3",".",".","7",".",".",".","."]
     * ,["6",".",".","1","9","5",".",".","."]
     * ,[".","9","8",".",".",".",".","6","."]
     * ,["8",".",".",".","6",".",".",".","3"]
     * ,["4",".",".","8",".","3",".",".","1"]
     * ,["7",".",".",".","2",".",".",".","6"]
     * ,[".","6",".",".",".",".","2","8","."]
     * ,[".",".",".","4","1","9",".",".","5"]
     * ,[".",".",".",".","8",".",".","7","9"]]
     * Output: true
     * <p>
     * Example 2:
     * Input: board =
     * [["8","3",".",".","7",".",".",".","."]
     * ,["6",".",".","1","9","5",".",".","."]
     * ,[".","9","8",".",".",".",".","6","."]
     * ,["8",".",".",".","6",".",".",".","3"]
     * ,["4",".",".","8",".","3",".",".","1"]
     * ,["7",".",".",".","2",".",".",".","6"]
     * ,[".","6",".",".",".",".","2","8","."]
     * ,[".",".",".","4","1","9",".",".","5"]
     * ,[".",".",".",".","8",".",".","7","9"]]
     * Output: false
     * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
     * <p>
     * <p>
     * Constraints:
     * board.length == 9
     * board[i].length == 9
     * board[i][j] is a digit 1-9 or '.'.
     */
    public static boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] subBoxes = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0' - 1;
                int n = (i / 3) * 3 + (j / 3);
                if (row[i][num] || col[j][num] || subBoxes[n][num]) {
                    return false;
                }
                row[i][num] = true;
                col[j][num] = true;
                subBoxes[n][num] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board =
                         {{'5', '3', '.', '.', '7', '.', '.', '.', '.' }
                        , {'6', '.', '.', '1', '9', '5', '.', '.', '.' }
                        , {'.', '9', '8', '.', '.', '.', '.', '6', '.' }
                        , {'8', '.', '.', '.', '6', '.', '.', '.', '3' }
                        , {'4', '.', '.', '8', '.', '3', '.', '.', '1' }
                        , {'7', '.', '.', '.', '2', '.', '.', '.', '6' }
                        , {'.', '6', '.', '.', '.', '.', '2', '8', '.' }
                        , {'.', '.', '.', '4', '1', '9', '.', '.', '5' }
                        , {'.', '.', '.', '.', '8', '.', '.', '7', '9' }};
        System.out.println(isValidSudoku(board));

    }
}
