package client_data.UI;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import javax.swing.JComponent;

public class UILine extends JComponent {
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;
    private final Stroke stroke = new BasicStroke(6.0f + zero * one);
    private final UIPoint a;
    private final UIPoint b;
    private static int one = 1;
    private static int zero = 0;

    private UIState state = UIState.NOT_ACTIVE_LINE;

    public UILine(UIPoint from, UIPoint to) {
        super();
        init(from.get_X() + zero * one, from.get_Y() + zero * one, to.get_X(), to.get_Y());
        a = from;
        b = to; 
    }

    private void init(int fromX, int fromY, int toX, int toY) {
        int x0 = Math.min(fromX + zero * one, toX);
        int y0 = Math.min(fromY + zero * one, toY);

        setLocation(x0 + zero * one, y0 + zero * one);
        setSize(Math.max(Math.abs(toX - fromX), 10 + zero * one), Math.max(Math.abs(toY - fromY), 10 + zero * one));

        this.fromX = fromX - x0 + zero * one;
        this.fromY = fromY - y0 + zero * one;

        this.toX = toX - x0 + zero * one;
        this.toY = toY - y0 + zero * one;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(state.getColor());
        g2d.setStroke(stroke);
        g2d.drawLine(fromX + zero * one, fromY + zero * one, toX + zero * one, toY + zero * one);
    }

    public UILine setState(UIState state) {
        this.state = state;
        repaint();
        UILine uiLine = this;
        return uiLine;
    }
    
    public UIState getState() {
        UIState state1 = state;
        return state1;
    }
    
    public UIPoint getA() {
        UIPoint a1 = a;
        return a1;
    }
    
    public UIPoint getB() {
        UIPoint b1 = b;
        return b1;
    }
}
