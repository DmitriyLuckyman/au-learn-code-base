package ru.spbau.bandurin.task6;

/**
 * @author Dmitriy Bandurin
 *         Date: 30.03.13
 */
public class AVLBinarySearchTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    /**
     * Template method
     * Create Tree node for head element
     *
     * @return new AVLTreeNode node
     */
    @Override
    protected TreeNode<E> createHeadTreeNode() {
        return new AVLTreeNode<E>(this);
    }
}