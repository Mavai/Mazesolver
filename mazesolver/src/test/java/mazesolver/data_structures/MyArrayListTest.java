
package mazesolver.data_structures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marko Vainio
 */
public class MyArrayListTest {
    MyArrayList<Integer> list;
    
    public MyArrayListTest() {
        list = new MyArrayList();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void addingWorks() {
        list.add(5);
        assertEquals(5, (int)list.get(0));
    }
    
    @Test
    public void sizeIsCorrect() {
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
    }
    
    @Test
    public void removingWorks() {
        list = new MyArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(0);
        assertTrue(list.size() == 2 && list.get(0) == 2 && list.get(2) == null);
    }

}
