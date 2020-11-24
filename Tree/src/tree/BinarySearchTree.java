package tree;

import printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
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
        root = null;
        size = 0;
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
     * 是否是完全二叉树
     *
     * @return
     */
    public boolean isCompleteTree() {
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            } else {
                if (node.right != null) {
                    return false;
                }
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 前驱节点
     *
     * @param node
     * @return
     */
    public Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return null;
        }
        //左子树不为空
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return node;
        }

        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        //node.parent==null || node = node.parent.right
        return node.parent;
    }

    public Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 层序遍历后返回树的高度
     *
     * @return
     */
    public int getTreeSize() {
        return levelOrderTraversal();
    }

    public int levelOrderTraversal() {
        return levelOrderTraversal(root);
    }

    public int height2() {
        return height(root);
    }

    /**
     * 递归方式计算数的高度
     * @param node
     * @return
     */
    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }
    /**
     * 层序遍历
     *
     * @param node
     * @return 返回的是树的高度
     */
    public int levelOrderTraversal(Node<E> node) {
        if (node == null) {
            return 0;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        int level = 1;
        int height = 0;
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            level--;
            System.out.println(poll.element);
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
            if (level == 0) {
                level = queue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * 后序遍历二叉树- 递归
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    public void postOrderTraversal(Node<E> node) {
        if (node == null) return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.element);
    }


    /**
     * 中序遍历二叉树，- 递归
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    public void inOrderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left);
        System.out.println(node.element);
        inOrderTraversal(node.right);
    }

    /**
     * 中序遍历二叉树-非递归
     */
    public void inOrderTraversalNoRecursive() {
        Stack<Node<E>> stack = new Stack<>();
        Node<E> currentNode = root;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            if (!stack.isEmpty()) {
                Node<E> pop = stack.pop();
                System.out.println(pop.element);
                currentNode = pop.right;
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

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }
}
