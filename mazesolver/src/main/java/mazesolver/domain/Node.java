
package mazesolver.domain;

/**
 *
 * @author Marko Vainio
 */
public class Node implements Comparable<Node>{
    private final int x;
    private final int y;
    private final Integer distance;

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
        return distance.compareTo(o.distance);
    }
    
}
