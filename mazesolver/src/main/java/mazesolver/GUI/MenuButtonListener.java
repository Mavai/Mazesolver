/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import mazesolver.logic.Astar;

/**
 *
 * @author Marko Vainio
 */
public class MenuButtonListener implements ActionListener{
    private final JButton pressed;
    private final MazeGui mainFrame;

    public MenuButtonListener(JButton pressed, MazeGui mainFrame) {
        this.pressed = pressed;
        this.mainFrame = mainFrame;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pressed.getText().equals("Uusi labyrintti")) {
            mainFrame.reprint();
        }
        if (pressed.getText().equals("Ratkaise käyttäen A*")) {
            Astar astar = new Astar(mainFrame.getMaze());
            astar.solve();
            astar.findShortestPath();
            mainFrame.clearPreviousMarks();
            mainFrame.setVisitedNodes(astar.getVisitedNodes());
            mainFrame.markVisitedNodes();
            mainFrame.setCurrentShortestPath(astar.getShortestPath());
        }
    }
    
}
