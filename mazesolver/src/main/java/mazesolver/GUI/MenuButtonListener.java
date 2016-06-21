/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.GUI;

import java.awt.event.*;
import java.util.Optional;
import javax.swing.*;
import mazesolver.logic.Astar;
import mazesolver.logic.IDA;

/**
 *
 * @author Marko Vainio
 */
public class MenuButtonListener implements ActionListener {

    private final JButton pressed;
    private final MazeGui mainFrame;

    public MenuButtonListener(JButton pressed, MazeGui mainFrame) {
        this.pressed = pressed;
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pressed.getText().equals("Uusi labyrintti")) {
            if (mainFrame.getTimer() != null) {
                mainFrame.getTimer().stop();
            }
            mainFrame.reprint();
        }
        if (pressed.getText().equals("Ratkaise käyttäen A*")) {
            if (mainFrame.getTimer() != null) {
                mainFrame.getTimer().stop();
            }
            Astar astar = new Astar(mainFrame.getMaze());
            astar.solve();
            astar.findShortestPath();
            mainFrame.clearPreviousMarks();
            mainFrame.setVisitedNodes(astar.getVisitedNodes());
            mainFrame.markVisitedNodes("A*");
            mainFrame.setCurrentShortestPath(astar.getShortestPath());
        }
        if (pressed.getText().equals("Ratkaise käyttäen IDA*")) {
            if (mainFrame.getTimer() != null) {
                mainFrame.getTimer().stop();
            }
            IDA ida = new IDA(mainFrame.getMaze());
            ida.idaSolve();
            ida.findShortestPath();
            mainFrame.clearPreviousMarks();
            mainFrame.setVisitedNodes(ida.getVisited());
            mainFrame.markVisitedNodes("IDA*");
            mainFrame.setCurrentShortestPath(ida.getShortestPath());
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
    }

}
