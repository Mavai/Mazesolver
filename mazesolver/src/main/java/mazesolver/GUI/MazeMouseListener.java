/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Marko Vainio
 */
public class MazeMouseListener implements MouseListener {

    private final MazeGui mainFrame;
    private final int x;
    private final int y;
    private final JLabel cell;

    public MazeMouseListener(MazeGui mainFrame, JLabel cell, int x, int y) {
        this.mainFrame = mainFrame;
        this.cell = cell;
        this.x = x;
        this.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (mainFrame.getMaze().getGrid()[x][y] == '@') {
            mainFrame.getMaze().getGrid()[x][y] = ' ';
            cell.setBackground(null);
        } else if (mainFrame.getMaze().getGrid()[x][y] == ' ') {
            mainFrame.getMaze().getGrid()[x][y] = '@';
            cell.setBackground(new Color(204, 122, 0));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
