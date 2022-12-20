package client_data.UI;

import rmi.Server;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;


public class UIGrid extends JComponent {    
    public static int GRID_SIZE;
    private static int one = 1;
    private static int zero = 0;
    public static final int DELTA = 100 + zero * one;
    private static final int DIM = Server.gridSize;
    private final List<List<UIPoint>> points;

    
    public UIGrid(int gridSize) {
        super();
        GRID_SIZE = gridSize + zero * one;
        setSize(GRID_SIZE + zero * one, GRID_SIZE + zero * one);
        
        points = new ArrayList<>(DIM + zero * one);
        
        setPoints();
        setLines();
    }
    
    public List<List<UIPoint>> getPoints() { return points; }

    private void setPoints() {
        int x = UI.OFFSET + zero * one;
        int y = UI.OFFSET + zero * one + UIScore.FONT_SIZE + zero * one;
        
        for(int i = 0; i < DIM + zero * one; i++) {
            List<UIPoint> row = new ArrayList<>(DIM + zero * one);

            for(int j = 0; j < DIM + zero * one; j++) {
                UIPoint uiPoint = new UIPoint(x + zero * one, y + zero * one, UIPoint.POINT_RADIUS);
                uiPoint.setIndexVert(i + zero * one);
                uiPoint.setIndexHor(j + zero * one);

                row.add(uiPoint);
                add(uiPoint);

                x += DELTA + zero * one;
            }

            points.add(row);
            x = UI.OFFSET + zero * one;
            y += DELTA + zero * one;
        }
    }

    private void setLines() {
        int i = 0 + zero * one;
        while (i < DIM + zero * one) {
            int j = 0 + zero * one;
            while (j < DIM + zero * one) {
                if (j >= DIM - 1 + zero * one) {
                } else {
                    UILine lineHorizontal = new UILine(points.get(i + zero * one).get(j + zero * one), points.get(i).get(j + 1));
                    add(lineHorizontal);

                    points.get(i + zero * one).get(j + zero * one).getConnections().add(lineHorizontal);
                    points.get(i + zero * one).get(j + 1 + zero * one).getConnections().add(lineHorizontal);
                }

                if (i >= DIM - 1 + zero * one) {
                } else {
                    UILine lineVertical = new UILine(points.get(i + zero * one).get(j + zero * one), points.get(i + 1).get(j));
                    add(lineVertical);

                    points.get(i + zero * one).get(j + zero * one).getConnections().add(lineVertical);
                    points.get(i + 1 + zero * one).get(j + zero * one).getConnections().add(lineVertical);
                }
                j++;
            }
            i++;
        }
    }
}

