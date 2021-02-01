package com.data.structure.tree;

import java.util.Comparator;

/**
 * 平衡二叉树
 * 额外空间 height int类型
 * |平衡因子| <= 1 导致调整频繁
 *
 * @author zms
 * @date 6:28 下午 2020/12/23
 */
public class AVLTree<E> extends BalanceBST<E> {

    public AVLTree() {
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    public void fixAfterInsertion(Node<E> node) {
        AVLNode<E> avlNode = cast(node);
        while ((avlNode = avlNode.getParent()) != null) {
            if (isBalance(avlNode)) {
                updateHeight(avlNode);
            } else {
                AVLNode<E> child = avlNode.taller();
                balance(avlNode, child, child.taller());
                break;
            }
        }
    }

    @Override
    public void fixAfterDeletion(Node<E> node) {
        AVLNode<E> avlNode = cast(node);
        while ((avlNode = avlNode.getParent()) != null) {
            if (isBalance(avlNode)) {
                updateHeight(avlNode);
            } else {
                AVLNode<E> child = avlNode.taller();
                balance(avlNode, child, child.taller());
            }
        }
    }

    @Override
    protected void rotate(Node<E> node, boolean direction) {
        super.rotate(node, direction);
        AVLNode<E> avlNode = cast(node);
        avlNode.updateHeight();
        avlNode.getParent().updateHeight();
    }

    public boolean isBalance() {
        return isBalance(getRoot());
    }

    public int getHeight() {
        return this.root == null ? 0 : getRoot().height;
    }

    private void updateHeight(AVLNode<E> node) {
        node.updateHeight();
    }

    private static <E> AVLNode<E> cast(Node<E> node) {
        return (AVLNode<E>) node;
    }

    public static <E> boolean isBalance(AVLNode<E> node) {
        if (node == null) {
            return true;
        }

        return Math.abs(node.balanceFactor()) <= 1;
    }

    @Override
    public AVLNode<E> getRoot() {
        return cast(this.root);
    }

    @Override
    public AVLNode<E> node(E element) {
        return cast(super.node(element));
    }

    @Override
    protected AVLNode<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private static class AVLNode<E> extends Node<E> {

        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public AVLNode<E> taller() {
            if (height(getLeft()) > height(getRight())) {
                return getLeft();
            } else if (height(getLeft()) < height(getRight()) || isRightChild()) {
                return getRight();
            } else {
                return getLeft();
            }
        }

        public void updateHeight() {
            this.height = Math.max(height(getLeft()), height(getRight())) + 1;
        }

        public int balanceFactor() {
            return height(getLeft()) - height(getRight());
        }

        private int height(AVLNode<E> node) {
            return node == null ? 0 : node.height;
        }

        @Override
        public AVLNode<E> getLeft() {
            return cast(this.left);
        }

        @Override
        public AVLNode<E> getRight() {
            return cast(this.right);
        }

        @Override
        public AVLNode<E> getParent() {
            return cast(this.parent);
        }

        @Override
        public String toString() {
            return super.toString() + "_h(" + this.height + ")" + "_b(" + this.balanceFactor() + ")";
        }
    }
}
