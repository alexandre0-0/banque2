package model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Lamine
 */
public class ManipTest {
    @Test
    public void testInstanceOf() {
        Object instance = new Commercial(1, "Peu importe", null, null);
        assertTrue(instance instanceof Commercial);
        assertTrue(instance instanceof Object);
        assertFalse(instance instanceof Client);
    }
    
    @Test
    public void testTranstypage() {
        Commercial expected = new Commercial(1, "Peu importe", null, null);
        Object instance = expected;
        
        // Change le type déclaré de instance si c'est possible
        Commercial result = (Commercial) instance;
                
        assertTrue(result == expected);
    }
    
    @Test(expected = ClassCastException.class)
    public void testTranstypageException() {
        Client expected = new Client(1, "Bidule", "Chouette@machin.com");
        Object instance = expected;
        Commercial result = (Commercial) instance;
    }
}
