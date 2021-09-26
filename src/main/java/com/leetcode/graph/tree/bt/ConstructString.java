package com.leetcode.graph.tree.bt;


/**
 * @author zms
 * @date 2:22 PM 2020/3/13
 * {@link "https://leetcode.com/problems/construct-string-from-binary-tree/"}
 */
public class ConstructString {
    /**
     * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.
     * The null node needs to be represented by empty parenthesis pair "()".
     * And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.
     *
     * Example 1:
     * Input: Binary tree: [1,2,3,4]
     *        1
     *      /   \
     *     2     3
     *    /
     *   4
     * Output: "1(2(4))(3)"
     * Explanation: Originallay it needs to be "1(2(4)())(3()())",
     *      but you need to omit all the unnecessary empty parenthesis pairs.
     *      And it will be "1(2(4))(3)".
     * Example 2:
     * Input: Binary tree: [1,2,3,null,4]
     *        1
     *      /   \
     *     2     3
     *      \
     *       4
     * Output: "1(2()(4))(3)"
     * Explanation: Almost the same as the first example,
     *      except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
     */
    public String tree2str(TreeNode t) {
        StringBuilder result = new StringBuilder();
        preOrder(t, result);
        return result.toString();
    }

    public static void preOrder(TreeNode root, StringBuilder builder) {
        if(root == null) {
            return;
        }
        builder.append(root.val);
        if(root.left != null) {
            builder.append("(");
            preOrder(root.left, builder);
            builder.append(")");
        } else if(root.right != null) {
            builder.append("()");
        }
        if(root.right != null) {
            builder.append("(");
            preOrder(root.right, builder);
            builder.append(")");
        }
    }
}
