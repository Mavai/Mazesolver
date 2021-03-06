/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.logic;

import mazesolver.domain.Maze;
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
public class IDATest {
    
    public IDATest() {
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
    public void shortestPathIsFound() {
        Maze maze = new Maze(11, 11);
        IDA ida = new IDA(maze);
        assertTrue(ida.idaSolve() != 1000000000);
    }
    
    @Test
    public void sameRestultAsAstar() {
        Maze maze = new Maze(51, 51);
        IDA ida = new IDA(maze);
        Astar astar = new Astar(maze);
        astar.solve();
        assertTrue(ida.idaSolve() == astar.getDistance(maze.getEndX(), maze.getEndY()));
    }
}
