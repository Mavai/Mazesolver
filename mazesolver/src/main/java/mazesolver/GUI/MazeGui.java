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
    public boolean mouseDown;
    public String clickedCellType;
    private JPanel mazeGrid;
    private JButton stop;
    private JSlider heightSlider;
    private JSlider widthSlider;
    private JSlider timerSpeed;
    private JCheckBox simulationCheck;
    private JLabel distance;
    private long elapsedTime;
    private JLabel time;

    public MazeGui(Maze maze) {
        super("Mazesolver");

        this.maze = maze;
        this.grid = new JLabel[maze.getGrid().length][maze.getGrid()[0].length];
        this.currentShortestPath = new MyArrayList<>();
        this.visitedNodes = new MyQueue<>();
        createComponents(this.getContentPane());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();
        setLocationRelativeTo(this);
        setVisible(true);
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
        menu.add(Box.createRigidArea(new Dimension(0, grid[0].length * 5 / 10)));
        createSliders(menu);
        JButton newMazeButton = createNewMazeButton();
        menu.add(newMazeButton);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        JButton solveAstarButton = createSolveAstarButton();
        menu.add(solveAstarButton);
        menu.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton solveIDAButton = createSolveIDAButton();
        menu.add(solveIDAButton);
        menu.add(Box.createRigidArea(new Dimension(0, 5)));
        stop = createStopButton();
        menu.add(stop);
        menu.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton clear = createClearButton();
        menu.add(clear);
        menu.add(Box.createRigidArea(new Dimension(0, 5)));
        createSimulationCheckbox();
        menu.add(simulationCheck);
        menu.add(Box.createRigidArea(new Dimension(0, 5)));
        createDistanceLabel(menu);
        menu.add(Box.createRigidArea(new Dimension(0, 5)));
        createElapsedTimeLabel(menu);
        menu.add(Box.createRigidArea(new Dimension(170, 0)));
        return menu;
    }

    private void createDistanceLabel(JPanel menu) {
        distance = new JLabel("Polun pituus: ");
        distance.setAlignmentX(CENTER_ALIGNMENT);
        menu.add(distance);
    }

    private void createElapsedTimeLabel(JPanel menu) {
        time = new JLabel("Kulunut aika: ");
        time.setAlignmentX(CENTER_ALIGNMENT);
        menu.add(time);
    }

    private void createSimulationCheckbox() {
        simulationCheck = new JCheckBox("Simuloi");
        simulationCheck.setSelected(true);
        simulationCheck.setAlignmentX(CENTER_ALIGNMENT);
    }

    private JButton createClearButton() {
        JButton clear = new JButton("Nollaa");
        clear.addActionListener(new MenuButtonListener(clear, this));
        clear.setAlignmentX(CENTER_ALIGNMENT);
        return clear;
    }

    private JSlider createSliders(JPanel menu) {
        timerSpeed = new JSlider(0, 200, 10);
        JLabel heightLabel = new JLabel("Korkeus: " + maze.getGrid()[0].length);
        JLabel widthLabel = new JLabel("Leveys: " + maze.getGrid().length);
        JLabel speedLabel = new JLabel("Nopeus: 10");
        heightSlider = new JSlider(JSlider.HORIZONTAL, 20, (Toolkit.getDefaultToolkit().getScreenSize().height - 90) / 15 - 1, maze.getGrid()[0].length);
        heightSlider.setName("height");
        heightSlider.addChangeListener(new SliderListener(heightLabel, widthLabel, speedLabel));
        menu.add(heightSlider);
        menu.add(heightLabel);
        menu.add(Box.createRigidArea(new Dimension(0, 5)));
        widthSlider = new JSlider(JSlider.HORIZONTAL, 20, (Toolkit.getDefaultToolkit().getScreenSize().width - 200) / 15 - 1, maze.getGrid().length);
        widthSlider.setName("width");
        widthSlider.addChangeListener(new SliderListener(heightLabel, widthLabel, speedLabel));
        menu.add(widthSlider);
        menu.add(widthLabel);
        menu.add(Box.createRigidArea(new Dimension(0, 5)));
        timerSpeed.setName("speed");
        timerSpeed.setMajorTickSpacing(10);
        timerSpeed.setMinorTickSpacing(5);
        timerSpeed.setPaintTicks(true);
        timerSpeed.setSnapToTicks(true);
        timerSpeed.addChangeListener(new SliderListener(speedLabel, widthLabel, speedLabel));
        menu.add(timerSpeed);
        menu.add(speedLabel);
        menu.add(Box.createRigidArea(new Dimension(0, 5)));
        return heightSlider;
    }

    private JButton createStopButton() {
        stop = new JButton("Pysäytä");
        stop.setAlignmentX(CENTER_ALIGNMENT);
        stop.addActionListener(new MenuButtonListener(stop, this));
        return stop;
    }

    private JButton createSolveIDAButton() {
        JButton solveIDAButton = new JButton("Ratkaise käyttäen IDA*");
        solveIDAButton.setAlignmentX(CENTER_ALIGNMENT);
        solveIDAButton.addActionListener(new MenuButtonListener(solveIDAButton, this));
        return solveIDAButton;
    }

    private JButton createSolveAstarButton() {
        JButton solveAstarButton = new JButton("Ratkaise käyttäen A*");
        solveAstarButton.setAlignmentX(CENTER_ALIGNMENT);
        solveAstarButton.addActionListener(new MenuButtonListener(solveAstarButton, this));
        return solveAstarButton;
    }

    private JButton createNewMazeButton() {
        JButton newMazeButton = new JButton("Uusi labyrintti");
        newMazeButton.setAlignmentX(CENTER_ALIGNMENT);
        newMazeButton.addActionListener(new MenuButtonListener(newMazeButton, this));
        return newMazeButton;
    }

    public JPanel createMaze() {
        JPanel ruudukko = new JPanel(new GridLayout(maze.getGrid()[0].length, maze.getGrid().length));
        for (int i = 0; i < maze.getGrid()[0].length; i++) {
            for (int j = 0; j < maze.getGrid().length; j++) {
                JLabel cell = new JLabel();
                editCell(cell, j, i);
                cell.addMouseListener(new MazeMouseListener(this, cell, j, i));
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
                JLabel cell = grid[j][i];
                if (maze.getGrid()[j][i] == '@') {
                    cell.setBackground(new Color(204, 122, 0));
                    cell.setName("marked");
                } else {
                    cell.setBackground(null);
                    cell.setName("unmarked");
                }
                markStartAndGoalNodes(j, i, cell);
            }
        }
        mazeGrid.setVisible(true);
    }

    private void markStartAndGoalNodes(int j, int i, JLabel cell) {
        if (j == maze.getStartX() && i == maze.getStartY()) {
            cell.setBackground(Color.BLUE);
        } else if (j == maze.getEndX() && i == maze.getEndY()) {
            cell.setBackground(Color.red);
        }
    }

    public void clearPreviousMarks() {
        mazeGrid.setVisible(false);
        distance.setText("Polun pituus: ");
        time.setText("Kulunut aika: ");
        clearShortestPath();
        clearVisitedNodes();
        mazeGrid.setVisible(true);
        getGoalCell().setBackground(Color.BLUE);
        getEndCell().setBackground(Color.red);
    }

    private void clearShortestPath() {
        for (Node node : currentShortestPath) {
            if (maze.getGrid()[node.getX()][node.getY()] == ' ') {
                grid[node.getX()][node.getY()].setBackground(null);
                grid[node.getX()][node.getY()].setName("unmarked");
            }
        }
    }

    private void clearVisitedNodes() {
        while (!visitedNodes.isEmpty()) {
            Node node = visitedNodes.poll();
            if (maze.getGrid()[node.getX()][node.getY()] == ' ') {
                grid[node.getX()][node.getY()].setBackground(null);
                grid[node.getX()][node.getY()].setName("unmarked");
            }
        }
    }

    public void markVisitedNodes(String algorithm) {
        getGoalCell().setBackground(Color.BLUE);
        getEndCell().setBackground(Color.red);
        if (algorithm.equals("A*")) {
            this.timer = new Timer(timerSpeed.getValue(), new TimerListener(visitedNodes.clone(), this));
        } else if (algorithm.equals("IDA*")) {
            this.timer = new Timer(timerSpeed.getValue(), new TimerListener(visitedNodes.clone(), this));
        }
        timer.start();
    }

    public void markShortestPath() {
        mazeGrid.setVisible(false);
        for (Node node : currentShortestPath) {
            grid[node.getX()][node.getY()].setBackground(Color.green);
            if (node.getX() == maze.getStartX() && node.getY() == maze.getStartY()) {
                grid[node.getX()][node.getY()].setBackground(Color.BLUE);
            } else if (node.getX() == maze.getEndX() && node.getY() == maze.getEndY()) {
                grid[node.getX()][node.getY()].setBackground(Color.red);
            }
        }
        distance.setText("Polun pituus: " + currentShortestPath.size());
        time.setText("Kulunut aika: " + elapsedTime + " ms");
        mazeGrid.setVisible(true);
    }

    private void editCell(JLabel cell, int j, int i) {
        cell.setPreferredSize(new Dimension(15, 15));
        cell.setOpaque(true);
        cell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        if (maze.getGrid()[j][i] == '@') {
            cell.setName("marked");
            cell.setBackground(new Color(204, 122, 0));
        } else {
            markStartAndGoalNodes(j, i, cell);
            cell.setName("unmarked");
        }
    }

    @Override
    public void run() {
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

    public JButton getStop() {
        return stop;
    }

    public JSlider getWidthSlider() {
        return widthSlider;
    }

    public JSlider getHeightSlider() {
        return heightSlider;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public JCheckBox getSimulationCheck() {
        return simulationCheck;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public JLabel getGoalCell() {
        return grid[maze.getStartX()][maze.getStartY()];
    }
    
    public JLabel getEndCell() {
        return grid[maze.getEndX()][maze.getEndY()];
    }

}
