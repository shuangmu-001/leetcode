package com.question.day03;

import com.leetcode.graph.tree.bt.TreeNode;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/">
 * All Nodes Distance K in Binary Tree</a>
 *
 * @author zms
 * @date 9:09 下午 2021/9/18
 */
public class Code09DistanceKNodes {

    // 给定三个参数：二叉树的头节点head，树上某个节点target，正数K，从target开始，可以向上或者向下走，返回与target的距离是K的所有节点
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<>();
        if (root == null || target == null) {
            return ans;
        }
        if (K == 0) {
            ans.add(target.val);
            return ans;
        }
        // 1、构建父子信息
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        createParentMap(root, parents);
        // 2、计算每棵树上距离目标固定距离的节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        int curLevel = 0;
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    continue;
                }
                if (K == curLevel) {
                    ans.add(poll.val);
                    continue;
                }
                if (poll.left != null && !visited.contains(poll.left)) {
                    queue.offer(poll.left);
                    visited.add(poll.left);
                }
                if (poll.right != null && !visited.contains(poll.right)) {
                    queue.offer(poll.right);
                    visited.add(poll.right);
                }
                if (parents.containsKey(poll) && !visited.contains(parents.get(poll))) {
                    queue.offer(parents.get(poll));
                    visited.add(parents.get(poll));
                }
            }
            curLevel++;
            if (K < curLevel) {
                break;
            }
        }
        return ans;
    }

    public void createParentMap(TreeNode root, Map<TreeNode, TreeNode> parents) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            parents.put(root.left, root);
            createParentMap(root.left, parents);
        }
        if (root.right != null) {
            parents.put(root.right, root);
            createParentMap(root.right, parents);
        }
    }

    // TODO 找到目标节点的所有祖先节点
    // TODO 每个节点到目标节点的距离
    // 遍历到目标target的时候，它的祖先节点都被压在栈里
    // 可以通过返回值知道target是否是当前节点的子孙节点
    public int dfs(TreeNode root, TreeNode target, List<Integer> ans, int K) {
        if (root == null) {
            return -1;
        }
        if (root == target) {
            // 收集target子树距离自己为k的节点
            subTreeAdd(root, ans, 0, K);
            return 1;
        }
        // target是否在root的左子树上
        int l = dfs(root.left, target, ans, K);
        // target是否在root的右子树上
        int r = dfs(root.right, target, ans, K);
        if (l != -1) {
            // 说明target在当前节点的左子树上
            if (l == K) {
                ans.add(root.val);
            } else {
                // 收集当前节点的右子树上距离target为k的节点
                subTreeAdd(root.right, ans, l + 1, K);
            }
            return l + 1;
        }
        if (r != -1) {
            // 说明target在当前节点的右子树上
            if (r == K) {
                ans.add(root.val);
            } else {
                // 收集当前节点的左子树上距离target为k的节点
                subTreeAdd(root.left, ans, r + 1, K);
            }
            return r + 1;
        }
        return -1;
    }

    public void subTreeAdd(TreeNode node, List<Integer> ans, int dist, int K) {
        if (node == null) {
            return;
        }
        if (dist == K) {
            ans.add(node.val);
        } else {
            subTreeAdd(node.left, ans, dist + 1, K);
            subTreeAdd(node.right, ans, dist + 1, K);
        }

    }
}
