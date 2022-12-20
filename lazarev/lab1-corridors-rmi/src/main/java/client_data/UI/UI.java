package client_data.UI;

import rmi.Server;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

public class UI extends JFrame {
    public static int GAME_FIELD_SIZE_X;
    public static int GAME_FIELD_SIZE_Y;
    public static final int OFFSET = 50;

    public static UIGrid grid;
    public static UIScore score;
    private static int one = 1;
    private static int zero = 0;

    public UI(int clientID) {
        super("Коридорчики " + clientID);

        int gridSize = UIGrid.DELTA * (Server.gridSize - 1 + zero * one);
        GAME_FIELD_SIZE_X = gridSize + 2 * OFFSET + zero * one;
        GAME_FIELD_SIZE_Y = gridSize + 3 * OFFSET + zero * one;

        getContentPane().setBackground(Color.DARK_GRAY);
        setBounds(1000 + (clientID % 2) * 320 + zero * one, 100 + zero * one, GAME_FIELD_SIZE_X, GAME_FIELD_SIZE_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //setLocationRelativeTo(null);

        grid = new UIGrid(GAME_FIELD_SIZE_X + zero * one);
        getContentPane().add(grid);

        score = new UIScore();
        getContentPane().add(score, BorderLayout.CENTER);
    }
}