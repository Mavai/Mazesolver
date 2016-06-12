package mazesolver;

import mazesolver.data_structures.MyPriorityQueue;
import mazesolver.data_structures.MyArrayList;
import mazesolver.data_structures.MyStack;
import mazesolver.domain.Maze;
import mazesolver.domain.Node;
import mazesolver.logic.Astar;
import mazesolver.logic.IDA;

/**
 *
 * @author markovai
 */
public class Main {

    public static void main(String[] args) {
        
        Maze maze = new Maze(101,21);
        Astar astar = new Astar(maze);
//        IDA ida = new IDA(maze);
//        System.out.println(astar.getDistance(maze.getStartX(), maze.getStartY()));
        astar.findShortestPath();
        astar.getShortestPath();
//        System.out.println(ida.idaSolve());
//        ida.getShortestPath();
//        maze.print();
//        
        
        maze.print();
//        int dist = astar.getDistance(maze.getEndX(), maze.getEndY());
//        System.out.println(dist);

    }
}
