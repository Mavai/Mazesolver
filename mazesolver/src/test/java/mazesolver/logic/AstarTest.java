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
public class AstarTest {
    
    public AstarTest() {
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
        Astar astar = new Astar(maze);
        astar.findShortestPath();
        assertTrue(astar.getDistance(maze.getEndX(), maze.getEndY()) != 1000000000);
    }
    
    @Test
    public void sameRestultAsIDA() {
        Maze maze = new Maze(51, 51);
        IDA ida = new IDA(maze);
        Astar astar = new Astar(maze);
        astar.findShortestPath();
        assertTrue(ida.idaSolve() == astar.getDistance(maze.getEndX(), maze.getEndY()));
    }
}
