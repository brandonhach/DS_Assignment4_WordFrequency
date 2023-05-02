package ADTs;

import java.util.Map;

public interface DictionaryADT<K, V> extends Iterable<Map.Entry<K,V>> {
    /**
     * Returns the node at key
     * @param key key of node to get
     * @return value
     */
    V get(K key);

    /**
     * sets the value of node at key with value
     * @param key key of node to be set
     * @param value new value of node
     */
    void set(K key, V value);
}
