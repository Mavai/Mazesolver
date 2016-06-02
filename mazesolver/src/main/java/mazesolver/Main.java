package mazesolver;

import mazesolver.domain.Maze;
import mazesolver.logic.Astar;

/**
 *
 * @author markovai
 */
public class Main {

    public static void main(String[] args) {
        
        Maze maze = new Maze(51,11);
        Astar astar = new Astar(maze);
        System.out.println(astar.getDistance(maze.getStartX(), maze.getStartY()));
        astar.findShortestPath();
        int dist = astar.getDistance(maze.getEndX(), maze.getEndY());
        astar.getShortestPath();
        maze.print();
        System.out.println(dist);

    }
}
