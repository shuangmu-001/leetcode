package com.data.structure.tree;


import java.util.Comparator;

/**
 * 近似平衡二叉树 额外空间 bit
 * 高度差不超过两倍
 * <p>
 * 利用2-3-4树（BLACK节点和它的RED节点融合在一起形成一个B树节点）来实现，
 *
 * @author zms
 * @date 12:43 下午 2020/12/24
 */
public class RedBlackTree<E> extends BalanceBST<E> {

    private static final boolean RED = true;

    private static final boolean BLACK = false;

    public RedBlackTree() {
    }

    public RedBlackTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    public RBNode<E> getRoot() {
        return (RBNode<E>) super.getRoot();
    }

    @Override
    protected RBNode<E> createNode(E element, TreeNode<E> parent) {
        return new RBNode<>(element, parent);
    }

    @Override
    public void fixAfterInsertion(TreeNode<E> node) {
        RBNode<E> rbNode = cast(node);
        RBNode<E> parent = rbNode.getParent();
        // 上溢到根节点
        if (parent == null) {
            this.root = rbNode;
            black(rbNode);
            return;
        }
        // 父节点是黑色
        if (isBlack(parent)) {
            return;
        }
        RBNode<E> sibling = parent.sibling();
        RBNode<E> grand = parent.getParent();
        red(grand);
        if (isBlack(sibling)) {
            // 旋转
            balance(grand, parent, rbNode);
            black(grand.getParent());
        } else {
            // 染色 + 上溢
            black(parent);
            black(sibling);
            fixAfterInsertion(grand);
        }
    }

    @Override
    public void fixAfterDeletion(TreeNode<E> node) {
        RBNode<E> rbNode = cast(node);
        // 删除红色叶子节点或替换的节点是RED
        if (isRed(rbNode)) {
            black(rbNode);
            return;
        }
        // 叶子节点BLACK  下溢 （删除节点是度为0的节点）
        RBNode<E> parent = rbNode.getParent();
        if (parent == null) {
            return;
        }

        boolean isRight = parent.getRight() == null || rbNode.isRightChild();
        RBNode<E> sibling = isRight ? parent.getLeft() : parent.getRight();

        if (isRed(sibling)) {
            // sibling 必然有两个叶子节点
            black(sibling);
            rotate(red(parent), isRight);
            sibling = isRight ? parent.getLeft() : parent.getRight();
        }

        boolean parentColor = parent.color;
        if (isBlack(sibling.getLeft()) && isBlack(sibling.getRight())) {
            black(parent);
            red(sibling);
            if (!parentColor) {
                fixAfterDeletion(parent);
            }
        } else {
            RBNode<E> son = isRed(sibling.getLeft()) ? sibling.getLeft() : sibling.getRight();
            balance(black(parent), black(sibling), black(son));
            color(parent.getParent(), parentColor);
        }

    }

    private static <E> RBNode<E> red(RBNode<E> rbNode) {
        return color(rbNode, RED);
    }

    private static <E> RBNode<E> black(RBNode<E> rbNode) {
        return color(rbNode, BLACK);
    }

    private static <E> RBNode<E> color(RBNode<E> rbNode, boolean color) {
        if (rbNode != null) {
            rbNode.color = color;
        }
        return rbNode;
    }

    private static <E> boolean colorOf(RBNode<E> rbNode) {
        return rbNode == null ? BLACK : rbNode.color;
    }

    private static <E> boolean isRed(RBNode<E> rbNode) {
        return colorOf(rbNode) == RED;
    }

    private static <E> boolean isBlack(RBNode<E> rbNode) {
        return colorOf(rbNode) == BLACK;
    }

    private static <E> RBNode<E> cast(TreeNode<E> node) {
        return (RBNode<E>) node;
    }

    private static class RBNode<E> extends TreeNode<E> {

        private boolean color = RED;

        public RBNode(E element, TreeNode<E> parent) {
            super(element, parent);
        }

        @Override
        public RBNode<E> getLeft() {
            return cast(super.getLeft());
        }

        @Override
        public RBNode<E> getRight() {
            return cast(super.getRight());
        }

        @Override
        public RBNode<E> getParent() {
            return cast(super.getParent());
        }

        @Override
        public RBNode<E> sibling() {
            if (isLeftChild()) {
                return getParent().getRight();
            }

            if (isRightChild()) {
                return getParent().getLeft();
            }
            return null;
        }

        @Override
        public String toString() {
            String colorStr = color ? "RED" : "BLACK";
            return super.toString() + "_color(" + colorStr + ")";
        }
    }
}
