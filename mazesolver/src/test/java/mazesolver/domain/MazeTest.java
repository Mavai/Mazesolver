package mazesolver.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import mazesolver.logic.Astar;
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
public class MazeTest {

    public MazeTest() {
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
    public void possbileDirectionsCorrectly() {
        Maze maze = new Maze();
        String[] directions = new String[4];
        assertEquals(0, maze.possibleDirections(0, 1, directions));
    }
    
    @Test
    public void twoExits() {
        Maze maze = new Maze(11, 11);
        assertTrue((maze.getStartX() == 0 && maze.getStartY() != 0 && maze.getEndX() == maze.getGrid().length - 1 && maze.getEndY() != 0));
    }
    
    @Test
    public void shortestPathIsFound() {
        Maze maze = new Maze(11, 11);
        Astar astar = new Astar(maze);
        astar.solve();
        assertTrue(astar.getDistance(maze.getEndX(), maze.getEndY()) != 1000000000);
    }

}
