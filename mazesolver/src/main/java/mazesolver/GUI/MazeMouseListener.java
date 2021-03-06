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
    }

    private void markCell() {
        if (mainFrame.getMaze().getGrid()[x][y] == '@') {
            mainFrame.getMaze().getGrid()[x][y] = ' ';
            cell.setBackground(null);
            cell.setName("unmarked");
        } else if (mainFrame.getMaze().getGrid()[x][y] == ' ') {
            mainFrame.getMaze().getGrid()[x][y] = '@';
            cell.setBackground(new Color(204, 122, 0));
            cell.setName("marked");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mainFrame.clickedCellType = cell.getName();
        mainFrame.mouseDown = true;
        if (e.isShiftDown()) {
            markGoalAndEnd(e);
        } else {
            markCell();
        }

    }

    private void markGoalAndEnd(MouseEvent e) {
        if (e.getButton() == 1 && mainFrame.getMaze().getGrid()[x][y] == ' ') {
            if (mainFrame.getMaze().getEndX() != x || mainFrame.getMaze().getEndY() != y) {
                mainFrame.getGoalCell().setBackground(null);
                mainFrame.getMaze().setStartCell(x, y);
                cell.setBackground(Color.BLUE);
            }

        } else if (e.getButton() == 3 && mainFrame.getMaze().getGrid()[x][y] == ' ' && cell.getName().equals("unmarked")) {
            if (mainFrame.getMaze().getStartX() != x || mainFrame.getMaze().getStartY() != y) {
                mainFrame.getEndCell().setBackground(null);
                mainFrame.getMaze().setGoalCell(x, y);
                cell.setBackground(Color.red);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mainFrame.mouseDown = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (mainFrame.mouseDown) {
            if (mainFrame.clickedCellType.equals("unmarked")) {
                if (mainFrame.getMaze().getGrid()[x][y] == ' ') {
                    mainFrame.getMaze().getGrid()[x][y] = '@';
                    cell.setBackground(new Color(204, 122, 0));
                    cell.setName("marked");
                }
            } else if (mainFrame.clickedCellType.equals("marked")) {
                if (mainFrame.getMaze().getGrid()[x][y] == '@') {
                    mainFrame.getMaze().getGrid()[x][y] = ' ';
                    cell.setBackground(null);
                    cell.setName("unmarked");
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
