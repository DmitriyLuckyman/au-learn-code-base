package ru.spbau.bandurin.task6;

/**
 * Base tree node for binary tree
 * @author Dmitriy Bandurin
 *         Date: 03.04.13
 */
public class TreeNode<T extends Comparable<T>> {
    private TreeNode<T> parent;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T value;

    /**
     * Create new TreeNode for tree
     */
    public TreeNode() {
    }

    /**
     * Create new TreeNode for tree
     * @param parent parent of current node
     * @param value node value
     */
    TreeNode(TreeNode<T> parent, T value) {
        this.parent = parent;
        this.value = value;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Get min element in branch
     * @return min element
     */
    public TreeNode<T> getMinElement(){
        TreeNode<T> t = this;
        while(t.left != null){
            t = t.left;
        }
        return t;
    }

    /**
     * Return max element in current branch
     * @return max element
     */
    public TreeNode<T> getMaxElement(){
        TreeNode<T> t = this;
        while(t.right != null){
            t = t.right;
        }
        return t;
    }

    /**
     * Returns the greatest element in this node branch strictly less than the
     * given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the greatest element less than {@code e},
     *         or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in this node branch
     */
    public T getLower(T e){
        int cmp = value.compareTo(e);
        T result;
        if(cmp >= 0){
            if(left == null){
                result = null;
            } else {
                result = left.getLower(e);
            }
        } else {
            if(right == null){
                result = value;
            } else {
                T lower = right.getLower(e);
                if(lower != null){
                    result = value.compareTo(lower) > 0 ? value : lower;
                } else {
                    result = value;
                }
            }
        }
        return result;
    }

    /**
     * Returns the greatest element in this branch less than or equal to
     * the given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the greatest element less than or equal to {@code e},
     *         or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the branch
     * @throws NullPointerException if the specified element is null
     *                              and this branch does not permit null elements
     */
    public T floor(T e) {
        int cmp = value.compareTo(e);
        T result;
        if(cmp > 0){
            if(left == null){
                result = null;
            } else {
                result = left.floor(e);
            }
        } else if(cmp < 0) {
            if(right == null){
                result = value;
            } else {
                T floor = right.floor(e);
                if(floor != null){
                    result = value.compareTo(floor) >= 0 ? value : floor;
                } else {
                    result = value;
                }
            }
        } else {
            result = value;
        }
        return result;
    }

    /**
     * Returns the least element in this branch greater than or equal to
     * the given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the least element greater than or equal to {@code e},
     *         or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the branch
     * @throws NullPointerException if the specified element is null
     *                              and this branch does not permit null elements
     */
    public T ceiling(T e) {
        int cmp = value.compareTo(e);
        T result;
        if(cmp < 0){
            if(right == null){
                result = null;
            } else {
                result = right.ceiling(e);
            }
        } else if(cmp > 0) {
            if(left == null){
                result = value;
            } else {
                T ceiling = left.ceiling(e);
                if(ceiling != null){
                    result = value.compareTo(ceiling) <= 0 ? value : ceiling;
                } else {
                    result = value;
                }
            }
        } else {
            result = value;
        }
        return result;
    }

    /**
     * Returns the least element in this tree branch strictly greater than the
     * given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the least element greater than {@code e},
     *         or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the branch
     * @throws NullPointerException if the specified element is null
     *                              and this branch does not permit null elements
     */
    public T higher(T e) {
        int cmp = value.compareTo(e);
        T result;
        if(cmp <= 0){
            if(right == null){
                result = null;
            } else {
                result = right.higher(e);
            }
        } else {
            if(left == null){
                result = value;
            } else {
                T higher = left.higher(e);
                if(higher != null){
                    result = value.compareTo(higher) < 0 ? value : higher;
                } else {
                    result = value;
                }
            }
        }
        return result;
    }

    /**
     * Add new element to this node branch
     * @param value value to add.
     * @return true if this node changed as a result of the call
     */
    public boolean add(T value){
        int cmp = this.value.compareTo(value);
        boolean result;
        if(cmp == 0){
            result = false;
        } else if(cmp < 0){
            if(this.right != null){
                result = this.right.add(value);
            } else {
                result = addRight(value);
            }
        } else {
            if(this.left != null){
                result = this.left.add(value);
            } else {
                result = addLeft(value);
            }
        }
        return result;
    }

    /**
     * Add left child to current node.
     * Override this method for added special processing.
     * @param value value for left child
     * @return true if child is added
     */
    protected boolean addLeft(T value) {
        this.left = createTreeNode(value);
        return true;
    }

    /**
     * Add right child to current node.
     * Override this method for added special processing.
     * @param value value for right child
     * @return true if child is added
     */
    protected boolean addRight(T value) {
        this.right = createTreeNode(value);
        return true;
    }

    /**
     * method for create new node for child node
     * @param value value for created node
     * @return new node with set current as parent and value
     */
    protected TreeNode<T> createTreeNode(T value) {
        return new TreeNode<T>(this, value);
    }
}