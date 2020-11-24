package tree;

import printer.BinaryTrees;

import java.util.Comparator;
import java.util.Random;

/**
 * url:
 * Author:Savannah
 * Description:
 * AlgorithmLearningProject 6/7/20
 */
public class Main {

    static class Person implements Comparable<Person> {
        int age;

        public Person(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return age + "";
        }

        @Override
        public int compareTo(Person o) {
            return this.age - o.age;
        }
    }


    public static void main(String[] args) {
        printTree();
        preOrderTraversalTest();
    }

    private static void printTree() {
        BinarySearchTree<Person> bst1 = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
//                if (o1.age > o2.age) return 1;
//                } else if (o1.age < o2.age)  return -1;
//                } else  return 0;
//
                return o1.age - o2.age;
            }
        });

//        BinarySearchTree<Person> bst2 = new BinarySearchTree<>();
//        bst2.add(new Person(1));
//        bst2.add(new Person(2));
//        bst2.add(new Person(4));
//        bst2.add(new Person(3));
//


//        BinaryTrees.println(bst1);
//
    }

    private static void preOrderTraversalTest() {
        BinarySearchTree<Integer> searchTree = new BinarySearchTree<>();
        searchTree.add(7);
        searchTree.add(4);
        searchTree.add(9);
        searchTree.add(2);
        searchTree.add(5);
        searchTree.add(1);
        searchTree.add(3);
        searchTree.add(8);
        searchTree.add(11);
        searchTree.add(10);
        searchTree.add(12);
        BinaryTrees.println(searchTree);
        searchTree.remove(11);
        BinaryTrees.println(searchTree);
        searchTree.remove(7);
        BinaryTrees.println(searchTree);
//        searchTree.preOrderTraversal();
//        searchTree.postOrderTraversal();
//        searchTree.levelOrderTraversal();
//        System.out.println(searchTree.getTreeSize());
    }
}
