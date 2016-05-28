/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.domain;

import java.util.Random;

/**
 *
 * @author Marko Vainio
 */
public final class Maze {

    private final char[][] maze;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public Maze(int width, int height) {
        this.maze = new char[width][height];
        initialize();
        createMiddle();
        createExitpoints();
    }

    public void initialize() {
        for (int i = 0; i < maze[0].length; i++) {
            for (int j = 0; j < maze.length; j++) {
                maze[j][i] = '@';
            }
        }
    }

    public void print() {
        for (int i = 0; i < maze[0].length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[j][i]);
            }
            System.out.println("");
        }
        System.out.println("----------------------------------------");
    }

    public void createMiddle() {
        Random r = new Random();
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

    public void createExitpoints() {
        int x = 0;
        int y = maze[0].length / 2;
        if (maze[x + 1][y] != ' ') {
            y++;
        }
        maze[x][y] = ' ';
        this.startX = x;
        this.startY = y;
        x = maze.length - 1;
        if (maze[x - 1][y] != ' ') {
            y++;
        }
        maze[x][y] = ' ';
        this.endX = x;
        this.endY = y;
    }

    public void generateMaze(int x, int y) {
        Random r = new Random();
        String[] directions = new String[4];
        while (true) {
            int directionsCount = possibleDirections(x, y, directions);
            if (directionsCount == 0) {
                break;
            }
            String direction = directions[r.nextInt(directionsCount)];
            proceed(direction, x, y);
        }
    }

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
            if (x + 3 >= maze.length - 1) {
                directions[directionsCount] = "right";
                directionsCount++;
            } else if (maze[x + 2][y] != ' ') {
                directions[directionsCount] = "right";
                directionsCount++;
            }
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
