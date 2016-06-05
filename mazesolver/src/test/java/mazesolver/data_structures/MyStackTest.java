/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.data_structures;

import mazesolver.domain.Node;
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
public class MyStackTest {
    MyStack<Integer> stack;
    
    public MyStackTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stack = new MyStack<>();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void addingItemsWorks() {
        stack.push(10);
        assertEquals(10,(int) stack.peek());
    }
    
    @Test
    public void removingItemsWorks() {
        stack.push(10);
        stack.push(5);
        stack.pop();
        assertEquals(10,(int) stack.peek());
    }
    
    @Test
    public void differentTypeStackWorks() {
        MyStack<Node> testStack = new MyStack<>();
        Node node = new Node(1, 1, 1);
        testStack.push(node);
        assertEquals(node, testStack.pop());
    }
    
    @Test
    public void StackGrowsAutomatically() {
        MyStack<Integer> testStack = new MyStack<>(1);
        testStack.push(5);
        testStack.push(5);
        testStack.push(5);
        testStack.push(5);
        testStack.push(5);
        assertEquals(8, testStack.arraySize());
    }
}
