package mazesolver.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import mazesolver.data_structures.MyQueue;
import mazesolver.domain.Node;

/**
 *
 * @author Marko Vainio
 */
public class TimerListener implements ActionListener {

    private MyQueue<Node> path;
    private MazeGui mainFrame;

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
        if (mainFrame.getGrid()[node.getX()][node.getY()].getName().equals("") || mainFrame.getGrid()[node.getX()][node.getY()].getName().equals("unmarked")) {
            mainFrame.getGrid()[node.getX()][node.getY()].setBackground(new Color(128, 255, 128));
            mainFrame.getGrid()[node.getX()][node.getY()].setName("marked");
        } else {
            mainFrame.getGrid()[node.getX()][node.getY()].setBackground(null);
            mainFrame.getGrid()[node.getX()][node.getY()].setName("unmarked");
        }

    }

}
