package mazesolver.performance_tests;

import mazesolver.domain.Maze;
import mazesolver.logic.Astar;
import mazesolver.logic.IDA;

/**
 *
 * @author Marko Vainio
 */
public class PerformanceTest {

    private Maze maze;
    private double astarResult;
    private double idaResult;

    public PerformanceTest() {
        testAstar();
        testIda();
    }

    public void testAstar() {
        long result = 0;
        for (int i = 0; i < 10; i++) {
            maze = new Maze(201, 201);
            Astar astar = new Astar(maze);
            long startTime = System.currentTimeMillis();
            astar.findShortestPath();
            long endTime = System.currentTimeMillis();
            result += endTime - startTime;
        }
        astarResult = result / 10;
    }

    public void testIda() {
        long result = 0;
        for (int i = 0; i < 10; i++) {
            maze = new Maze(201, 201);
            IDA ida = new IDA(maze);
            long startTime = System.currentTimeMillis();
            ida.idaSolve();
            long endTime = System.currentTimeMillis();
            result += endTime - startTime;
        }
        idaResult = result / 10;
    }

    public double getAstarResult() {
        return astarResult;
    }

    public double getIdaResult() {
        return idaResult;
    }

}
