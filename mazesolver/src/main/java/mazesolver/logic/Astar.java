
package mazesolver.logic;

import java.util.ArrayList;
import java.util.PriorityQueue;
import mazesolver.domain.Node;

/**
 *
 * @author Marko Vainio
 */
public class Astar {
    private int[][] distance;
    private boolean[][] used;
    private char[][] maze;
    private PriorityQueue<Node> que;

    public Astar(char[][] maze) {
        this.maze = maze;
        this.distance = new int[maze.length][maze[0].length];
        this.used = new boolean[maze.length][maze[0].length];
        for (int i = 0; i < distance[0].length; i++) {
            for (int j = 0; j < distance.length; j++) {
                distance[j][i] = 1000000000;
            }
        }
        que = new PriorityQueue<>();
    }
    
    public void findShortestPath(int startX, int startY, int endX, int endY) {
        distance[startX][startY] = 0;
        que.add(new Node(startX, startY, 0));
        
        while(!que.isEmpty()) {
            Node current = que.poll();
            int x = current.getX();
            int y = current.getY();
            int dist = current.getDistance();
            
            if (used[x][y]) {
                continue;
            }
            used[x][y] = true;
            
            for (Node next : findPossibleNeighbours(x, y)) {
                if (distance[next.getX()][next.getY()] > dist + next.getDistance()) {
                    distance[next.getX()][next.getY()] = dist + next.getDistance();
                    que.add(new Node(next.getX(), next.getY(), distance[next.getX()][next.getY()]));
                }
            }
        }
    }
    
    public int getDistance(int x, int y) {
        return distance[x][y];
    }
    
    public ArrayList<Node> findPossibleNeighbours(int x, int y) {
        ArrayList<Node> neighbours = new ArrayList<>();
        int xMin = x - 1;
        int xMax = x + 1;
        int yMin = y - 1;
        int yMax = y + 1;
        if (x == 0) {
            xMin = 0;
        }
        if (y == 0) {
            yMin = 0;
        }
        if (x == maze.length - 1) {
            xMax = maze.length - 1;
        }
        if (y == maze[0].length - 1) {
            yMax = maze[0].length - 1;
        }
        if (yMax != y && maze[x][yMax] != '@') {
            neighbours.add(new Node(x, yMax, 1));
        }
        if (yMin != y && maze[x][yMin] != '@') {
            neighbours.add(new Node(x, yMin, 1));
        }
        if (xMax != x && maze[xMax][y] != '@') {
            neighbours.add(new Node(xMax, y, 1));
        }
        if (xMin != x && maze[xMin][y] != '@') {
            neighbours.add(new Node(xMin, y, 1));
        }
        return neighbours;
    }
    
    
    
    
    
}
