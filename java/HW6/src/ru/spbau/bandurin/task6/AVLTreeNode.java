package ru.spbau.bandurin.task6;

/**
 * Base node for AVL Tree
 * @author Dmitriy Bandurin
 *         Date: 04.04.13
 */
public class AVLTreeNode<E extends Comparable<E>> extends TreeNode<E> {
    private final AVLBinarySearchTree<E> tree;

    /**
     * Create AVL tree node with link to tree
     * @param es AVL tree
     */
    public AVLTreeNode(AVLBinarySearchTree<E> es) {
        tree = es;
    }

    /**
     * Create new child AVl Node
     * @param tree link to AVL tree
     * @param eavlTreeNode parent Avl node
     * @param value node value
     */
    public AVLTreeNode(AVLBinarySearchTree<E> tree, AVLTreeNode<E> eavlTreeNode, E value) {
        super(eavlTreeNode, value);
        this.tree = tree;
    }

    /**
     * Get right child casted to AVLTreeNode
     * @return right child
     */
    @Override
    public AVLTreeNode<E> getRight() {
        return (AVLTreeNode<E>) super.getRight();
    }

    /**
     * Get Left child casted to AVLTreeNode
     * @return left child
     */
    @Override
    public AVLTreeNode<E> getLeft() {
        return (AVLTreeNode<E>) super.getLeft();
    }

    /**
     * Get parent node casted to AVLTreeNode
     * @return  parent AVLTreeNode
     */
    @Override
    public AVLTreeNode<E> getParent() {
        return (AVLTreeNode<E>) super.getParent();
    }

    /**
     * Create AVLTreeNode with current node as parent.
     * @param value value for created node
     * @return new AVLTreeNode with init value and parent
     */
    @Override
    protected AVLTreeNode<E> createTreeNode(E value) {
        return new AVLTreeNode<E>(tree, this, value);
    }

    /**
     * Add left child and balance tree
     * @param value value to added
     * @return true if child is added.
     */
    @Override
    protected boolean addLeft(E value) {
        boolean result = super.addLeft(value);
        balancing();
        return result;
    }

    /**
     * Add right child and balance tree
     * @param value value to added
     * @return true if child is added.
     */
    @Override
    protected boolean addRight(E value) {
        boolean result = super.addRight(value);
        balancing();
        return result;
    }

    /**
     * Start balance process from current node
     */
    public void balancing() {
        AVLTreeNode<E> node = this;
        int balance = node.calculateBalance();
        if (balance == -2) {
            AVLTreeNode leftLeft = node.getLeft().getLeft();
            AVLTreeNode rightRight = node.getLeft().getRight();
            if ( leftLeft != null && leftLeft.height() >= rightRight.height()) {
                node = smallRotateRight(node);
            } else {
                node = bigRotateRight(node);
            }
        } else if (balance == 2) {
            AVLTreeNode rightLeft = node.getRight().getLeft();
            AVLTreeNode rightRight = node.getRight().getRight();

            if (rightRight != null && rightRight.height() >= rightLeft.height()) {
                node = smallRotateLeft(node);
            } else {
                node = bigRotateLeft(node);
            }
        }

        if (node.getParent() != null) {
            (node.getParent()).balancing();
        } else {
            tree.setHead(node);
        }
    }

    /**
     * Left rotation using the given node.
     * @param node The node for the rotation.
     * @return The root of the rotated tree.
     */
    private AVLTreeNode<E> smallRotateLeft(AVLTreeNode<E> node) {
        AVLTreeNode<E> v = node.getRight();
        v.setParent(node.getParent());

        node.setRight(v.getLeft());

        if(node.getRight() != null) {
            node.getRight().setParent(node);
        }

        v.setLeft(node);
        node.setParent(v);

        if(v.getParent() != null) {
            if(v.getParent().getRight() == this) {
                v.getParent().setRight(v);
            } else if(v.getParent().getLeft() == node) {
                v.getParent().setLeft(v);
            }
        }

        node.calculateBalance();
        v.calculateBalance();

        return v;
    }

    /**
     * Right rotation using the given node.
     * @param node The node for the rotation
     * @return The root of the new rotated tree.
     */
    private AVLTreeNode<E> smallRotateRight(AVLTreeNode<E> node) {

        AVLTreeNode<E> v = node.getLeft();
        v.setParent(node.getParent());

        node.setLeft(v.getRight());

        if(node.getLeft() != null) {
            node.getLeft().setParent(node);
        }

        v.setRight(node);
        node.setParent(v);


        if(v.getParent() != null) {
            if(v.getParent().getRight() == node) {
                v.getParent().setRight(v);
            } else if(v.getParent().getLeft() == node) {
                v.getParent().setLeft(v);
            }
        }

        node.calculateBalance();
        v.calculateBalance();

        return v;
    }

    /**
     * Big right rotate using the given node
     * @param u The node for the rotation.
     * @return The root after the double rotation.
     */
    private AVLTreeNode<E> bigRotateRight(AVLTreeNode<E> u) {
        u.setLeft(smallRotateLeft(u.getLeft()));
        return smallRotateRight(u);
    }

    /**
     * Big left rotate using the given node
     * @param u The node for the rotation.
     * @return The root after the double rotation.
     */
    private AVLTreeNode<E> bigRotateLeft(AVLTreeNode<E> u) {
        u.setRight(smallRotateRight(u.getRight()));
        return smallRotateLeft(u);
    }

    /**
     * Calculate diff between right and left sub trees
     * @return right height - left height
     */
    private int calculateBalance() {
        AVLTreeNode right = getRight();
        AVLTreeNode left = getLeft();
        return (right != null ? right.height() : 0) - (left != null ? left.height() : 0);
    }

    /**
     * Calculate node height
     * @return node height
     */
    private int height() {
        AVLTreeNode left = getLeft();
        AVLTreeNode right = getRight();
        if (left == null && right == null) {
            return 0;
        } else if (left == null) {
            return 1 + right.height();
        } else if (right == null) {
            return 1 + left.height();
        } else {
            return 1 + Math.max(left.height(), right.height());
        }
    }
}