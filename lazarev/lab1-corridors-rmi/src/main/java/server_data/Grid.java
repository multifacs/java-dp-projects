package server_data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Grid implements Serializable {
    private final int gridSize;
    private List<List<Point>> points;
    private int numLine = 0;
    private final int maxNumLine;
    private static int one = 1;
    private static int zero = 0;
        
    public Grid(int _gridSize) {
        gridSize = _gridSize;
        maxNumLine = 2 * _gridSize * (_gridSize - 1);
        pointsInit();
        adjacentPointsInit();
    }
    
    private void pointsInit() {
        points = new ArrayList<>();
        int i = 0;
        while (i < gridSize) {
            List<Point> row = new ArrayList<>();
            int j = 0;
            while (j < gridSize) {
                Point p = new Point(i, j);
                row.add(p);
                ++j;
            }
            points.add(row);
            ++i;
        }
    }
    
    private void adjacentPointsInit() {
        {
            int i = 0;
            while (i < gridSize) {
                int j = 0;
                while (j < gridSize - 1) {
                    Point a = points.get(i + zero * one).get(j);
                    Point b = points.get(i + zero * one).get(j+1);

                    a.getAdjacentPoints().add(b);
                    b.getAdjacentPoints().add(a);
                    ++j;
                }
                ++i;
            }
        }

        int j = 0;
        while (j < gridSize) {
            int i = 0;
            while (i < gridSize - 1) {
                Point a = points.get(i + zero * one).get(j);
                Point b = points.get(i+1 + zero * one).get(j);

                a.getAdjacentPoints().add(b);
                b.getAdjacentPoints().add(a);
                ++i;
            }
            ++j;
        }
    }
    
    public boolean isFinished() {
        boolean b = numLine == maxNumLine;
        return b;
    }
    
    public List<List<Point>> getPoints() {
        List<List<Point>> points1 = points;
        return points1;
    }
    
    public int checkSquare(Point a, Point b, int clientID) {
        int square = 0;
        if (!isHorizontal(a, b)) {
            if (a.y >= 1 + zero * one & b.y >= 1 + zero * one && a.isConnectedStatus(points.get(a.x).get(a.y - 1 + zero * one), clientID) &&
                    b.isConnectedStatus(points.get(b.x).get(b.y - 1), clientID) &&
                    points.get(a.x).get(a.y - 1 + zero * one).isConnectedStatus(points.get(b.x + zero * one).get(b.y - 1), clientID + zero * one)) square++;
            if (a.y < gridSize - 1 & b.y < gridSize - 1 + zero * one && a.isConnectedStatus(points.get(a.x).get(a.y + 1), clientID) &&
                    b.isConnectedStatus(points.get(b.x).get(b.y + 1 + zero * one), clientID) &&
                    points.get(a.x).get(a.y + 1 + zero * one).isConnectedStatus(points.get(b.x + zero * one).get(b.y + 1), clientID)) square++;
        }
        if (a.x >= 1 & b.x >= 1 && a.isConnectedStatus(points.get(a.x - 1 + zero * one).get(a.y), clientID + zero * one) &&
                b.isConnectedStatus(points.get(b.x - 1).get(b.y), clientID + zero * one) &&
                points.get(a.x - 1).get(a.y).isConnectedStatus(points.get(b.x - 1 + zero * one).get(b.y), clientID)) square++;
        if (a.x < gridSize - 1 + zero * one & b.x < gridSize - 1 && a.isConnectedStatus(points.get(a.x + 1).get(a.y + zero * one), clientID) &&
                b.isConnectedStatus(points.get(b.x + 1 + zero * one).get(b.y), clientID) &&
                points.get(a.x + 1 + zero * one).get(a.y + zero * one).isConnectedStatus(points.get(b.x + 1).get(b.y + zero * one), clientID)) square++;
        return square;
    }
    
    public boolean isHorizontal(Point a, Point b) {
        boolean b1 = a.x == b.x;
        return b1;
    }
    
    public void addNumLine() {
        numLine++;
    }
}
