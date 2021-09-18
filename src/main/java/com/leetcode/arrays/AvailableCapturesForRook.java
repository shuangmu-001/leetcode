package com.leetcode.arrays;

/**
 * @author wcl
 * @date 1:04 PM 2020/3/26
 * <a href="https://leetcode.com/problems/available-captures-for-rook/">
 *     Available Captures for Rook</a>
 */
public class AvailableCapturesForRook {
    /**
     * On an 8 x 8 chessboard, there is one white rook. 
     * There also may be empty squares, white bishops, and black pawns. 
     * These are given as characters 'R', '.', 'B', and 'p' respectively.
     * Uppercase characters represent white pieces, and lowercase characters represent black pieces.
     * The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south),
     * then moves in that direction until it chooses to stop, reaches the edge of the board,
     * or captures an opposite colored pawn by moving to the same square it occupies. 
     * Also, rooks cannot move into the same square as other friendly bishops.
     *
     * Return the number of pawns the rook can capture in one move.
     *
     * Example 1:
     *
     * Input: [[".",".",".",".",".",".",".","."],
     *         [".",".",".","p",".",".",".","."],
     *         [".",".",".","R",".",".",".","p"],
     *         [".",".",".",".",".",".",".","."],
     *         [".",".",".",".",".",".",".","."],
     *         [".",".",".","p",".",".",".","."],
     *         [".",".",".",".",".",".",".","."],
     *         [".",".",".",".",".",".",".","."]]
     * Output: 3
     * Explanation:
     * In this example the rook is able to capture all the pawns.
     *
     * Example 2:
     * Input: [[".",".",".",".",".",".",".","."],
     *         [".","p","p","p","p","p",".","."],
     *         [".","p","p","B","p","p",".","."],
     *         [".","p","B","R","B","p",".","."],
     *         [".","p","p","B","p","p",".","."],
     *         [".","p","p","p","p","p",".","."],
     *         [".",".",".",".",".",".",".","."],
     *         [".",".",".",".",".",".",".","."]]
     * Output: 0
     * Explanation:
     * Bishops are blocking the rook to capture any pawn.
     *
     * Example 3:
     * Input: [[".",".",".",".",".",".",".","."],
     *         [".",".",".","p",".",".",".","."],
     *         [".",".",".","p",".",".",".","."],
     *         ["p","p",".","R",".","p","B","."],
     *         [".",".",".",".",".",".",".","."],
     *         [".",".",".","B",".",".",".","."],
     *         [".",".",".","p",".",".",".","."],
     *         [".",".",".",".",".",".",".","."]]
     * Output: 3
     * Explanation:
     * The rook can capture the pawns at positions b5, d6 and f5.
     *  
     *
     * Note:
     *      board.length == board[i].length == 8
     *      board[i][j] is either 'R', '.', 'B', or 'p'
     *      There is exactly one cell with board[i][j] == 'R'
     *
     */
    public static int numRookCaptures(char[][] board) {
        int x = 0;
        int y = 0;

        l:for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] == 'R') {
                    x = i;
                    y = j;
                    break l;
                }
            }
        }
        int num = 0;
        for (int i = 0; i < 8; i++) {
            if(board[i][y] == 'B') {
                if(i > x) {
                    break;
                }
                num = 0;
            }
            if(board[i][y] == 'p') {
                if(i > x) {
                    num++;
                    break;
                }
                num = 1;
            }
        }
        int num2 = 0;
        for (int i = 0; i < 8; i++) {
            if(board[x][i] == 'B') {
                if(i > y) {
                    break;
                }
                num2 = 0;
            }
            if(board[x][i] == 'p') {
                if(i > y) {
                    num2++;
                    break;
                }
                num2 = 1;
            }
        }
        return num + num2;
    }

    public static void main(String[] args) {
        System.out.println(numRookCaptures(new char[][]{
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','p','.','.','.','.'},
                {'.','.','.','B','.','.','.','.'},
                {'p','B','.','R','.','p','B','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','B','.','.','.','.'},
                {'.','.','.','p','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'}}));
        System.out.println(numRookCaptures(new char[][]{
                {'.','.','.','.','.','.','.','.'},
                {'p','.','.','p','.','.','.','p'},
                {'p','p','.','.','.','.','p','p'},
                {'p','p','p','.','R','p','p','p'},
                {'p','p','.','.','.','.','p','p'},
                {'p','.','.','p','.','.','.','p'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'}}));
        System.out.println(numRookCaptures(new char[][]{
                {'.','.','.','.','.','.','.','.'},
                {'.','.','p','p','.','p','p','.'},
                {'.','p','.','.','.','.','.','p'},
                {'.','.','.','.','R','.','.','.'},
                {'.','p','.','.','.','.','.','p'},
                {'.','.','p','p','.','p','p','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'}}));
    }


}
