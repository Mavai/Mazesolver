/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.GUI;

import java.awt.*;
import javax.swing.*;
import mazesolver.data_structures.*;
import mazesolver.domain.Maze;
import mazesolver.domain.Node;

/**
 *
 * @author Marko Vainio
 */
public class MazeGui extends JFrame implements Runnable {

    private Maze maze;
    private final JLabel[][] grid;
    private MyArrayList<Node> currentShortestPath;
    private MyQueue<Node> visitedNodes;
    private Timer timer;
    public boolean mousDown;
    public String status;
    private JPanel mazeGrid;

    public MazeGui(Maze maze) {
        super("Mazesolver");

        this.maze = maze;
        this.grid = new JLabel[maze.getGrid().length][maze.getGrid()[0].length];
        this.currentShortestPath = new MyArrayList<>();
        this.visitedNodes = new MyQueue<>();
    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        mazeGrid = createMaze();
        container.add(mazeGrid, BorderLayout.CENTER);
        JPanel menu = createMenu();

        container.add(menu, BorderLayout.EAST);

    }

    public JPanel createMenu() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
        JButton newMazeButton = new JButton("Uusi labyrintti");
        newMazeButton.setAlignmentX(CENTER_ALIGNMENT);
        newMazeButton.addActionListener(new MenuButtonListener(newMazeButton, this));
        menu.add(Box.createRigidArea(new Dimension(0, maze.getGrid()[0].length * 15 / 3)));
        menu.add(newMazeButton);
        menu.add(Box.createRigidArea(new Dimension(0, 20)));
        JButton solveAstarButton = new JButton("Ratkaise käyttäen A*");
        solveAstarButton.setAlignmentX(CENTER_ALIGNMENT);
        solveAstarButton.addActionListener(new MenuButtonListener(solveAstarButton, this));
        menu.add(solveAstarButton);
        menu.add(Box.createRigidArea(new Dimension(0, 20)));
        JButton solveIDAButton = new JButton("Ratkaise käyttäen IDA*");
        solveIDAButton.setAlignmentX(CENTER_ALIGNMENT);
        solveIDAButton.addActionListener(new MenuButtonListener(solveIDAButton, this));
        menu.add(solveIDAButton);
        menu.add(Box.createRigidArea(new Dimension(0, 20)));
        JButton stop = new JButton("Pysäytä");
        stop.setAlignmentX(CENTER_ALIGNMENT);
        stop.addActionListener(new MenuButtonListener(stop, this));
        menu.add(stop);
        menu.add(Box.createRigidArea(new Dimension(170, 0)));
        return menu;
    }

    public JPanel createMaze() {
        JPanel ruudukko = new JPanel(new GridLayout(maze.getGrid()[0].length, maze.getGrid().length));
        for (int i = 0; i < maze.getGrid()[0].length; i++) {
            for (int j = 0; j < maze.getGrid().length; j++) {
                JLabel cell = new JLabel();
                editCell(cell, j, i);
                cell.addMouseListener(new MazeMouseListener(this, cell, j, i));
                cell.setName("unmarked");
                grid[j][i] = cell;
                ruudukko.add(cell);
            }
        }
        return ruudukko;
    }

    public void reprint() {
        mazeGrid.setVisible(false);
        clearPreviousMarks();
        currentShortestPath = new MyArrayList<>();
        visitedNodes = new MyQueue<>();
        this.maze = new Maze(maze.getGrid().length, maze.getGrid()[0].length);
        for (int i = 0; i < maze.getGrid()[0].length; i++) {
            for (int j = 0; j < maze.getGrid().length; j++) {
                if (maze.getGrid()[j][i] == '@') {
                    grid[j][i].setBackground(new Color(204, 122, 0));
                } else {
                    grid[j][i].setBackground(null);
                }
            }
        }
        mazeGrid.setVisible(true);
    }

    public void clearPreviousMarks() {
        for (Node node : currentShortestPath) {
            if (maze.getGrid()[node.getX()][node.getY()] == ' ') {
                grid[node.getX()][node.getY()].setBackground(null);
                grid[node.getX()][node.getY()].setName("unmarked");
            }
        }
        while (!visitedNodes.isEmpty()) {
            Node node = visitedNodes.poll();
            if (maze.getGrid()[node.getX()][node.getY()] == ' ') {
                grid[node.getX()][node.getY()].setBackground(null);
                grid[node.getX()][node.getY()].setName("unmarked");
            }
        }
    }

    public void markVisitedNodes(String algorithm) {
        if (algorithm.equals("A*")) {
            this.timer = new Timer(10, new TimerListener(visitedNodes.clone(), this));
        } else if (algorithm.equals("IDA*")) {
            this.timer = new Timer(1, new TimerListener(visitedNodes.clone(), this));
        }
        timer.start();
    }

    public void markShortestPath() {
        for (Node node : currentShortestPath) {
            grid[node.getX()][node.getY()].setBackground(Color.green);
        }
    }

    private void editCell(JLabel cell, int j, int i) {
        cell.setPreferredSize(new Dimension(20, 20));
        cell.setOpaque(true);
        cell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        if (maze.getGrid()[j][i] == '@') {
            cell.setName("marked");
            cell.setBackground(new Color(204, 122, 0));
        }
    }

    @Override
    public void run() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(this.getContentPane());
        pack();
        setLocationRelativeTo(this);
        setVisible(true);
    }

    public Maze getMaze() {
        return maze;
    }

    public void setVisitedNodes(MyQueue<Node> visitedNodes) {
        this.visitedNodes = visitedNodes;
    }

    public JLabel[][] getGrid() {
        return grid;
    }

    public void setCurrentShortestPath(MyArrayList<Node> currentShortestPath) {
        this.currentShortestPath = currentShortestPath;
    }

    public Timer getTimer() {
        return timer;
    }

}
