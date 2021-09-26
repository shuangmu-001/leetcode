package com.leetcode.graph.tree.bt;

/**
 * @author zms
 * @date 8:32 PM 2020/3/19
 * <a href="https://leetcode.com/problems/binary-tree-coloring-game/">
 * Binary Tree Coloring Game</a>
 */
public class BTColoringGame {
    /**
     * Two players play a turn based game on a binary tree.
     * We are given the root of this binary tree, and the number of nodes n in the tree.
     * n is odd, and each node has a distinct value from 1 to n.
     * Initially, the first player names a value x with 1 <= x <= n,
     * and the second player names a value y with 1 <= y <= n and y != x.
     * The first player colors the node with value x red, and the second player colors the node with value y blue.
     * Then, the players take turns starting with the first player.
     * In each turn, that player chooses a node of their color (red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)
     * If (and only if) a player cannot choose such a node in this way, they must pass their turn.
     * If both players pass their turn, the game ends, and the winner is the player that colored more nodes.
     * You are the second player.
     * If it is possible to choose such a y to ensure you win the game, return true.
     * If it is not possible, return false.
     * <p>
     * Example 1:
     * Input: root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
     * Output: true
     * Explanation: The second player can choose the node with value 2.
     * <p>
     * Constraints:
     * root is the root of a binary tree with n nodes and distinct node values from 1 to n.
     * n is odd.
     * 1 <= x <= n <= 100
     */
    private TreeNode targetNode;
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        getTargetNode(root, x);
        int left = getCount(targetNode.left);
        int right = getCount(targetNode.right);
        int xCont1 = Math.min(left, right) + 1;
        int xCount2 = left + right + 1;
        if(n - xCount2 > xCount2) {
            return true;
        }
        return n - xCount2 + xCont1 < Math.max(left, right);

    }

    public void getTargetNode(TreeNode root, int x) {
        if (root == null || targetNode != null) {
            return;
        }
        if (root.val == x) {
            targetNode = root;
        }
        getTargetNode(root.left, x);
        getTargetNode(root.right, x);
    }

    public int getCount(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = getCount(root.left);
        int right = getCount(root.right);
        return left + right + 1;
    }
}
