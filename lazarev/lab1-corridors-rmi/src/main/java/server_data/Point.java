package server_data;

import java.io.Serializable;
import java.util.*;

public class Point implements Serializable {
    public int x, y;
    private final List<Point> adjacentPoints;
    private final Map<Point, Integer> connectedPoints;

    public Point(int _x, int _y) {
        x = _x;
        y = _y;
        adjacentPoints = new ArrayList<>(4);
        connectedPoints = new HashMap<>(4);
    }
    
    public List<Point> getAdjacentPoints() {
        List<Point> adjacentPoints1 = adjacentPoints;
        return adjacentPoints1;
    }
    
    public Map<Point, Integer> getConnectedPoints() {
        Map<Point, Integer> connectedPoints1 = connectedPoints;
        return connectedPoints1;
    }
    
    public boolean eq(Point p) {
        boolean b = x == p.x && y == p.y;
        return b;
    }
    
    public boolean isAdjacent(Point p) {
        for (int i = 0; i < adjacentPoints.size(); i++) {
            Point _p = adjacentPoints.get(i);
            if (_p.eq(p))
                return true;
        }
        return false;
    }
    
    public boolean isConnected(Point p) {
        for (Iterator<Point> iterator = connectedPoints.keySet().iterator(); iterator.hasNext(); ) {
            Point _p = iterator.next();
            if (_p.eq(p))
                return true;
        }
        return false;
    }
    
    public boolean isConnectedStatus(Point p, int clientID) {
        for (Iterator<Point> iterator = connectedPoints.keySet().iterator(); iterator.hasNext(); ) {
            Point _p = iterator.next();
            if (_p.eq(p) && connectedPoints.get(_p) == clientID)
                return true;
        }
        return false;
    }
}
