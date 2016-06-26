package mazesolver.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.Timer;
import mazesolver.data_structures.MyQueue;
import mazesolver.domain.Node;

/**
 *
 * @author Marko Vainio
 */
public class TimerListener implements ActionListener {

    private final MyQueue<Node> path;
    private final MazeGui mainFrame;

    public TimerListener(MyQueue<Node> path, MazeGui mainFrame) {
        this.path = path;
        this.mainFrame = mainFrame;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Node node;
        Timer timer = (Timer) e.getSource();
        if (path.isEmpty()) {
            timer.stop();
            mainFrame.markShortestPath();
            return;
        }
        node = path.poll();
        JLabel cell = mainFrame.getGrid()[node.getX()][node.getY()];
        if (cell.getName().equals("") || mainFrame.getGrid()[node.getX()][node.getY()].getName().equals("unmarked")) {
            cell.setBackground(new Color(128, 255, 128));
            cell.setName("marked");
        } else {
            cell.setBackground(null);
            cell.setName("unmarked");
        }
        if (node.getX() == mainFrame.getMaze().getStartX() && node.getY() == mainFrame.getMaze().getStartY()) {
            cell.setBackground(Color.BLUE);
        } else if (node.getX() == mainFrame.getMaze().getEndX() && node.getY() == mainFrame.getMaze().getEndY()) {
            cell.setBackground(Color.red);
        }
        mainFrame.getToolkit().sync();
    }
    
}
