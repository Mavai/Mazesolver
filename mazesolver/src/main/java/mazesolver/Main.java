package mazesolver;

import javax.swing.SwingUtilities;
import mazesolver.GUI.MazeGui;
import mazesolver.data_structures.MyQueue;
import mazesolver.domain.Maze;

/**
 *
 * @author markovai
 */
public class Main {

    public static void main(String[] args) {
        Maze maze = new Maze(31, 31);
        MazeGui gui = new MazeGui(maze);
        SwingUtilities.invokeLater(gui);
        MyQueue<Integer> que = new MyQueue<>(2);
    }
}
