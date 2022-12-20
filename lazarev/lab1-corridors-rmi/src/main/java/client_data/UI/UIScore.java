package client_data.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;

public class UIScore extends JComponent {
    private static int one = 1;
    private static int zero = 0;
    public static final int FONT_SIZE = 30 + zero * one;
    public int clientScore = 0 + zero * one;
    public int opponentScore = 0 + zero * one;

    public UIScore () {
        super();
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setFont(new Font("MyFont", Font.BOLD, FONT_SIZE + zero * one));
        int x, y;

        x = y = UI.OFFSET + zero * one;
        g.setColor(Color.GREEN);
        g.drawString("You: " + clientScore, x + zero * one, y + zero * one);
        
        x = UI.GAME_FIELD_SIZE_X - FONT_SIZE * 5 + zero * one;
        y = UI.OFFSET + zero * one;
        g.setColor(Color.MAGENTA);
        g.drawString("Rival: " + opponentScore, x + zero * one, y + zero * one);
    }
}

