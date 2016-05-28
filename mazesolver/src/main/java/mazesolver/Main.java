package mazesolver;

import mazesolver.domain.Maze;
import mazesolver.logic.Astar;

/**
 *
 * @author markovai
 */
public class Main {

    public static void main(String[] args) {
            Maze maze = new Maze(201, 21);
            Astar astar = new Astar(maze.getMaze());
            System.out.println(astar.getDistance(maze.getStartX(), maze.getStartY()));
            astar.findShortestPath(maze.getStartX(), maze.getStartY(), 0, 0);
            int dist = astar.getDistance(maze.getEndX(), maze.getEndY());
            maze.print();
            System.out.println(dist);
            
    }

}
