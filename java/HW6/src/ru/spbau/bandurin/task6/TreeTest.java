package ru.spbau.bandurin.task6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;


public class TreeTest extends Assert {

    @Test
    public void testAVL1() {
        List<Integer> input = Arrays.asList(50, 18, 19, 1, 94, 2, 16, 4);
        List<Integer> output = Arrays.asList(19, 2, 1, 16, 4, 18, 50, 94);
        BinarySearchTree<Integer> tree = new AVLBinarySearchTree<Integer>();
        testTree(tree, input, output);
    }

    @Test
    public void testUNB1() {
        List<Integer> input = Arrays.asList(50, 18, 19, 1, 94, 2, 16, 4);
        List<Integer> output = Arrays.asList(50, 18, 1, 2, 16, 4, 19, 94);
        BinarySearchTree<Integer> tree = new UnbalancedBinarySearchTree<Integer>();
        testTree(tree, input, output);
    }

    @Test
    public void testAVL2() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> output = Arrays.asList(4, 2, 1, 3, 8, 6, 5, 7, 9, 10);
        BinarySearchTree<Integer> tree = new AVLBinarySearchTree<Integer>();
        testTree(tree, input, output);
    }

    @Test
    public void testUNB2() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> output = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        BinarySearchTree<Integer> tree = new UnbalancedBinarySearchTree<Integer>();
        testTree(tree, input, output);
    }
    
    @Test
    public void testAVL3() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Collections.reverse(input);
        List<Integer> output = Arrays.asList(7, 3, 2, 1, 5, 4, 6, 9, 8, 10);
        BinarySearchTree<Integer> tree = new AVLBinarySearchTree<Integer>();
        testTree(tree, input, output);
    }
    
    @Test
    public void testUNB3() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Collections.reverse(input);
        List<Integer> output = Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        BinarySearchTree<Integer> tree = new UnbalancedBinarySearchTree<Integer>();
        testTree(tree, input, output);
    }
    
    @Test(expected=ConcurrentModificationException.class)
    public void testConcurrentModification1() {
        BinarySearchTree<Integer> tree = new AVLBinarySearchTree<Integer>();
        tree.add(1);
        Iterator<Integer> it = tree.iterator();
        tree.add(2);
        it.hasNext();
    }

    @Test(expected=ConcurrentModificationException.class)
    public void testConcurrentModification2() {
        BinarySearchTree<Integer> tree = new AVLBinarySearchTree<Integer>();
        tree.add(1);
        Iterator<Integer> it = tree.treeIterator();
        tree.add(2);
        it.hasNext();
    }
    
    @Test(expected=ConcurrentModificationException.class)
    public void testConcurrentModification3() {
        BinarySearchTree<Integer> tree = new AVLBinarySearchTree<Integer>();
        tree.add(1);
        Iterator<Integer> it = tree.iterator();
        tree.add(2);
        it.next();
    }

    @Test(expected=ConcurrentModificationException.class)
    public void testConcurrentModification4() {
        BinarySearchTree<Integer> tree = new AVLBinarySearchTree<Integer>();
        tree.add(1);
        Iterator<Integer> it = tree.treeIterator();
        tree.add(2);
        it.next();
    }
    
    @Test
    public void testComparator() {
        BinarySearchTree<Integer> tree = new AVLBinarySearchTree<Integer>();
        assertNull(tree.comparator());
    }
    
    private static <T extends Comparable<T>> void testTree(BinarySearchTree<T> tree, List<T> input
            , List<T> tree_output) {
        List<T> sorted_input = new ArrayList<T>(input);
        Collections.sort(sorted_input);
        
        tree.addAll(input);
        
        List<T> output = new ArrayList<T>();
        for (T t: tree) {
            output.add(t);
        }
        
        assertTrue(sorted_input.equals(output));
        
        output.clear();
        Iterator<T> it = tree.treeIterator();
        while (it.hasNext()) {
            output.add(it.next());
        }
        
        assertTrue(tree_output.equals(output));
        
    }
    
    private static <T extends Comparable<T>> List<T> generateTestOutput(BinarySearchTree<T> tree, List<T> input) {
        tree.addAll(input);
        List<T> output = new ArrayList<T>();

        Iterator<T> it = tree.treeIterator();
        while (it.hasNext()) {
            output.add(it.next());
        }
        
        System.out.println(output);
        
        return output;
    }
    
    @Test
    public void testBasicBSTFunctions() {
        BinarySearchTree<Integer> tree = new AVLBinarySearchTree<Integer>();
        testBasicBSTFunctions(tree);
    }
    
    private void testBasicBSTFunctions(BinarySearchTree<Integer> tree) {
        tree.addAll(Arrays.asList(50, 18, 19, 1, 94, 2, 16, 4));

        assertEquals((int) tree.lower(4), 2);
        assertEquals((int) tree.floor(4), 4);

        assertEquals((int) tree.higher(50), 94);
        assertEquals((int) tree.ceiling(50), 50);
        
        assertNull(tree.higher(94));
        assertEquals((int) tree.ceiling(94), 94);

        assertEquals((int) tree.higher(19), 50);
        assertEquals((int) tree.ceiling(19), 19);
        assertEquals((int) tree.first(), 1);
        assertEquals((int) tree.last(), 94);

        assertFalse(tree.add(1));
    }

}
