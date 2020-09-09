package tree;

import printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.Stack;

/**
 * url:
 * Author:Savannah
 * Description:
 * AlgorithmLearningProject 6/7/20
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {

    private int size;
    private Node<E> root;
    private Comparator comparator;

    public BinarySearchTree() {

    }

    public BinarySearchTree(Comparator comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {

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
            } else if (cmp < 0) {
                parent.left = new Node<>(element, parent);
            }
        }
    }

    /**
     * 前序遍历二叉树 - 非递归
     */
    public void preOrderTraversalNoRecursive() {
        Node<E> currentNode = root;
        Stack<Node<E>> stack = new Stack<>();
        while (currentNode != null || !stack.isEmpty()) {
            while ((currentNode != null)) {
                System.out.println(currentNode.element);
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            if (!stack.isEmpty()) {
                Node<E> node = stack.pop();
                currentNode = node.right;
            }
        }
    }

    /**
     * 前序遍历二叉树 - 递归
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        if (node.left != null) {
            preOrderTraversal(node.left);
        }
        if (node.right != null) {
            preOrderTraversal(node.right);
        }
    }

    /**
     * @param e1
     * @param e2
     * @return >0表示当前的值大于节点的，<0表示当前的节点小于0，等于表示相等
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
    }

    public void remove(E element) {

    }

    public void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("元素不能为空");
        }
    }

    public boolean contains(E element) {
        return false;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node) node).element.toString();
    }

    private static final class Node<E> {
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        E element;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }
}
