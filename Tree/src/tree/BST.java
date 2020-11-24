package tree;

import java.util.Comparator;

/**
 * url:
 * Author:Savannah
 * Description:
 * AlgorithmLearningProject 6/7/20
 */
@SuppressWarnings("unchecked")
public class BST<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }


    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            root = new Node<E>(element, null);
            size++;
        } else {
            Node<E> node = root;
            Node<E> parent = root;
            int cmp = 0;
            while (node != null) {
                cmp = compare(element, node.element);
                if (cmp > 0) {
                    parent = node;
                    node = node.right;
                } else if (cmp < 0) {
                    parent = node;
                    node = node.left;
                } else { //相等的情况下不处理
                    return;
                }
            }
            if (cmp > 0) {
                parent.right = new Node<>(element, parent);
            } else {
                parent.left = new Node<>(element, parent);
            }
            size++;
        }
    }

    /**
     * @param e1
     * @param e2
     * @return >0表示当前的值大于节点的，<0表示当前的节点小于0，等于表示相等
     */
    public int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
    }

    public void remove(E element) {
        remove(node(element));
    }

    public void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        size--;

        if (node.hasTwoChildren()) { //度为2的节点
            //找到后继节点
            Node<E> s = successor(node);
            //用后继节点的值覆盖度为2的节点值
            node.element = s.element;
            //删除后继节点
            node = s;
        }

        //删除节点（节点的度数为1或者为0）
        Node<E> rep = node.left != null ? node.left : node.right;

        if (rep != null) { //node度为1的节点
            rep.parent = node.parent;
            if (node.parent == null) {
                root = rep;
            } else if (node == node.parent.left) {
                node.parent.left = rep;
            } else {
                node.parent.right = rep;
            }
        } else if (node.parent == null) { //node根节点
            root = null;
        } else {//node叶子节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
    }

    public Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            else if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }


    public void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("元素不能为空");
        }
    }

    public boolean contains(E element) {
        return node(element) != null;
    }
}
