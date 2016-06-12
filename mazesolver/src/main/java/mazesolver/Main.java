package mazesolver;

import mazesolver.data_structures.MyPriorityQueue;
import mazesolver.data_structures.MyArrayList;
import mazesolver.data_structures.MyStack;
import mazesolver.domain.Maze;
import mazesolver.domain.Node;
import mazesolver.logic.Astar;
import mazesolver.logic.IDA;
import mazesolver.performance_tests.PerformanceTest;

/**
 *
 * @author markovai
 */
public class Main {

    public static void main(String[] args) {
        PerformanceTest test = new PerformanceTest();
        System.out.println(test.getAstarResult());
        System.out.println(test.getIdaResult());

    }
}
