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
        Astar astar = new Astar(maze.getMaze());
        System.out.println(astar.getDistance(maze.getStartX(), maze.getStartY()));
        astar.findShortestPath(maze.getStartX(), maze.getStartY(), maze.getEndX(), maze.getEndY());
        int dist = astar.getDistance(maze.getEndX(), maze.getEndY());
        astar.getShortestPath(maze.getStartX(), maze.getStartY(), maze.getEndX(), maze.getEndY());
        maze.print();
        System.out.println(dist);

    }
}
