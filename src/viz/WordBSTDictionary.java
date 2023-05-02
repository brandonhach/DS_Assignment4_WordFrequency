package viz;

import ADTs.DictionaryADT;
import bridges.base.BSTElement;
import bridges.connect.Bridges;
import java.lang.Comparable;
import java.security.Key;

import java.util.Iterator;
import java.util.Map;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Stack;

/*
Implementing a dictionary by Binary Search Tree (BST)
BSTElement: refer to
http://bridgesuncc.github.io/doc/java-api/current/html/classbridges_1_1base_1_1_b_s_t_element.html
*/

public class WordBSTDictionary<K extends Comparable<? super K>, V>
        implements DictionaryADT<K, V> {
    private BSTElement<K, V> root;
    int size;

    // Implement iterator logic for BSTDictionary
    class BSTIterator implements Iterator<Map.Entry<K, V>> {
        Stack<BSTElement<K, V>> stack;

        BSTIterator(BSTElement<K, V> r) {
            stack = new Stack<BSTElement<K, V>>();
            while (r != null) {
                stack.push(r);
                r = r.getLeft();
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        // Iterative solution of DFS
        public Map.Entry<K, V> next() {
            BSTElement<K, V> node = stack.pop();
            AbstractMap.SimpleEntry<K, V> result;
            result = new AbstractMap.SimpleEntry<K, V>(node.getKey(), node.getValue());
            if (node.getRight() != null) {
                node = node.getRight();
                while (node != null) {
                    stack.push(node);
                    node = node.getLeft();
                }
            }
            return result;
        }
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new BSTIterator(root);
    }

    public WordBSTDictionary() {
    }

    @Override
    public V get(K k) {
        if (this.root == null || k == null)
            return null;
        return get(this.root, k);
    }

    // TODO If the given key in the BST exists, the method returns its associated
    // value. If the given key does not exist in the BST,
    // the method returns null.
    // The method uses recursive binary search to find
    // the value of the given k. In another word, this method uses
    // recursive binary search to find the frequency of
    // a given word in the BST of Shakespeare Poems.
    private V get(BSTElement<K, V> node, K k) {
        if (node == null)
            return null;

        if (node.getKey().equals(k)) {
            return node.getValue();
        }

        if (k.compareTo(node.getKey()) < 0) {
            return get(node.getLeft(), k);
        }

        return get(node.getRight(), k);

    }

    @Override
    public void set(K k, V e) {
        if (k == null)
            return;
        if (this.root == null) {
            this.root = new BSTElement<K, V>(k, e);
            size = 1;
        } else
            set(null, this.root, true, k, e);
    }

    // If the given key in the BST exists, the method changes its associated
    // value. If the given key does not exist in the BST, the method inserts
    // the key-value associated pair.
    // In this implementation, each subtree is represented by
    // its root node, which is an instance of BSTElement.
    // This algorithm uses recursive binary search to find
    // the BSTElement that holds the given k
    // and then change its value. In another word,the algoritms
    // uses recursive binary search to find
    // a given word in the BST and change its associated frequency.
    private void set(BSTElement<K, V> parentnode,
            BSTElement<K, V> node,
            boolean isLeft,
            K k, V e) {
        if (k == null || e == null)
            return;
        if (node == null && parentnode == null)
            return;
        if (node == null && parentnode != null) {
            size++;
            if (isLeft)
                parentnode.setLeft(
                        new BSTElement<K, V>(k, e));
            else
                parentnode.setRight(
                        new BSTElement<K, V>(k, e));
        } else {
            if (k.compareTo(node.getKey()) == 0)
                node.setValue(e);
            else if (k.compareTo(node.getKey()) > 0)
                set(node, node.getRight(), false, k, e);
            else
                set(node, node.getLeft(), true, k, e);
        }
    }

    public ArrayList<K> inorder() {
        if (this.root == null)
            return null;

        ArrayList<K> list = new ArrayList<K>();
        return inorder(this.root, list);
    }

    // TODO For Inorder, we traverse from the left subtree to
    // the root then to the right subtree.In the case of binary search trees (BST),
    // the inorder traversal gives nodes in non-decreasing order
    private ArrayList<K> inorder(BSTElement<K, V> node,
            ArrayList<K> list) {
        if (node != null) {
            inorder(node.getLeft(), list);
            list.add(node.getKey());
            inorder(node.getRight(), list);
        }

        return list;
    }

    public ArrayList<K> preorder() {
        if (this.root == null)
            return null;

        ArrayList<K> list = new ArrayList<K>();
        return preorder(this.root, list);
    }

    // TODO For preorder, we traverse from the root to
    // the left subtree then to the right subtree.
    private ArrayList<K> preorder(BSTElement<K, V> node,
            ArrayList<K> list) {
        // TODO
        if (node != null) {
            list.add(node.getKey());
            preorder(node.getLeft(), list);
            preorder(node.getRight(), list);
        }
        return list;
    }

    public ArrayList<K> postorder() {
        if (this.root == null)
            return null;

        ArrayList<K> list = new ArrayList<K>();
        return postorder(this.root, list);
    }

    // TODO For postorder, we traverse from
    // the left subtree to the right subtree
    // then to the root.
    private ArrayList<K> postorder(BSTElement<K, V> node,
            ArrayList<K> list) {
        // TODO
        if (node != null) {
            postorder(node.getLeft(), list);
            postorder(node.getRight(), list);
            list.add(node.getKey());
        }
        return list;
    }

    public int size() {
        return this.size;
    }

    /// visualization function
    public void visualize(Bridges bridges) {
        if (root != null) {
            root.setColor("red");
            bridges.setDataStructure(root);
            try {
                bridges.visualize();
            } catch (Exception e) {
                System.err.println("Exception :" + e.getMessage());
            }
        }
    }

}
