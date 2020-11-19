package BinarySearchTree;

import BinarySearchTree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> implements BinaryTreeInfo {

    private Integer size=0;
    private Node<E> root; //根节点
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }
    public BinarySearchTree(Comparator<E> comparator) {
            this.comparator = comparator;
    }

    /**
     * @return 元素的数量
     */
    public int size() {
        return size;
    }

    /**
     * @return  是否为空
     */
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * 清空所有元素
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * @param element 添加元素
     */
    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            root = new Node<E>(element, null);
            size++;
            return;
        }
        //添加的不是第一个节点
            Node<E> parent = root;
            Node<E> node = root;
            int cmp = 0;
            do {
                cmp = compare(element, node.element);
                parent = node;
                if (cmp > 0) {
                    node = node.right;
                }else if (cmp < 0) {
                    node = node.left;
                }else { //cmp == 0
                    node.element = element;
                    return;
                }
            } while (node != null);
            Node<E> newNode = new Node<E>(element, parent);
            if (cmp > 0) {
                parent.right = newNode;
            }else {
                parent.left = newNode;
            }
            size++;
    }

    /**
     * @param element 删除元素
     */
    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        //节点不为null
        if (node.hasTwoChildren()) {//度为2的节点
            Node<E> s = successor(node);
            node.element = s.element;
            node = s;
        }
        //node的度为1或者为0
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {//度为1的节点
            replacement.parent = node.parent;
            if (node.parent == null) {//node是度为1的节点并且是根节点
                root = replacement;
            }else if (node == node.parent.left) {
                node.parent.left = replacement;
            }else {
                node.parent.right = replacement;
            }
        }else if (node.parent == null) {//叶子节点同时也是根节点
            root = null;
        }else {//叶子节点但不是根节点
            if (node == node.parent.right) {
                node.parent.right = null;
            }else {
                node.parent.left = null;
            }
        }
        size--;
    }
    /**
     * @return 找到节点node
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) {//则相等
                return node;
            }
            if (cmp > 0) {//element > node.element
                node = node.right;
            }else { //cmp < 0 element < node.element
                node = node.left;
            }
        }
        return null;
    }

    /**
     * @param element // 是否包含某元素
     * @return true代表包含 false则不包含
     */
    public boolean contains(E element) {
        return node(element) != null;
    }

    /**
     * 前序遍历---递归
     */
    public void preorderTraversal() {
        preorderTraversal(root);
    }
    public void preorderTraversal(Visitor<E> visitor) {
        preorderTraversal(root, visitor);
    }
    private void preorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }
    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) {
            return;
        }
        visitor.visit(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }
    /**
     * 中序遍历--递归
     */
    public void inorderTraversal() {
        inorderTraversal(root);
    }
    public void inorderTraversal(Visitor<E> visitor) {
        inorderTraversal(root, visitor);
    }
    private void inorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }
    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) {
            return;
        }
        inorderTraversal(node.left, visitor);
        visitor.visit(node.element);
        inorderTraversal(node.right, visitor);
    }

    /**
     * 后序遍历--递归
     */
    public void postorderTraversal() {
        postorderTraversal(root);
    }
    public void postorderTraversal(Visitor<E> visitor) {
        postorderTraversal(root, visitor);
    }
    private void postorderTraversal(Node<E> node) {
        if (node == null)   return;
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }
    private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null)   return;
        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        visitor.visit(node.element);
    }

    /**
     * 层序遍历--非递归
     */
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }
    /**
     * @param visitor 层序遍历的打印方法 --> visitor.visit(node.element);
     */
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            visitor.visit(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }
    public static interface Visitor<E> {
        void visit(E element);
    }

    /**
     * @return 返回二叉树高度
     */
    public int height() {
//        return height(root);
        return height2();
    }
    private int height(Node<E> node) {//--递归
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }
    private int height2() {//非递归
        if (root == null) {
            return 0;
        }
        int heightIndex = 0;
        int levelSize = 1; //存储每一层的元素数量
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;
           // System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelSize == 0) {
                levelSize = queue.size();
                heightIndex++;
            }
        }
        return heightIndex;
    }

    /**
     * @return 判断是不是完全二叉树 返回true代表是， false代表不是
     */
    public boolean isComplete() {
        if (root == null) {
            return false;
        }
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
            }else if (node.right != null) {
                return false;
            }
            if (node.right != null) {
                queue.offer(node.right);
            }else {
                leaf = true;
            }
        }
        return true;
    }

    /**
     * @return 返回node的前驱节点
     */
    private Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return node;
        }
        Node<E> pLeft = node.left;
        if (pLeft!= null) {
            //先找第一个左节点，然后找到最后一个右节点

            while (pLeft != null) {
                pLeft = pLeft.right;
            }
            return pLeft;
        }
        //从父节点中寻找
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        //循环结束--两种情况
        //1.node.parent == null
        //或者 node.parent.right == node
        return node.parent;
    }

    /**
     * @return 返回node的后继节点
     */
    private Node<E> successor(Node<E> node) {
        if (node == null) {
            return node;
        }
        Node<E> pRight = node.right;
        if (pRight != null) {
            while (pRight != null) {
                pRight = pRight.left;
            }
            return pRight;
        }
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }
    //=================================================
    /**
     * 比较元素大小
     * @return 返回值等于0，则e1=e2； 返回值大于0，则e1>e2； 返回值小于0，则e1<e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }
    /**
     * @param element 检测所传的参数是否为null
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("参数不能为null");
        }
    }

    //-----------------------------------
    //以下为实现打印器方法
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element;
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

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
