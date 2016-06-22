package mazesolver;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import mazesolver.GUI.MazeGui;
import mazesolver.domain.Maze;

/**
 *
 * @author markovai
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        }
        Maze maze = new Maze(25, 25);
        MazeGui gui = new MazeGui(maze);
        SwingUtilities.invokeLater(gui);
    }
}
