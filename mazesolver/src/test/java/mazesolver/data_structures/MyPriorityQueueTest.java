
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
public class MyPriorityQueueTest {
    MyPriorityQueue<Integer> que;
    
    public MyPriorityQueueTest() {
        que = new MyPriorityQueue<>();
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
        que.add(5);
        que.add(3);
        assertEquals(3, (int) que.peek());
    }
    
    @Test
    public void removingWorks() {
        que.add(5);
        que.add(1);
        que.add(3);
        que.poll();
        assertEquals(3, (int) que.peek());
    }
    
    @Test
    public void listGrowsCorrectly() {
        que = new MyPriorityQueue<>(2);
        que.add(3);
        que.add(2);
        que.add(1);
        assertEquals(4, que.tableSize());
    }


}
