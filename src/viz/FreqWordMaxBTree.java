package viz;

import bridges.base.BSTElement;
import java.util.List;
import java.lang.Integer;

// Import Bridges and relevant data source 
import bridges.connect.Bridges;
import bridges.base.BinTreeElement;
import bridges.data_src_dependent.Shakespeare;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Stack;

/**
 * Implement a maximum binary tree
 * A maximum binary tree is a binary tree structure that is
 * satisfied with the recursive property that each node has a
 * key larger than the keys in its child nodes. The property
 * determines that the root node holds the largest key among
 * all nodes in the tree and every node holds the largest key
 * among all nodes in the subtree rooted in the node.
 * 
 * In this code, we would like to build a frequency-based
 * maximum binary tree.
 * 
 * @author Qiong
 * @param <K>
 * @param <V>
 */
public class FreqWordMaxBTree<K extends Integer, V extends String>
        implements Iterable<Map.Entry<K, V>> {
    // private FreqWordHeapElement<Integer, String> root;
    private BSTElement<K, V> root;
    int size;

    class HeapIterator implements Iterator<Map.Entry<K, V>> {
        Stack<BSTElement<K, V>> stack;

        HeapIterator(BSTElement<K, V> r) {
            stack = new Stack<BSTElement<K, V>>();
            while (r != null) {
                stack.push(r);
                r = r.getLeft();
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

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
        return new HeapIterator(root);
    }

    public FreqWordMaxBTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(Integer freq, String word) {
        if (isEmpty()) {
            this.root = new BSTElement<>((K) freq, (V) word);
            size++;
            return;
        } else
            insert(this.root, freq, word);
    }

    // TODO insert the key-value pair to the BST
    // satisfying the key ordering property of BST
    private void insert(BSTElement<K, V> node,
            Integer freq, String word) {
        if (freq.compareTo(node.getKey()) == 0)
            node.setValue((V) (node.getValue().toString() + ", " + word));
        else {
            K k = (K) freq;
            V v = (V) word;
            // TODO if the freq larger than node key, remember
            // the node key and value and replace
            // node key and value with given key and value
            // If node has no left or right child node, make
            // a new BSTElement node and insert it to the node as
            // its left or right child node.
            // if node has both left and right child node, get
            // the smaller child node and insert the K-V pair to
            // the subtree rooted by the smaller child node
            if (freq.compareTo(node.getKey()) > 0) {
                k = node.getKey();
                v = node.getValue();
                node.setValue((V) word);
                node.setKey((K) freq);
            }
            if (node.getLeft() == null) {
                node.setLeft(new BSTElement<>(k, v));
                size++;
            } else if (node.getRight() == null) {
                node.setRight(new BSTElement<>(k, v));
                size++;
            } else if (node.getLeft().getValue().compareTo(node.getRight().getValue()) > 0) {
                insert(node.getRight(), k, v);
            } else {
                insert(node.getLeft(), k, v);
            }
        }
    }

    // Get the highest frequent word(s)
    public Map.Entry<K, V> peek() {
        if (root == null)
            return null;
        AbstractMap.SimpleEntry<K, V> en;
        en = new AbstractMap.SimpleEntry<K, V>(root.getKey(), root.getValue());

        return en;
    }

    public Map.Entry<K, V> pop() {
        if (root == null)
            return null;
        return pop(null, this.root, true);
    }

    // Get the highest frequent word(s)
    private Map.Entry<K, V> pop(BSTElement<K, V> parent,
            BSTElement<K, V> node, boolean isLeft) {
        if (node == null)
            return null;
        AbstractMap.SimpleEntry<K, V> en;
        en = new AbstractMap.SimpleEntry<K, V>(node.getKey(), node.getValue());

        if (node.getLeft() == null && node.getRight() == null) {
            if (parent == null)
                root = null;
            else if (isLeft)
                parent.setLeft(null);
            else
                parent.setRight(null);
            size--;
        } else if (node.getLeft() != null && node.getRight() == null) {
            if (parent == null)
                root = root.getLeft();
            else if (isLeft)
                parent.setLeft(node.getLeft());
            else
                parent.setRight(node.getLeft());
            size--;
        } else if (node.getLeft() == null && node.getRight() != null) {
            if (parent == null)
                root = root.getRight();
            else if (isLeft)
                parent.setLeft(node.getRight());
            else
                parent.setRight(node.getRight());
            size--;
        } else {
            K k;
            V v;
            if (node.getLeft().getKey().compareTo(node.getRight().getKey()) > 0) {
                k = node.getLeft().getKey();
                v = node.getLeft().getValue();
                pop(node, node.getLeft(), true);
            } else {
                k = node.getRight().getKey();
                v = node.getRight().getValue();
                pop(node, node.getRight(), false);
            }
            node.setKey(k);
            node.setValue(v);
        }
        return en;
    }

    public int size() {
        return this.size;
    }

    /// visualization features.
    public void visualize(Bridges bridges) {

        this.updateLabels();
        root.setColor("red");
        // Pass the data structure to Bridges
        bridges.setDataStructure(this.root);

        // Visualize the list
        try {
            bridges.visualize();
        } catch (Exception e) {
            System.err.println("exception: " + e.getMessage());
        }
    }

    protected void updateLabels() {
        if (root != null)
            updateLabels(root);
    }

    private void updateLabels(BSTElement<K, V> localRoot) {
        localRoot.setLabel(localRoot.getKey().toString() + ", " + localRoot.getValue().toString());

        if (localRoot.getLeft() != null)
            updateLabels((BSTElement<K, V>) (localRoot.getLeft()));

        if (localRoot.getRight() != null)
            updateLabels((BSTElement<K, V>) (localRoot.getRight()));
    }

}
