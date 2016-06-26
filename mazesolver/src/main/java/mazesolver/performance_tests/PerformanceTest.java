package mazesolver.performance_tests;

import mazesolver.domain.Maze;
import mazesolver.logic.Astar;
import mazesolver.logic.IDA;

/**
 *
 * @author Marko Vainio
 */
public final class PerformanceTest {

    private Maze maze;
    private double astarResult;
    private double idaResult;

    public PerformanceTest() {
    }

    public void runTests(int x, int y) {
        testIda(x, y);
        testAstar(x, y);
        
    }

    public void testAstar(int x, int y) {
        long result = 0;
        for (int i = 0; i < 10; i++) {
            maze = new Maze(x, y);
            Astar astar = new Astar(maze);
            long startTime = System.currentTimeMillis();
            astar.solve();
            long endTime = System.currentTimeMillis();
            result += endTime - startTime;
        }
        astarResult = result / 10;
    }

    public void testIda(int x, int y) {
        long result = 0;
        for (int i = 0; i < 10; i++) {
            maze = new Maze(x, y);
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
