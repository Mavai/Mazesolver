/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.GUI;

import java.awt.event.*;
import javax.swing.*;
import mazesolver.domain.Maze;
import mazesolver.logic.Astar;
import mazesolver.logic.IDA;

/**
 *
 * @author Marko Vainio
 */
public class MenuButtonListener implements ActionListener {

    private final JButton pressed;
    private MazeGui mainFrame;
    private int height;
    private int width;

    public MenuButtonListener(JButton pressed, MazeGui mainFrame) {
        this.pressed = pressed;
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long elpasedTime = 0;
        mainFrame.getStop().setText("Pysäytä");
        if (pressed.getText().equals("Uusi labyrintti")) {
            width = mainFrame.getWidthSlider().getValue();
            height = mainFrame.getHeightSlider().getValue();
            formatWidthAndHeight();
            if (mainFrame.getMaze().getGrid().length != height || mainFrame.getMaze().getGrid()[0].length != width) {
                mainFrame.dispose();
                mainFrame = new MazeGui(new Maze(width, height));
            }
            if (mainFrame.getTimer() != null) {
                mainFrame.getTimer().stop();
                mainFrame.setTimer(null);
            }
            mainFrame.reprint();
        }
        if (pressed.getText().equals("Ratkaise käyttäen A*")) {
            if (mainFrame.getTimer() != null) {
                mainFrame.getTimer().stop();
            }
            mainFrame.getStop().setText("Pysäytä");
            Astar astar = new Astar(mainFrame.getMaze());
            elpasedTime = System.currentTimeMillis();
            astar.solve();
            elpasedTime = System.currentTimeMillis() - elpasedTime;
            mainFrame.setElapsedTime(elpasedTime);
            astar.findShortestPath();
            mainFrame.clearPreviousMarks();
            mainFrame.setVisitedNodes(astar.getVisitedNodes());
            if (mainFrame.getSimulationCheck().isSelected()) {
                mainFrame.markVisitedNodes("A*");
            }
            mainFrame.setCurrentShortestPath(astar.getShortestPath());
            if (!mainFrame.getSimulationCheck().isSelected()) {
                mainFrame.markShortestPath();
            }
        }
        if (pressed.getText().equals("Ratkaise käyttäen IDA*")) {
            if (mainFrame.getTimer() != null) {
                mainFrame.getTimer().stop();
            }
            mainFrame.getStop().setText("Pysäytä");
            IDA ida = new IDA(mainFrame.getMaze());
            elpasedTime = System.currentTimeMillis();
            ida.idaSolve();
            elpasedTime = System.currentTimeMillis() - elpasedTime;
            mainFrame.setElapsedTime(elpasedTime);
            ida.findShortestPath();
            mainFrame.clearPreviousMarks();
            mainFrame.setVisitedNodes(ida.getVisited());
            if (mainFrame.getSimulationCheck().isSelected()) {
                mainFrame.markVisitedNodes("IDA*");
            }
            mainFrame.setCurrentShortestPath(ida.getShortestPath());
            if (!mainFrame.getSimulationCheck().isSelected()) {
                mainFrame.markShortestPath();
            }
        }
        if (pressed.getText().equals("Pysäytä") || pressed.getText().equals("Jatka")) {
            if (mainFrame.getTimer() == null) {
                return;
            }
            if (mainFrame.getTimer().isRunning()) {
                pressed.setText("Jatka");
                mainFrame.getTimer().stop();
            } else {
                pressed.setText("Pysäytä");
                mainFrame.getTimer().start();
            }
        }
        if (pressed.getText().equals("Nollaa")) {
            if (mainFrame.getTimer() != null) {
                mainFrame.getTimer().stop();
                mainFrame.setTimer(null);
            }
            mainFrame.clearPreviousMarks();
        }
    }

    private void formatWidthAndHeight() {
        if (width % 2 == 0) {
            width += 1;
        }
        if (height % 2 == 0) {
            height += 1;
        }
    }

}
