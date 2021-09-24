package com.data.structure.tree;

import java.util.Comparator;

/**
 * @author zms
 * @date 2:34 下午 2020/12/23
 */
@SuppressWarnings("unchecked")
public class BinarySearchTree<E> extends BinaryTree<E> {

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    protected Comparator<E> comparator;

    public boolean contains(E element) {
        return node(element) != null;
    }

    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            root = createNode(element, null);
            size++;
            fixAfterInsertion(root);
            return;
        }
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        int res = 0;
        while (current != null) {
            res = compareTo(current.element, element);
            parent = current;
            if (res > 0) {
                current = current.left;
            } else if (res < 0) {
                current = current.right;
            } else {
                current.element = element;
                return;
            }
        }
        TreeNode<E> node = createNode(element, parent);
        if (res > 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        size++;
        fixAfterInsertion(node);
    }


    public void fixAfterInsertion(TreeNode<E> node) {

    }

    /**
     * 根据传入的值删除元素
     */
    public void remove(E element) {
        remove(node(element));
    }

    // 根据节点删除元素
    private void remove(TreeNode<E> node) {
        if (node == null) {
            return;
        }

        size--;
        // 度为2的节点
        if (node.hasTwoChildren()) {
            // 找到后继节点
            TreeNode<E> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        TreeNode<E> replacement = node.left != null ? node.left : node.right;
        // node是度为1的节点
        if (replacement != null) {
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            // node是度为1的节点并且是根节点
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }
            fixAfterDeletion(replacement);
        } else {
            // node是度为0的节点
            if (node.parent == null) {
                // node是叶子节点并且是根节点
                this.root = null;
            } else {
                // node是叶子节点，但不是根节点
                // 判断node节点是父节点的左子节点还是右子节点
                if (node == node.parent.left) {
                    node.parent.left = null;
                } else {
                    node.parent.right = null;
                }
            }
            fixAfterDeletion(node);
        }
    }

    public void fixAfterDeletion(TreeNode<E> node) {

    }

    protected int compareTo(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        Comparable<E> comparable = (Comparable<E>) e1;
        return comparable.compareTo(e2);
    }

    // 根据元素值获取节点元素
    public TreeNode<E> node(E element) {
        elementNotNullCheck(element);

        TreeNode<E> node = root;
        while (node != null) {
            int cmp = compareTo(element, node.element);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    // 检测传入的节点是否为空
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not null");
        }
    }
}
