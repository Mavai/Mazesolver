package mazesolver.logic;

import mazesolver.data_structures.*;
import mazesolver.domain.Maze;
import mazesolver.domain.Node;

/**
 * Offers implementation for an IDA pathfinding algorithm.
 *
 * @author Marko Vainio
 */
public class IDA {

    private final Maze maze;
    private final char[][] grid;
    private boolean found;
    private boolean[][] used;
    private final Node[][] path;
    private final MyQueue<Node> visited;
    private final MyArrayList<Node> shortestPath;
    private int distance;

    /**
     * Constructor for an IDA algorithm.
     *
     * @param maze The maze where we are looking for a path.
     */
    public IDA(Maze maze) {
        this.maze = maze;
        this.grid = maze.getGrid();
        path = new Node[grid.length][grid[0].length];
        visited = new MyQueue<>();
        this.shortestPath = new MyArrayList<>();
    }

    /**
     * The main algorithm which performs multiple Depth-First-Searches in the
     * maze with different limitations.
     *
     * @return Returns the length of the shortest path nad -1 if one isn't
     * found.
     */
    public int idaSolve() {
        Node root = new Node(maze.getStartX(), maze.getStartY(), 0);
        int limit = Math.abs(root.getX() - maze.getEndX()) + Math.abs(root.getY() - maze.getEndY());
        while (true) {
            this.used = new boolean[grid.length][grid[0].length];
            int t = search(root, 0, limit);
            if (found) {
                distance = limit;
                return limit;
            }
            if (t == 1000000) {
                distance = -1;
                return -1;
            }
            limit = t;
        }
    }

    /**
     * Depth-First-Search algorithm.
     *
     * @param node Node where the algorithm starts the recursive process.
     * @param g Current depth.
     * @param limit Length of current best path.
     * @return Length of current best path.
     */
    private int search(Node node, int g, int limit) {
        visited.add(node);
        int f = g + Math.abs(node.getX() - maze.getEndX()) + Math.abs(node.getY() - maze.getEndY());
        if (f > limit) {
            visited.add(node);
            return f;
        }
        if (node.getX() == maze.getEndX() && node.getY() == maze.getEndY()) {
            found = true;
            return limit;
        }
        int min = 1000000;

        for (Node successor : findPossibleNeighbours(node.getX(), node.getY())) {
            if (!used[successor.getX()][successor.getY()]) {
                used[node.getX()][node.getY()] = true;
                path[successor.getX()][successor.getY()] = node;
                int t = search(successor, g + 1, limit);
                if (found) {
                    return limit;
                }
                if (t < min) {
                    min = t;
                }
            }

        }
        visited.add(node);
        return min;
    }

    /**
     * Gets the nodes from the shortest path.
     */
    public void findShortestPath() {
        if (distance == -1) {
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

    /**
     * Verifies node's possible neighbours.
     *
     * @param x Current X-coordinate.
     * @param y Current y-coordinate.
     * @return Returns list of possible neighbour nodes.
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

    public MyQueue<Node> getVisited() {
        return visited;
    }

    public MyArrayList<Node> getShortestPath() {
        return shortestPath;
    }
    
    

}
