
package mazesolver.domain;

import java.util.Objects;

/**
 * This class provides implementation for Nodes in a grid.
 * @author Marko Vainio
 */
public class Node implements Comparable<Node>{
    private final Integer x;
    private final Integer y;
    private final Integer distance;

    /**
     * Contructor for grid node.
     * @param x Node's x-coordinate.
     * @param y Node's y-coordinate.
     * @param distance Estimated distance from another node.
     */
    public Node(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Integer getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Node o) {
        if (Objects.equals(distance, o.distance)) {
            return o.y.compareTo(y);
        }
        return distance.compareTo(o.distance);
    }
    
}
