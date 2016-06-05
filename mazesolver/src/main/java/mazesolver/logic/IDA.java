package mazesolver.logic;

import java.util.ArrayList;
import java.util.Stack;
import mazesolver.data_structures.MyStack;
import mazesolver.domain.Maze;
import mazesolver.domain.Node;

/**
 *
 * @author Marko Vainio
 */
public class IDA {

    private Maze maze;
    private char[][] grid;
    private boolean found;
    private boolean[][] used;
    private final Node[][] path;

    public IDA(Maze maze) {
        this.maze = maze;
        this.grid = maze.getMaze();
        path = new Node[grid.length][grid[0].length];
    }

    public int idaSolve() {
        Node root = new Node(maze.getStartX(), maze.getStartY(), 0);
        int limit = Math.abs(root.getX() - maze.getEndX()) + Math.abs(root.getY() - maze.getEndY());
        while (true) {
            this.used = new boolean[grid.length][grid[0].length];
            int t = search(root, 0, limit);
            if (found) {
                return limit;
            }
            if (t == 1000000) {
                return -1;
            }
            limit = t;
            
        }
    }

    private int search(Node node, int g, int limit) {
        grid[node.getX()][node.getY()] = '$';
        int f = g + Math.abs(node.getX() - maze.getEndX()) + Math.abs(node.getY() - maze.getEndY());
        if (f > limit) {
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
        return min;
    }
    
    public void getShortestPath() {
        MyStack<Node> stack = new MyStack<>();
        Node node = new Node(maze.getEndX(), maze.getEndY(), 0);
        while (node.getX() != maze.getStartX() || node.getY() != maze.getStartY()) {
            stack.push(node);
            node = path[node.getX()][node.getY()];
        }
        while (!stack.isEmpty()) {
            node = stack.pop();
            grid[node.getX()][node.getY()] = '.';
        }
        grid[maze.getStartX()][maze.getStartY()] = '.';
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
