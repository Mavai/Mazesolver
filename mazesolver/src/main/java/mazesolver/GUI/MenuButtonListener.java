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
        mainFrame.getStop().setText("PysÃ¤ytÃ¤");
        if (pressed.getText().equals("Uusi labyrintti")) {
            newMaze();
        }
        if (pressed.getText().equals("Ratkaise kÃ¤yttÃ¤en A*")) {
            solveAstar();
        }
        if (pressed.getText().equals("Ratkaise kÃ¤yttÃ¤en IDA*")) {
            solveIDA();
        }
        if (pressed.getText().equals("PysÃ¤ytÃ¤") || pressed.getText().equals("Jatka")) {
            if (stopOrContinue()) return;
        }
        if (pressed.getText().equals("Nollaa")) {
            reset();
        }
    }

    private void reset() {
        if (mainFrame.getTimer() != null) {
            mainFrame.getTimer().stop();
            mainFrame.setTimer(null);
        }
        mainFrame.clearPreviousMarks();
    }

    private boolean stopOrContinue() {
        if (mainFrame.getTimer() == null) {
            return true;
        }
        if (mainFrame.getTimer().isRunning()) {
            pressed.setText("Jatka");
            mainFrame.getTimer().stop();
        } else {
            pressed.setText("PysÃ¤ytÃ¤");
            mainFrame.getTimer().start();
        }
        return false;
    }

    private void solveIDA() {
        long elpasedTime;
        stopTimer();
        mainFrame.getStop().setText("PysÃ¤ytÃ¤");
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
    
    private void solveAstar() {
        long elpasedTime;
        stopTimer();
        mainFrame.getStop().setText("PysÃ¤ytÃ¤");
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
    
    private void newMaze() {
        width = mainFrame.getWidthSlider().getValue();
        height = mainFrame.getHeightSlider().getValue();
        formatWidthAndHeight();
        if (mainFrame.getMaze().getGrid()[0].length != height || mainFrame.getMaze().getGrid().length != width) {
            mainFrame.dispose();
            mainFrame = new MazeGui(new Maze(width, height));
        }
        if (mainFrame.getTimer() != null) {
            mainFrame.getTimer().stop();
            mainFrame.setTimer(null);
        }
        mainFrame.reprint();
    }

    private void stopTimer() {
        if (mainFrame.getTimer() != null) {
            mainFrame.getTimer().stop();
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
