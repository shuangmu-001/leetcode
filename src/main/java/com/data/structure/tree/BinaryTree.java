package com.data.structure.tree;

import com.data.structure.tree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树(通用)
 */
@SuppressWarnings("unchecked")
public class BinaryTree<E> implements BinaryTreeInfo {

    public BinaryTree() {
    }

    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    protected int size;

    protected Node<E> root;

    public static abstract class Visitor<E> {

        boolean stop;

        // 如果返回true，就代表停止遍历
        public abstract boolean visit(E element);
    }

    public Node<E> getRoot() {
        return root;
    }

    /**
     * 内部类，节点类
     */
    public static class Node<E> {
        public E element;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() { // 是否叶子节点
            return left == null && right == null;
        }

        public boolean hasTwoChildren() { // 是否有两个子节点
            return left != null && right != null;
        }

        public boolean isLeftChild() { // 判断自己是不是左子树
            return parent != null && this == parent.left;
        }

        // 判断自己是不是右子树
        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        /*
         * 返回兄弟节点
         */
        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public Node<E> getParent() {
            return parent;
        }

        @Override
        public String toString() {
            String parentStr = "null";
            if (this.parent != null) {
                parentStr = this.parent.element.toString();
            }
            return this.element + "_parent(" + parentStr + ")";
        }
    }

    /**
     * 元素的数量
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空所有的元素
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 前序遍历
     */
    public void preorder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        preorder(root, visitor);
    }

    public static <E> void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    /**
     * 中序遍历
     */
    public void inorder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        inorder(root, visitor);
    }

    public static <E> void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        inorder(node.left, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    /**
     * 后序遍历
     */
    public void postorder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        postorder(root, visitor);
    }

    public static <E> void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        postorder(node.left, visitor);
        postorder(node.right, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 层次遍历
     */
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor.stop) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)) {
                return;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 求树的高度(递归)
     */
    public int height1() {
        return height1(root);
    }

    public static <E> int height1(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height1(node.left), height1(node.right));
    }

    /**
     * 求树的高度高度(迭代)
     */
    public int height() {
        if (root == null) {
            return 0;
        }
        // 存储每一层的元素数量
        int levelSize = 1;
        // 树的高度
        int height = 0;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            // 即将要访问下一层
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * 是否是完全二叉树
     * 从上到下，从左到右，中间没有间隙，
     * 1、叶子节点之后的所有节点都是叶子节点
     * 2、不存在只有右子节点的节点（要么两个子节点，要么只有左子节点（只存在一个））
     */
    public boolean isComplete() {
        if (root == null) {
            return false;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        // leaf代表是否要求后面都是叶子节点
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            // 要求是叶子结点，但是当前节点不是叶子结点
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
                if (node.right != null) {
                    queue.offer(node.right);
                }
            } else if (node.right != null) {
                return false;
            } else {
                // 要求后面都是叶子节点
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 前驱节点: 中序遍历时的前一个节点
     * 1、左子树中最右的节点 => node.left.right.right...（子节点）
     * 2、当前节点是某节点右子树的最左边（祖先节点）
     */
    public static <E> Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return null;
        }

        Node<E> preNode = node.left;
        if (preNode != null) {
            while (preNode.right != null) {
                preNode = preNode.right;
            }
            return preNode;
        }

        while (node.parent != null && node.parent.left == node) {
            node = node.parent;
        }

        return node.parent;
    }

    /**
     * 后继节点: 中序遍历时的后一个节点
     * 1、右子树中最左的节点 => node.right.left.left...（子节点）
     * 2、当前节点是某节点的左子树的最右边（祖先节点）
     */
    public static <E> Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            Node<E> p = node.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        while (node.parent != null && node.parent.right == node) {
            node = node.parent;
        }
        return node.parent;
    }

    // 默认返回一个通用节点
    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    /**
     * BinaryTreeInfo 工具，用来打印二叉树
     */
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return node;
    }
}