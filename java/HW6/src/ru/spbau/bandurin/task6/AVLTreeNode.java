package ru.spbau.bandurin.task6;

/**
 * Base node for AVL Tree
 * @author Dmitriy Bandurin
 *         Date: 04.04.13
 */
public class AVLTreeNode<E extends Comparable<? super E>> extends TreeNode<E> {
    private final AVLBinarySearchTree<E> tree;
    private int height;

    /**
     * Create AVL tree node with link to tree
     * @param es AVL tree
     */
    public AVLTreeNode(AVLBinarySearchTree<E> es) {
        tree = es;
        height = 1;
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
        height = 1;
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
     * Add new element to this node branch
     *
     * @param value value to add.
     * @return true if this node changed as a result of the call
     */
    @Override
    public boolean add(E value) {
        final AVLTreeNode<E> insert = insert(value);
        if(insert != null && insert != tree.getHead()){
            tree.setHead(insert);
        }
        return insert != null;
    }

    /**
     * Add new element to this node branch
     *
     * @param value value to add.
     * @return true if this node changed as a result of the call
     */
     private AVLTreeNode<E> insert(E value) {
        int cmp = this.getValue().compareTo(value);
        AVLTreeNode<E> result;
        if(cmp == 0){
           result = null;
        } else if(cmp < 0){
            result = this.getRight() != null ? this.getRight().insert(value) : createTreeNode(value);
            if(result != null){
                this.setRight(result);
            }
        } else {
            result = this.getLeft() != null ? this.getLeft().insert(value) : createTreeNode(value);
            if(result != null){
                this.setLeft(result);
            }
        }
        return result == null ? result : balancing(this);
    }

    /**
     * Start balance process
     */
    public AVLTreeNode<E> balancing( AVLTreeNode<E> node) {
        node.fixHeight();
        final int balance = calculateBalance();
        if( balance == 2 )
        {
            if( node.getRight().calculateBalance() < 0 ){
                node.setRight(smallRotateRight(node.getRight()));
            }
            return smallRotateLeft(node);
        }
        if( balance == -2 )
        {
            if( node.getLeft().calculateBalance() > 0 ){
                node.setLeft(smallRotateLeft(node.getLeft()));
            }
            return smallRotateRight(node);
        }
        return node;
    }

    /**
     * Left rotation using the given node.
     * @param node The node for the rotation.
     * @return The root of the rotated tree.
     */
    private AVLTreeNode<E> smallRotateLeft(AVLTreeNode<E> node) {
        AVLTreeNode<E> p = node.getRight();
        node.setRight(p.getLeft());
        p.setLeft(node);
        node.fixHeight();
        p.fixHeight();
        return p;
    }

    /**
     * Right rotation using the given node.
     * @param node The node for the rotation
     * @return The root of the new rotated tree.
     */
    private AVLTreeNode<E> smallRotateRight(AVLTreeNode<E> node) {
        AVLTreeNode<E> q = node.getLeft();
        node.setLeft(q.getRight());
        q.setRight(node);
        node.fixHeight();
        q.fixHeight();
        return q;
    }

    /**
     * Calculate diff between right and left sub trees
     * @return right height - left height
     */
    private int calculateBalance() {
        return height(getRight()) - height(getLeft());
    }

    private int height(AVLTreeNode<E> node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(){
        height = Math.max(height(getRight()), height(getLeft())) + 1;
    }
}