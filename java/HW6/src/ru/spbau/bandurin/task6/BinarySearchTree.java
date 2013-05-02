package ru.spbau.bandurin.task6;

import java.util.*;

/**
 * Abstract class represents Binary tree
 *
 * @author Dmitriy Bandurin
 *         Date: 30.03.13
 */
public abstract class BinarySearchTree<E extends Comparable<? super E>> extends AbstractSet<E> implements NavigableSet<E> {
    private TreeNode<E> head;

    /**
     * Should increase if tree structure is changed.
     * This field used by iterators to detect tree structure changes.
     */
    protected int version = 0;
    private int size = 0;

    /**
     * Get head element for current tree
     * @return head element tree node.
     */
    public TreeNode<E> getHead() {
        return head;
    }

    public void setHead(TreeNode<E> head) {
         this.head = head;
    }

    /**
     * Returns an iterator over the elements in this tree, in head -> left child -> right child order
     *
     * @return an iterator over the elements in this set
     */
    public Iterator<E> treeIterator() {
        return new Iterator<E>() {
            private final int versionWhenCreated;
            private Iterator<E> values;
            {
                ArrayList<E> tmp = new ArrayList<E>(size);
                collectValues(head, tmp);
                versionWhenCreated = version;
                values = tmp.iterator();
            }

            void collectValues(TreeNode<E> node, ArrayList<E> result){
                if(node != null){
                    result.add(node.getValue());
                    if(node.getLeft() != null){
                        collectValues(node.getLeft(), result);
                    }

                    if(node.getRight() != null){
                        collectValues(node.getRight(), result);
                    }
                }
            }

            /**
             * {@inheritDoc}
             * Returns true if the iteration has more elements.
             * (In other words, returns true if next would return an element rather than throwing an exception.)
             * @throws ConcurrentModificationException if tree is changed after iterator was created.
             */
            public boolean hasNext() {
                checkModification();
                return values.hasNext();
            }

            public E next() {
                checkModification();
                return values.next();
            }

            private void checkModification() {
                if (version != versionWhenCreated) {
                    throw new ConcurrentModificationException("Tree is Changed");
                }
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    /**
     * Returns the comparator used to order the elements in this set,
     * or <tt>null</tt> if this set uses the {@linkplain Comparable
     * natural ordering} of its elements.
     *
     * @return the comparator used to order the elements in this set,
     *         or <tt>null</tt> if this set uses the natural ordering
     *         of its elements
     */
    public Comparator<? super E> comparator() {
        return null;
    }

    /**
     * Returns an iterator over the elements contained in this collection.
     *
     * @return an iterator over the elements contained in this collection
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int versionWhenCreated;
            private Iterator<E> values;
            {
                ArrayList<E> tmp = new ArrayList<E>(size);
                collectValues(head, tmp);
                versionWhenCreated = version;
                values = tmp.iterator();
            }

            void collectValues(TreeNode<E> node, ArrayList<E> result){
                if(node != null){
                    if(node.getLeft() != null){
                        collectValues(node.getLeft(), result);
                    }
                    result.add(node.getValue());
                    if(node.getRight() != null){
                        collectValues(node.getRight(), result);
                    }
                }
            }

            /**
             * {@inheritDoc}
             * Returns true if the iteration has more elements.
             * (In other words, returns true if next would return an element rather than throwing an exception.)
             * @throws ConcurrentModificationException if tree is changed after iterator was created.
             */
            public boolean hasNext() {
                checkModification();
                return values.hasNext();
            }

            public E next() {
                checkModification();
                return values.next();
            }

            private void checkModification() {
                if (version != versionWhenCreated) {
                    throw new ConcurrentModificationException("Tree is Changed");
                }
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * {@inheritDoc}
     * <p/>
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     * @throws IllegalStateException         {@inheritDoc}
     */
    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("Added element can't be null");
        }
        boolean result;
        if (head == null) {
            head = createHeadTreeNode();
            head.setValue(e);
            result = true;
        } else {
            result = head.add(e);
        }
        if(result){
            ++version;
            ++size;
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Template method
     * Create Tree node for head element
     * @return new Tree node
     */
    protected abstract TreeNode<E> createHeadTreeNode();

    /**
     * Returns the greatest element in this set strictly less than the
     * given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the greatest element less than {@code e},
     *         or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    public E lower(E e) {
        if (e == null) {
            throw new NullPointerException("Test element can't be null");
        }
        E result = null;
        if(head != null){
            result = head.getLower(e);
        }
        return result;
    }

    /**
     * Returns the greatest element in this set less than or equal to
     * the given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the greatest element less than or equal to {@code e},
     *         or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    public E floor(E e) {
        if (e == null) {
            throw new NullPointerException("Test element can't be null");
        }
        E result = null;
        if(head != null){
            result = head.floor(e);
        }
        return result;
    }

    /**
     * Returns the least element in this set greater than or equal to
     * the given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the least element greater than or equal to {@code e},
     *         or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    public E ceiling(E e) {
        if (e == null) {
            throw new NullPointerException("Test element can't be null");
        }
        E result = null;
        if(head != null){
            result = head.ceiling(e);
        }
        return result;
    }

    /**
     * Returns the least element in this set strictly greater than the
     * given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the least element greater than {@code e},
     *         or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    public E higher(E e) {
        if (e == null) {
            throw new NullPointerException("Test element can't be null");
        }
        E result = null;
        if(head != null){
            result = head.higher(e);
        }
        return result;
    }

    /**
     * Returns the first (lowest) element currently in this set.
     *
     * @return the first (lowest) element currently in this set
     * @throws java.util.NoSuchElementException
     *          if this set is empty
     */
    public E first() {
        if (head == null || head.getValue() == null) {
            throw new NoSuchElementException();
        }

        return head.getMinElement().getValue();
    }

    /**
     * Returns the last (highest) element currently in this set.
     *
     * @return the last (highest) element currently in this set
     * @throws java.util.NoSuchElementException
     *          if this set is empty
     */
    public E last() {
        if (head == null || head.getValue() == null) {
            throw new NoSuchElementException();
        }
        return head.getMaxElement().getValue();
    }

    /**
     * Operation not supported
     *
     * @throws UnsupportedOperationException
     */
    public E pollFirst() {
        throw new UnsupportedOperationException();
    }

    /**
     * Operation not supported
     *
     * @throws UnsupportedOperationException
     */
    public E pollLast() {
        throw new UnsupportedOperationException();
    }

    /**
     * Operation not supported
     *
     * @throws UnsupportedOperationException
     */
    public SortedSet<E> tailSet(E fromElement) {
        throw new UnsupportedOperationException();
    }

    /**
     * Operation not supported
     *
     * @throws UnsupportedOperationException
     */
    public NavigableSet<E> descendingSet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Operation not supported
     *
     * @throws UnsupportedOperationException
     */
    public Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Operation not supported
     *
     * @throws UnsupportedOperationException
     */
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        throw new UnsupportedOperationException();
    }

    /**
     * Operation not supported
     *
     * @throws UnsupportedOperationException
     */
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        throw new UnsupportedOperationException();
    }

    /**
     * Operation not supported
     *
     * @throws UnsupportedOperationException
     */
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        throw new UnsupportedOperationException();
    }

    /**
     * Operation not supported
     *
     * @throws UnsupportedOperationException
     */
    public SortedSet<E> subSet(E fromElement, E toElement) {
        throw new UnsupportedOperationException();
    }

    /**
     * Operation not supported
     *
     * @throws UnsupportedOperationException
     */
    public SortedSet<E> headSet(E toElement) {
        throw new UnsupportedOperationException();
    }

    /**
     * Operation not supported
     *
     * @throws UnsupportedOperationException
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * Operation not supported
     *
     * @throws UnsupportedOperationException
     */
    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    /**
     * Operation not supported
     *
     * @throws UnsupportedOperationException
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * Operation not supported
     *
     * @throws UnsupportedOperationException
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}