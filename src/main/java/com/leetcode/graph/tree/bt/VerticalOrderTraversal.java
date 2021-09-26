package com.leetcode.graph.tree.bt;

import java.util.*;

/**
 * @author zms
 * @date 10:31 AM 2020/3/20
 * <a href="https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/">
 *     Vertical Order Traversal of a Binary Tree</a>
 */
public class VerticalOrderTraversal {
    /**
     * Given a binary tree, return the vertical order traversal of its nodes values.
     * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
     * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes,
     * we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
     * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
     * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
     *
     * Example 1:
     *      Input: [3,9,20,null,null,15,7]
     *      Output: [[9],[3,15],[20],[7]]
     *      Explanation:
     *          Without loss of generality, we can assume the root node is at position (0, 0):
     *          Then, the node with value 9 occurs at position (-1, -1);
     *          The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
     *          The node with value 20 occurs at position (1, -1);
     *          The node with value 7 occurs at position (2, -2).
     *
     * Example 2:
     *      Input: [1,2,3,4,5,6,7]
     *      Output: [[4],[2],[1,5,6],[3],[7]]
     *      Explanation:
     *          The node with value 5 and the node with value 6 have the same position according to the given scheme.
     *          However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
     *
     * Note:
     *      The tree will have between 1 and 1000 nodes.
     *      Each node's value will be between 0 and 1000.
     */
    private static Map<Integer, Map<Integer, Set<Integer>>> nodeMap = new TreeMap<>();
    public static List<List<Integer>> myVerticalTraversal(TreeNode root) {
        LinkedList<List<Integer>> nodes =  new LinkedList<>();
        verticalTraversal(root, 0, 0);
        Set<Map.Entry<Integer, Map<Integer, Set<Integer>>>> entries = nodeMap.entrySet();
        for(Map.Entry<Integer, Map<Integer, Set<Integer>>> map : entries) {
            Set<Map.Entry<Integer, Set<Integer>>> entries1 = map.getValue().entrySet();
            List<Integer> list = new ArrayList<>();
            for (Map.Entry<Integer, Set<Integer>> map1 : entries1) {
                list.addAll(map1.getValue());
            }
            nodes.add(list);
        }
        return nodes;
    }

    public static void verticalTraversal(TreeNode root, int x, int y) {
        if(root == null) {
            return;
        }
        if(!nodeMap.containsKey(x)) {
            nodeMap.put(x, new TreeMap<>());
        }
        Map<Integer, Set<Integer>> map = nodeMap.get(x);
        if(!map.containsKey(y)) {
            map.put(y, new TreeSet<>());
        }
        Set<Integer> integers = map.get(y);
        integers.add(root.val);
        verticalTraversal(root.left, x - 1, y + 1);
        verticalTraversal(root.right, x + 1, y + 1);
    }

    public static void main(String[] args) {
        TreeNode root01 = new TreeNode(1);
        TreeNode left11 = new TreeNode(0);
        TreeNode right11 = new TreeNode(2);
        root01.left = left11;
        root01.right = right11;
        System.out.println(myVerticalTraversal(root01));

        TreeNode root02 = new TreeNode(3);
        TreeNode left21 = new TreeNode(0);
        TreeNode right21 = new TreeNode(4);
        root02.left = left21;
        root02.right = right21;
        TreeNode left23 = new TreeNode(1);
        TreeNode right22 = new TreeNode(2);
        left21.right = right22;
        right22.left = left23;
        System.out.println(myVerticalTraversal(root02));
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> nodes =  new LinkedList<>();
        List<Location> locations = new ArrayList<>();
        traversal(root, 0, 0, locations);
        Collections.sort(locations);
        int prev = 0;
        for (int i = 0; i < locations.size(); i++) {
            if(i == 0) {
                prev = locations.get(0).val;
                nodes.add(new ArrayList<>());
            }
            if(prev != locations.get(0).val) {
                nodes.add(new ArrayList<>());
            }
            nodes.get(nodes.size() - 1).add(locations.get(i).val);
            prev = locations.get(i).x;
        }
        return nodes;
    }

    public void traversal(TreeNode root, int x, int y, List<Location> locations) {
       if(root == null) {
           return;
       }
       locations.add(new Location(x, y, root.val));
       traversal(root.left, x - 1, y + 1, locations);
       traversal(root.right, x + 1, y + 1, locations);
    }


    static class Location implements Comparable<Location>{
        private int x;
        private int y;
        private int val;

        public Location(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }


        @Override
        public int compareTo(Location o) {
            if(x != o.x) {
                return Integer.compare(x,o.x);
            } else if(y != o.y) {
                return Integer.compare(y,o.y);
            } else {
                return Integer.compare(val,o.val);
            }
        }
    }

    private int leftLen = 0;
    public List<List<Integer>> verticalTraversal3(TreeNode root) {
        List<List<Integer>> nodes = new LinkedList<>();
        verticalTraversal(root, 0, 0, nodes);
        for (List<Integer> list : nodes) {
            Collections.sort(list);
            for (int j = 0; j < list.size(); j++) {
                list.set(j, list.get(j) % 1000);
            }
        }
        return nodes;
    }

    public void verticalTraversal(TreeNode root, int x, int y, List<List<Integer>> nodeList) {
        if(root == null) {
            return;
        }

        if(nodeList.isEmpty()) {
            nodeList.add(new ArrayList<>());
        }

        if(x < 0 && x + leftLen < 0) {
            leftLen = Math.max(leftLen, Math.abs(x));
            nodeList.add(0, new ArrayList<>());
        }

        if(x > 0 && x + leftLen >= nodeList.size()) {
            nodeList.add(new ArrayList<>());
        }

        List<Integer> nodes = nodeList.get(leftLen + x);
        nodes.add(1000 * y + root.val);

        verticalTraversal(root.left, x - 1,y+1 ,nodeList);
        verticalTraversal(root.right, x + 1,y+1, nodeList);

    }
}
