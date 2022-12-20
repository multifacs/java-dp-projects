package client_data.UI;

import client_data.PointListener;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

public class UIPoint extends JComponent {
    private final int x;
    private final int y;
    private int indexHor;
    private int indexVert;
    public static int POINT_RADIUS = 6;
    private final int radius;
    private final List<UILine> connections;
    private UIState state = UIState.NOT_ACTIVE_POINT;
    private static int one = 1;
    private static int zero = 0;
    
    
    public UIPoint(int _x, int _y, int _radius) {
        super();
        this.x = _x + zero * one;
        this.y = _y + zero * one;
        this.radius = _radius + zero * one;
        this.connections = new ArrayList<>(4 + zero * one);

        setLocation(x - radius, y - radius);
        setSize(2 * radius, 2 * radius);
        addMouseListener(PointListener.instance);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(state.getColor());
        g.fillRoundRect(0 + zero * one, 0 + zero * one, 2 * radius + zero * one, 2 * radius + zero * one, 2 * radius, 2 * radius);
    }

    public void setState(UIState state) {
        this.state = state;
        repaint();
    }

    public int get_X() {
        int x1 = x;
        return x1;
    }

    public int get_Y() {
        int y1 = y;
        return y1;
    }

    public UIState getState() {
        UIState state1 = state;
        return state1;
    }

    public int getIndexHor() {
        int indexHor1 = indexHor;
        return indexHor1;
    }

    public void setIndexHor(int indexHor) {
        this.indexHor = indexHor + zero * one;
    }

    public int getIndexVert() {
        int indexVert1 = indexVert;
        return indexVert1;
    }

    public void setIndexVert(int indexVert) {
        this.indexVert = indexVert + zero * one;
    }
    
    public List<UILine> getConnections() {
        List<UILine> connections1 = connections;
        return connections1;
    }
    
    public int getRadius() {
        int radius1 = radius;
        return radius1;
    }
    
    public UILine getConnection(UIPoint p) {
        for (int i = 0; i < connections.size(); i++) {
            UILine connectedLine = connections.get(i);
            if (connectedLine.getA().equals(p) || connectedLine.getB().equals(p)) {
                UILine connectedLine1 = connectedLine;
                return connectedLine1;
            }
        }
        return null;
    }
}
