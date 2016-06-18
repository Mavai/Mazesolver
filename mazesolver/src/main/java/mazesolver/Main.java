package mazesolver;

import javax.swing.SwingUtilities;
import mazesolver.GUI.MazeGui;
import mazesolver.domain.Maze;

/**
 *
 * @author markovai
 */
public class Main {

    public static void main(String[] args) {
        Maze maze = new Maze(51, 21);
        MazeGui gui = new MazeGui(maze);
        SwingUtilities.invokeLater(gui);

    }
}
