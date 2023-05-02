/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viz;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Qiong
 */
public class StandardDictionaryTest {
    
    public StandardDictionaryTest() {
    }

    // Test normal case of the getting and setting method
    @Test
    public void testSet1() {
        StandardDictionary<String, Integer> dict;
        dict = new StandardDictionary<>();
        String key = "DS";
        Integer val = 1;
        dict.set(key, val);
        assertEquals(dict.get(key).intValue(), val.intValue());
    }

    // Test the getting method in an empty dictionay   
    @Test
    public void testSet2() {
        StandardDictionary<String, Integer> dict;
        dict = new StandardDictionary<>();
        String key = "DS";
        assertEquals(dict.get(key), null);
    }
    
    // Test abnormal case of the getting method
    @Test
    public void testSet3() {
        StandardDictionary<String, Integer> dict;
        dict = new StandardDictionary<>();
        String key1 = "DS";
        String key2 = "Algorithms";
        Integer val = 1;
        dict.set(key1, val);
        assertEquals(dict.get(key2), null);
    }
    
    // Test normal case of the getting and setting method
    @Test
    public void testSet4() {
        StandardDictionary<String, Integer> dict;
        dict = new StandardDictionary<>();
        String key1 = "DS";
        Integer val1 = 1;
        dict.set(key1, val1);
        String key2 = "Algorithms";
        Integer val2 = 2;
        dict.set(key2, val2);
        assertEquals(dict.get(key1).intValue(), val1.intValue());
        assertEquals(dict.get(key2).intValue(), val2.intValue());
    }
    
    // Test normal case of the getting and setting method
    @Test
    public void testSet5() {
        StandardDictionary<String, Integer> dict;
        dict = new StandardDictionary<>();
        String key1 = "DS";
        Integer val1 = 1;
        dict.set(key1, val1);
        key1 = "Algorithms";
        Integer val2 = 2;
        dict.set(key1, val2);
        assertEquals(dict.get(key1).intValue(), val2.intValue());
    }

    // Test normal case of the getting and setting method
    @Test
    public void testSet6() {
        StandardDictionary<String, Integer> dict;
        dict = new StandardDictionary<>();
        String key1 = "DS";
        Integer val1 = 1;
        dict.set(key1, val1);
        Integer val2 = 2;
        dict.set(key1, val2);
        assertEquals(dict.get(key1).intValue(), val2.intValue());
    }
}
