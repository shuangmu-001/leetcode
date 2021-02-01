package com.data.structure.tree;

import java.util.Comparator;

/**
 * @author zms
 * @date 12:35 下午 2020/12/27
 */
public class BalanceBST<E> extends BinarySearchTree<E> {

    public BalanceBST() {
    }

    public BalanceBST(Comparator<E> comparator) {
        super(comparator);
    }

    protected void balance(Node<E> avlNode, Node<E> child, Node<E> son) {
        if (child.isLeftChild()) {
            if (son.isRightChild()) {
                rotateLeft(child);
            }
            rotateRight(avlNode);
        } else {
            if (son.isLeftChild()) {
                rotateRight(child);
            }
            rotateLeft(avlNode);
        }
    }

    // 右旋 LL(左左子树)
    private void rotateRight(Node<E> node) {
        rotate(node, true);
    }

    // 左旋 RR(右右子树)
    private void rotateLeft(Node<E> node) {
        rotate(node, false);
    }

    protected void rotate(Node<E> node, boolean direction) {
        if (node == null) {
            throw new IllegalArgumentException("旋转的节点不能为空");
        }

        if (node.isLeaf()) {
            throw new IllegalArgumentException("旋转的节点不能为叶子节点 : " + node);
        }

        Node<E> child;
        if (direction) {
            child = node.getLeft();
            node.left = child.right;
            if(child.right != null) {
                child.right.parent = node;
            }
            child.right = node;
        } else {
            child = node.getRight();
            node.right = child.left;
            if(child.left != null) {
                child.left.parent = node;
            }
            child.left = node;
        }

        Node<E> parent = node.getParent();
        if (parent == null) {
            this.root = child;
        } else if (node.isRightChild()) {
            parent.right = child;
        } else {
            parent.left = child;
        }
        node.parent = child;
        child.parent = parent;
    }

}
