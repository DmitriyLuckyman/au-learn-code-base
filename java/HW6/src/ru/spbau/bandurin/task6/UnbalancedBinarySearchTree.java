package ru.spbau.bandurin.task6;

/**
 * Simple without auto balancing binary tree
 * @author Dmitriy Bandurin
 *         Date: 30.03.13
 */
public class UnbalancedBinarySearchTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    /**
     * Template method
     * Create Tree node for head element
     *
     * @return new Tree node
     */
    @Override
    protected TreeNode<E> createHeadTreeNode() {
        return new TreeNode<E>();
    }
}