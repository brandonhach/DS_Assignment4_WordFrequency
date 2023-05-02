package viz;

import ADTs.DictionaryADT;
import static Apps.Shakespeare_Poems.splitLyrics;
import bridges.connect.Bridges;
import bridges.data_src_dependent.Shakespeare;
import java.util.HashMap;

import java.util.Map;
import java.util.Iterator;
import java.util.List;

/*
Implementing a dictionary by HashMap
*/
public class StandardDictionary<K, V> implements DictionaryADT<K, V> {
    private HashMap<K, V> counts;

    public Iterator<Map.Entry<K, V>> iterator() {
        return counts.entrySet().iterator(); // HashMap are not iterable in java?!
    }

    public StandardDictionary() {
        counts = new HashMap<K, V>();
    }

    // TODO use the get() method in the java built-in HashMap class
    // to make this method complete.
    // If the given key in the HashMap exists, the method returns its associated
    // value. If the given key does not exist in the HashMap,
    // the method returns null.
    @Override
    public V get(K key) {
        // getOrDefault method is just like the get method, but default val is null! C:
        return counts.getOrDefault(key, null);
    }

    // TODO use the put() method in the java built-in HashMap class
    // to make this method complete
    // If the given key in the HashMap exists, the method changes its associated
    // value. If the given key does not exist in the HashMap, the method inserts
    // the key-value associated pair.
    @Override
    public void set(K key, V value) {
        counts.put(key, value);
    }

}
