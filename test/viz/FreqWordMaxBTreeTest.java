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
public class FreqWordMaxBTreeTest{
    
    public FreqWordMaxBTreeTest() {
    }

    // Test normal case of the getting and setting method
    @Test
    public void testInsert() {
        FreqWordMaxBTree<Integer, String> dict;
        dict = new FreqWordMaxBTree<>();
        Integer key = 150;
        String val = "DS";
        dict.insert(key, val);
        assertEquals(dict.peek().getKey().intValue(), key.intValue());
        assertEquals(dict.size(), 1);
        
        dict.insert(key+1, "Algorithm");
        assertEquals(dict.peek().getKey().intValue(), key+1);
        assertEquals(dict.size(), 2); 

        dict.insert(key-1, "DB");
        assertEquals(dict.peek().getKey().intValue(), key+1);
        assertEquals(dict.size(), 3); 
        
        dict.insert(key+1, "Parallel");
        assertEquals(dict.peek().getKey().intValue(), key+1);
        assertEquals(dict.peek().getValue(), "Algorithm, Parallel");
        assertEquals(dict.size(), 3); 
    }
    
    @Test
    public void testpop() {
        FreqWordMaxBTree<Integer, String> dict;
        dict = new FreqWordMaxBTree<>();
        int i = 0;
        int max = 5;
        for (i = 1; i <= max; i++){
            dict.insert(new Integer(i), "Parallel");
        }
        i--;
        while (i >= 1){
            assertEquals(i, dict.pop().getKey().intValue());
            i--;
        }
    }
    
    @Test
    public void testIsEmpty1() {
        FreqWordMaxBTree<Integer, String> dict;
        dict = new FreqWordMaxBTree<>();
        assertTrue(dict.isEmpty());
    }  
    
    @Test
    public void testIsEmpty2() {
        FreqWordMaxBTree<Integer, String> dict;
        dict = new FreqWordMaxBTree<>();
        dict.insert(new Integer(1), "Test");
        assertFalse(dict.isEmpty());
    }    
}
