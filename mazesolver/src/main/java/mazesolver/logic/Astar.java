package mazesolver.logic;

import mazesolver.data_structures.*;
import mazesolver.domain.Maze;
import mazesolver.domain.Node;

/**
 * This class provides implementation for A-Star pathfinding algorithm. It gets
 * the maze in which we are looking for a path as a parameter.
 *
 * @author Marko Vainio
 */
public class Astar {

    private final int[][] distance;
    private final boolean[][] used;
    private final Maze maze;
    private final Node[][] path;
    private final char[][] grid;
    private final MyArrayList<Node> shortestPath;
    private final MyQueue<Node> visitedNodes;

    /**
     * Constructor for A-Star pathfinding algorithm.
     *
     * @param maze Maze in which we aare looking for a path.
     */
    public Astar(Maze maze) {
        this.maze = maze;
        this.grid = maze.getGrid();
        this.distance = new int[grid.length][grid[0].length];
        this.used = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < distance[0].length; i++) {
            for (int j = 0; j < distance.length; j++) {
                distance[j][i] = 1000000000;
            }
        }
        this.path = new Node[grid.length][grid[0].length];
        this.shortestPath = new MyArrayList<>();
        this.visitedNodes = new MyQueue<>();
    }

    /**
     * Goes through nodes in the grid in order to find the shortest path. Nodes
     * are put in a priority queue when found and the best candidate is looked
     * at any given time.
     *
     */
    public void solve() {
        MyPriorityQueue<Node> que = new MyPriorityQueue<>();
        distance[maze.getStartX()][maze.getStartY()] = 0;
        boolean found = false;
        que.add(new Node(maze.getStartX(), maze.getStartY(), 0));

        while (!que.isEmpty()) {
            Node current = que.poll();
            int x = current.getX();
            int y = current.getY();
            int dist = distance[x][y];

            if (used[x][y]) {
                continue;
            }
            used[x][y] = true;
            visitedNodes.add(current);

            for (Node next : findPossibleNeighbours(x, y)) {
                int estimate = (Math.abs(next.getX() - maze.getEndX()) + Math.abs(next.getY() - maze.getEndY()));
                if (distance[next.getX()][next.getY()] > dist + 1) {
                    distance[next.getX()][next.getY()] = dist + 1;
                    que.add(new Node(next.getX(), next.getY(), distance[next.getX()][next.getY()] + estimate));
                    path[next.getX()][next.getY()] = current;
                    if (next.getX() == maze.getEndX() && next.getY() == maze.getEndY()) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                break;
            }
        }
    }

    /**
     * Extracts the shortest path from the class' path array and marks it in the
     * grid.
     */
    public void findShortestPath() {
        if (distance[maze.getEndX()][maze.getEndY()] == 1000000000) {
            return;
        }
        MyStack<Node> stack = new MyStack<>();
        Node node = new Node(maze.getEndX(), maze.getEndY(), 0);
        while (node.getX() != maze.getStartX() || node.getY() != maze.getStartY()) {
            stack.push(node);
            node = path[node.getX()][node.getY()];
        }
        while (!stack.isEmpty()) {
            node = stack.pop();
            shortestPath.add(node);
        }
        shortestPath.add(new Node(maze.getStartX(), maze.getStartY(), 1));
    }

    public MyArrayList<Node> getShortestPath() {
        return shortestPath;
    }

    public MyQueue<Node> getVisitedNodes() {
        return visitedNodes;
    }

    public int getDistance(int x, int y) {
        return distance[x][y];
    }

    /**
     * Determines all possible neighbours for a node in the grid i.e nodes which
     * arent walls.
     *
     * @param x Current x-coordinate.
     * @param y Current x-coordinate.
     * @return Array of neighbour nodes.
     */
    public MyArrayList<Node> findPossibleNeighbours(int x, int y) {
        MyArrayList<Node> neighbours = new MyArrayList<>();
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
        if (x == grid.length - 1) {
            xMax = grid.length - 1;
        }
        if (y == grid[0].length - 1) {
            yMax = grid[0].length - 1;
        }
        if (xMax != x && grid[xMax][y] != '@') {
            neighbours.add(new Node(xMax, y, 1));
        }
        if (yMax != y && grid[x][yMax] != '@') {
            neighbours.add(new Node(x, yMax, 1));
        }
        if (yMin != y && grid[x][yMin] != '@') {
            neighbours.add(new Node(x, yMin, 1));
        }
        if (xMin != x && grid[xMin][y] != '@') {
            neighbours.add(new Node(xMin, y, 1));
        }
        return neighbours;
    }

}
