package mazesolver.domain;

import java.util.Random;

/**
 * This class provides implementation for a randomized ascii maze with two exit points and no loops.
 * @author Marko Vainio
 */
public final class Maze {

    private final char[][] maze;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Random r = new Random();
    
/**
 * The primary constructor with width and height parameters.
 * @param width
 * @param height 
 */
    public Maze(int width, int height) {
        this.maze = new char[width][height];
        initialize();
        createMiddle();
        createExitpoints();
    }

    /**
     * Constructor that creates and empty maze, for test purposes.
     */
    public Maze() {
        this.maze = new char[25][25];
        for (int i = 0; i < maze[0].length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (j == 0 || i == 0 || i == maze[0].length - 1 || j == maze.length - 1) {
                    maze[j][i] = '@';
                } else {
                    maze[j][i] = ' ';
                }
            }
        }
        createExitpoints();
    }

    /**
     * Initializes the grid in which the maze will be made.
     */
    public void initialize() {
        for (int i = 0; i < maze[0].length; i++) {
            for (int j = 0; j < maze.length; j++) {
                maze[j][i] = '@';
            }
        }
    }

    /**
     * Prints the maze.
     */
    public void print() {
        for (int i = 0; i < maze[0].length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[j][i]);
            }
            System.out.println("");
        }
        System.out.println("----------------------------------------");
    }

    /**
     * Creates the middle section of the maze. Starts the recursive process from the middle of the grid.
     */
    public void createMiddle() {
        int beginX = maze.length / 2;
        if (beginX % 2 != 1) {
            beginX++;
        }
        int beginY = maze[0].length / 2;
        if (beginY % 2 != 1) {
            beginY++;
        }
        generateMaze(beginX, beginY);
    }

    /**
     * Creates start and goal points for the maze.
     */
    public void createExitpoints() {
        int x = 0;
        int y = 1;
        if (maze[x + 1][y] != ' ') {
            y++;
        }
        maze[x][y] = ' ';
        this.startX = x;
        this.startY = y;
        x = maze.length - 1;
        y = maze[0].length - 1;
        if (maze[x - 1][y] != ' ') {
            y--;
        }
        maze[x][y] = ' ';
        this.endX = x;
        this.endY = y;
    }

    /**
     * Recursive method that determines which way the maze will be continued.
     * @param x Current x-coordinate
     * @param y Current y-coordinate
     */
    public void generateMaze(int x, int y) {
        String[] directions = new String[4];
        int directionsCount;
        String direction;
        while (true) {
            directionsCount = possibleDirections(x, y, directions);
            if (directionsCount == 0) {
                break;
            }
            direction = directions[r.nextInt(directionsCount)];

            proceed(direction, x, y);
        }
    }

    /**
     * Proceeds the grid to the given direction.
     * @param direction Direction in which to proceed.
     * @param x Current x-coordinate
     * @param y Current y-coordinate 
     */
    public void proceed(String direction, int x, int y) {
        if (direction == "up") {
            maze[x][y - 1] = ' ';
            maze[x][y - 2] = ' ';
            y = y - 2;
        }
        if (direction == "down") {
            maze[x][y + 1] = ' ';
            maze[x][y + 2] = ' ';
            y = y + 2;
        }
        if (direction == "left") {
            maze[x - 1][y] = ' ';
            maze[x - 2][y] = ' ';
            x = x - 2;
        }
        if (direction == "right") {
            maze[x + 1][y] = ' ';
            maze[x + 2][y] = ' ';
            x = x + 2;
        }
        generateMaze(x, y);
    }

    /**
     * Checks for possible directions to proceed and returns the count.
     * @param x Current x-coordinate
     * @param y Current y-coordinate 
     * @param directions Array of possible directions.
     * @return Return count of possible directions.
     */
    public int possibleDirections(int x, int y, String[] directions) {
        int directionsCount = 0;
        if (x - 2 > 0 && maze[x - 2][y] != ' ' && maze[x - 2][y - 1] != ' ' && maze[x - 2][y + 1] != ' ' && maze[x - 3][y] != ' ') {
            directions[directionsCount] = "left";
            directionsCount++;
        }
        if (y + 2 < maze[0].length - 1 && maze[x][y + 2] != ' ' && maze[x + 1][y + 2] != ' ' && maze[x - 1][y + 2] != ' ' && maze[x][y + 3] != ' ') {
            directions[directionsCount] = "down";
            directionsCount++;
        }
        if (y - 2 > 0 && maze[x][y - 2] != ' ' && maze[x + 1][y - 2] != ' ' && maze[x - 1][y - 2] != ' ' && maze[x][y - 3] != ' ') {
            directions[directionsCount] = "up";
            directionsCount++;
        }
        if (x + 2 < maze.length - 1 && maze[x + 2][y] != ' ' && maze[x + 2][y + 1] != ' ' && maze[x + 2][y - 1] != ' ' && maze[x + 3][y] != ' ') {
            directions[directionsCount] = "right";
            directionsCount++;
        }
        return directionsCount;
    }

    public char[][] getMaze() {
        return maze;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

}
