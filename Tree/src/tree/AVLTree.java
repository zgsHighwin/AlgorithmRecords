package tree;

import java.util.Comparator;

/**
 * url:
 * Author:Savannah
 * Description:
 * AlgorithmLearningProject 11/24/20
 */
public class AVLTree<E> extends BST<E>{
    public AVLTree(){
        this(null);
    }


    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }
}
