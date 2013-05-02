package ru.spbau.bandurin.task6;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new AVLBinarySearchTree<Integer>(){
            @Override
            public boolean add(Integer integer) {
                System.out.println("addValue = " + integer);
                final boolean add = super.add(integer);
                widePrint(this);
                Main.treeIterator(this);
                System.out.println("\nEndAddValue = " + integer);
                return add;
            }
        };
        tree.addAll(Arrays.asList(50, 18, 19, 1, 94, 2, 16, 4));

        // обход в ширину
        widePrint(tree);

        //обход корень -> левое поддерево -> правое поддерево
        treeIterator(tree);

        System.out.println();
        System.out.println("tree.first() = " + tree.first());
        System.out.println("tree.last() = " + tree.last());

        System.out.println();
        System.out.println("tree.lower(8) = " + tree.lower(8));
        System.out.println("tree.lower(7) = " + tree.lower(7));
        System.out.println("tree.lower(2) = " + tree.lower(2));
        System.out.println("tree.lower(11) = " + tree.lower(11));

        System.out.println();
        System.out.println("tree.floor(8) = " + tree.floor(8));
        System.out.println("tree.floor(7) = " + tree.floor(7));
        System.out.println("tree.floor(2) = " + tree.floor(2));
        System.out.println("tree.floor(11) = " + tree.floor(11));

        System.out.println();
        System.out.println("tree.ceiling(8) = " + tree.ceiling(8));
        System.out.println("tree.ceiling(7) = " + tree.ceiling(7));
        System.out.println("tree.ceiling(2) = " + tree.ceiling(2));
        System.out.println("tree.ceiling(11) = " + tree.ceiling(11));

        System.out.println();
        System.out.println("tree.higher(8) = " + tree.higher(8));
        System.out.println("tree.higher(7) = " + tree.higher(7));
        System.out.println("tree.higher(2) = " + tree.higher(2));
        System.out.println("tree.higher(11) = " + tree.higher(11));
    }

    private static void treeIterator(BinarySearchTree<Integer> tree) {
        Iterator<Integer> integerIterator = tree.treeIterator();

        while(integerIterator.hasNext()){
            System.out.print(integerIterator.next() + " ");
        }
    }

    private static void widePrint(BinarySearchTree<Integer> tree) {
        LinkedList<TreeNode<Integer>> queue1 = new LinkedList<TreeNode<Integer>>();
        LinkedList<TreeNode<Integer>> queue2 = new LinkedList<TreeNode<Integer>>();
        queue1.offer(tree.getHead());
        while(!queue1.isEmpty()){
            while(!queue1.isEmpty()){
                TreeNode<Integer> poll = queue1.poll();
                if(poll != null){
                    queue2.offer(poll);
                    System.out.print(poll.getValue() + " ");
                }
            }
            System.out.println();
            while(!queue2.isEmpty()){
                TreeNode<Integer> poll = queue2.poll();
                if(poll != null){
                    queue1.add(poll.getLeft());
                    queue1.add(poll.getRight());
                }
            }
        }
    }
}
