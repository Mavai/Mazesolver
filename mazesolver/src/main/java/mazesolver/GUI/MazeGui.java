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
        menu.add(Box.createRigidArea(new Dimension(0, grid[0].length * 18 / 10)));
        createSliders(menu);
        JButton newMazeButton = createNewMazeButton();
        menu.add(newMazeButton);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        JButton solveAstarButton = createSolveAstarButton();
        menu.add(solveAstarButton);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        JButton solveIDAButton = createSolveIDAButton();
        menu.add(solveIDAButton);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        stop = createStopButton();
        menu.add(stop);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        JButton clear = createClearButton();
        menu.add(clear);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        createSimulationCheckbox();
        menu.add(simulationCheck);
        menu.add(Box.createRigidArea(new Dimension(170, 0)));
        return menu;
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
        JLabel heightLabel = new JLabel("Korkeus: 25");
        JLabel widthLabel = new JLabel("Leveys: 25");
        JLabel speedLabel = new JLabel("Nopeus: 100");
        heightSlider = new JSlider(JSlider.HORIZONTAL, 20, (Toolkit.getDefaultToolkit().getScreenSize().height - 90) / 18, 25);
        heightSlider.setName("height");
        heightSlider.addChangeListener(new SliderListener(heightLabel, widthLabel, speedLabel));
        menu.add(heightSlider);
        menu.add(heightLabel);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        widthSlider = new JSlider(JSlider.HORIZONTAL, 20, (Toolkit.getDefaultToolkit().getScreenSize().width - 200) / 18, 25);
        widthSlider.setName("width");
        widthSlider.addChangeListener(new SliderListener(heightLabel, widthLabel, speedLabel));
        menu.add(widthSlider);
        menu.add(widthLabel);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        timerSpeed.setName("speed");
        timerSpeed.setMajorTickSpacing(10);
        timerSpeed.setPaintTicks(true);
        timerSpeed.setSnapToTicks(true);
        timerSpeed.addChangeListener(new SliderListener(speedLabel, widthLabel, speedLabel));
        menu.add(timerSpeed);
        menu.add(speedLabel);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        return heightSlider;
    }

    private JButton createStopButton() {
        JButton stop = new JButton("Pysäytä");
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
                if (maze.getGrid()[j][i] == '@') {
                    grid[j][i].setBackground(new Color(204, 122, 0));
                    grid[j][i].setName("marked");
                } else {
                    grid[j][i].setBackground(null);
                    grid[j][i].setName("unmarked");
                }
            }
        }
        mazeGrid.setVisible(true);
    }

    public void clearPreviousMarks() {
        mazeGrid.setVisible(false);
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
        mazeGrid.setVisible(true);
    }

    public void markVisitedNodes(String algorithm) {
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
        }
        mazeGrid.setVisible(true);
    }

    private void editCell(JLabel cell, int j, int i) {
        cell.setPreferredSize(new Dimension(18, 18));
        cell.setOpaque(true);
        cell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        if (maze.getGrid()[j][i] == '@') {
            cell.setName("marked");
            cell.setBackground(new Color(204, 122, 0));
        } else {
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

}
