package mazesolver.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import javax.swing.Timer;
import mazesolver.domain.Node;

/**
 *
 * @author Marko Vainio
 */
public class TimerListener implements ActionListener {

    private ArrayDeque<Node> path;
    private MazeGui mainFrame;

    public TimerListener(ArrayDeque<Node> path, MazeGui mainFrame) {
        this.path = path;
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Timer timer = (Timer) e.getSource();
        if (path.isEmpty()) {
            timer.stop();
            mainFrame.markShortestPath();
            return;
        }
        Node node = path.poll();
        mainFrame.getGrid()[node.getX()][node.getY()].setBackground(new Color(128, 255, 128));
    }

}
