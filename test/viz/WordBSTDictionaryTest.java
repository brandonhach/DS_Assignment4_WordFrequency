/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viz;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Implementing a dictionary by Binary Search Tree (BST)
 * 
 * @author Qiong
 */
public class WordBSTDictionaryTest {

    public WordBSTDictionaryTest() {
    }

    // Test normal case of the getting and setting method
    @Test
    public void testSet1() {
        WordBSTDictionary<String, Integer> dict;
        dict = new WordBSTDictionary<>();
        String key = "DS";
        Integer val = 1;
        dict.set(key, val);
        assertEquals(dict.get(key).intValue(), val.intValue());
        assertEquals(dict.size(), 1);
    }

    // Test the getting method in an empty dictionay
    @Test
    public void testSet2() {
        WordBSTDictionary<String, Integer> dict;
        dict = new WordBSTDictionary<>();
        String key = "DS";
        assertEquals(dict.get(key), null);
        assertEquals(dict.size(), 0);
    }

    // Test abnormal case of the getting method
    @Test
    public void testSet3() {
        WordBSTDictionary<String, Integer> dict;
        dict = new WordBSTDictionary<>();
        String key1 = "DS";
        String key2 = "Algorithms";
        Integer val = 1;
        dict.set(key1, val);
        assertEquals(dict.get(key2), null);
        assertEquals(dict.size(), 1);
    }

    // Test normal case of the getting and setting method
    @Test
    public void testSet4() {
        WordBSTDictionary<String, Integer> dict;
        dict = new WordBSTDictionary<>();
        String key1 = "DS";
        Integer val1 = 1;
        dict.set(key1, val1);
        String key2 = "Algorithms";
        Integer val2 = 2;
        dict.set(key2, val2);
        assertEquals(dict.get(key1).intValue(), val1.intValue());
        assertEquals(dict.get(key2).intValue(), val2.intValue());
        assertEquals(dict.size(), 2);
    }

    // Test normal case of the getting and setting method
    @Test
    public void testSet5() {
        WordBSTDictionary<String, Integer> dict;
        dict = new WordBSTDictionary<>();
        String key1 = "DS";
        Integer val1 = 1;
        dict.set(key1, val1);
        key1 = "Algorithms";
        Integer val2 = 2;
        dict.set(key1, val2);
        assertEquals(dict.get(key1).intValue(), val2.intValue());
        assertEquals(dict.size(), 2);
    }

    // Test normal case of the getting and setting method
    @Test
    public void testSet6() {
        WordBSTDictionary<String, Integer> dict;
        dict = new WordBSTDictionary<>();
        String key1 = "DS";
        Integer val1 = 1;
        dict.set(key1, val1);
        Integer val2 = 2;
        dict.set(key1, val2);
        assertEquals(dict.get(key1).intValue(), val2.intValue());
        assertEquals(dict.size(), 1);
    }

    @Test
    public void testOrders1() {
        WordBSTDictionary<String, Integer> dict;
        dict = new WordBSTDictionary<>();
        String keys[] = { "a", "b", "c", "d", "e", "f" };
        Integer val = 1;
        for (String key : keys)
            dict.set(key, val);

        assertEquals(dict.size(), 6);
        ArrayList<String> inorder = dict.inorder();
        ArrayList<String> preorder = dict.preorder();
        ArrayList<String> postorder = dict.postorder();
        int index = 0;
        Object inorderitems[] = inorder.toArray();
        Object preorderitems[] = preorder.toArray();
        Object postorderitems[] = postorder.toArray();
        for (String s : keys) {
            assertTrue(s.equalsIgnoreCase((String) (inorderitems[index])));
            assertTrue(s.equalsIgnoreCase((String) (preorderitems[index])));
            assertTrue(s.equalsIgnoreCase((String) (postorderitems[keys.length - 1 - index])));
            index++;
        }
    }

    @Test
    public void testOrders2() {
        WordBSTDictionary<String, Integer> dict;
        dict = new WordBSTDictionary<>();
        String keys[] = { "f", "e", "d", "c", "b", "a" };
        Integer val = 1;
        for (String key : keys)
            dict.set(key, val);

        assertEquals(dict.size(), 6);
        ArrayList<String> inorder = dict.inorder();
        ArrayList<String> preorder = dict.preorder();
        ArrayList<String> postorder = dict.postorder();
        int index = 0;
        Object inorderitems[] = inorder.toArray();
        Object preorderitems[] = preorder.toArray();
        Object postorderitems[] = postorder.toArray();
        for (String s : keys) {
            assertTrue(s.equalsIgnoreCase((String) (inorderitems[keys.length - 1 - index])));
            assertTrue(s.equalsIgnoreCase((String) (preorderitems[index])));
            assertTrue(s.equalsIgnoreCase((String) (postorderitems[keys.length - 1 - index])));
            index++;
        }
    }

    @Test
    public void testOrders3() {
        WordBSTDictionary<String, Integer> dict;
        dict = new WordBSTDictionary<>();
        String keys[] = { "d", "b", "a", "c", "e", "f" };

        // TODO List the pre-order of the binary-search tree built by keys
        String preorderedkeys[] = { "d", "b", "a", "c", "e", "f" };

        // TODO List the in-order of the binary-search tree built by keys
        String inorderedkeys[] = { "a", "b", "c", "d", "e", "f" };

        // TODO List the post-order of the binary-search tree built by keys
        String postorderedkeys[] = { "a", "c", "b", "f", "e", "d" };
        Integer val = 1;
        for (String key : keys)
            dict.set(key, val);

        assertEquals(dict.size(), 6);
        ArrayList<String> inorder = dict.inorder();
        ArrayList<String> preorder = dict.preorder();
        ArrayList<String> postorder = dict.postorder();
        int index = 0;
        Object inorderitems[] = inorder.toArray();
        Object preorderitems[] = preorder.toArray();
        Object postorderitems[] = postorder.toArray();
        for (String s : preorderedkeys) {
            assertTrue(s.equalsIgnoreCase((String) (preorderitems[index])));
            index++;
        }
        index = 0;
        for (String s : inorderedkeys) {
            assertTrue(s.equalsIgnoreCase((String) (inorderitems[index])));
            index++;
        }
        index = 0;
        for (String s : postorderedkeys) {
            assertTrue(s.equalsIgnoreCase((String) (postorderitems[index])));
            index++;
        }
    }
}
