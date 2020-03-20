package com.leetcode.graph.tree.bt;

/**
 * @author wcl
 * @date 3:01 PM 2020/3/20
 * <a href="https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/">
 *     Recover a Tree From Preorder Traversal</a>
 */
public class RecoverATreeFromPreorderTraversal {
    /**
     * We run a preorder depth first search on the root of a binary tree.
     * At each node in this traversal, we output D dashes (where D is the depth of this node),
     * then we output the value of this node.
     * (If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node is 0.)
     * If a node has only one child, that child is guaranteed to be the left child.
     * Given the output S of this traversal, recover the tree and return its root.
     *
     * Example 1:
     *      Input: "1-2--3--4-5--6--7"
     *      Output: [1,2,5,3,4,6,7]
     *
     * Example 2:
     *      Input: "1-2--3---4-5--6---7"
     *      Output: [1,2,5,3,null,6,null,4,null,7]
     *
     * Example 3:
     *      Input: "1-401--349---90--88"
     *      Output: [1,401,null,349,88,90]
     *
     * Note:
     *      The number of nodes in the original tree is between 1 and 1000.
     *      Each node will have a value between 1 and 10^9.
     */
    public TreeNode recoverFromPreorder(String S) {
        if(S == null) {
            return null;
        }
        return recoverFromPreorder(new StringBuilder(S), 0);
    }

    public static TreeNode recoverFromPreorder(StringBuilder S, int depth) {
        if(S == null) {
            return null;
        }
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == '-') {
                count++;
            } else {
                break;
            }
        }
        if(count < depth) {
            return null;
        }
        int i = S.indexOf("-",count) ;
        i = i < 0 ? S.length() : i;
        TreeNode root = new TreeNode(Integer.parseInt(S.substring(count, i)));
        S.delete(0,i);
        root.left = recoverFromPreorder(S, depth + 1);
        root.right = recoverFromPreorder(S, depth + 1);
        return root;
    }

    public void preorder(TreeNode root, StringBuilder str, int depth) {
        if(root != null) {
            for (int i = 0; i < depth; i++) {
                str.append("-");
            }
            str.append(root.val);
            preorder(root.left, str, depth + 1);
            preorder(root.right, str, depth + 1);
        }
    }

    public static void main(String[] args) {
        String str = "1-2--3--4-5--6--7";
        System.out.println(recoverFromPreorder(new StringBuilder(str), 0));
        String str1 = "1-2--3---4-5--6---7";
        System.out.println(recoverFromPreorder(new StringBuilder(str1), 0));
        String str2 = "1-401--349---90--88";
        System.out.println(recoverFromPreorder(new StringBuilder(str2), 0));
    }

}
